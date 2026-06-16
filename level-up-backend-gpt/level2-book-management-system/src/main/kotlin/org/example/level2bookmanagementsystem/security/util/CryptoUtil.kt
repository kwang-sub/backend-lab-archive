package org.example.level2bookmanagementsystem.security.util

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.ByteBuffer
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class CryptoUtil(
    @param:Value("\${app.crypto.aes256gcm.key}")
    private val base64Key: String
) {

    private val key: SecretKey
    private val secureRandom = SecureRandom()
    private val iterations = 3
    private val memoryKiB = 65536   // 64MB
    private val parallelism = 1

    init {
                val decodedKey = Base64.getDecoder().decode(base64Key)
        require(decodedKey.size == 32) { "AES-256-GCM key must be 32 bytes" }
        this@CryptoUtil.key = SecretKeySpec(decodedKey, "AES")
    }

    /** plaintext → Base64( version(1) | iv(12) | ct+tag ) */
    fun encryptAesGcm(plaintext: String, aad: ByteArray? = null): String {
        val iv = ByteArray(12).also { secureRandom.nextBytes(it) }
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(128, iv))
        if (aad != null) cipher.updateAAD(aad)
        val ct = cipher.doFinal(plaintext.toByteArray(Charsets.UTF_8))

        val payload = ByteBuffer.allocate(1 + iv.size + ct.size)
            .put(1)            // version
            .put(iv)
            .put(ct)
            .array()
        return Base64.getEncoder().encodeToString(payload)
    }

    /** Base64( version | iv | ct+tag ) → plaintext */
    fun decryptAesGcm(token: String, aad: ByteArray? = null): String {
        val payload = Base64.getDecoder().decode(token)
        val buf = ByteBuffer.wrap(payload)
        val version = buf.get().toInt()
        require(version == 1) { "Unsupported crypto version: $version" }

        val iv = ByteArray(12).also { buf.get(it) }
        val ct = ByteArray(buf.remaining()).also { buf.get(it) }

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
        if (aad != null) cipher.updateAAD(aad)
        return String(cipher.doFinal(ct), Charsets.UTF_8)
    }


    /** 문자열 해시 생성 (복호화 불가) */
    fun hashArgon2(raw: String): String {
        val argon2 = getArgon2()
        val chars = raw.toCharArray()
        return argon2.hash(iterations, memoryKiB, parallelism, chars)
            .also { argon2.wipeArray(chars) } // 민감정보 메모리에서 지우기
    }

    /** 비밀번호 검증: 입력 평문 vs 저장된 해시 */
    fun verifyArgon2(hash: String, raw: CharArray): Boolean {
        val argon2 = getArgon2()
        return argon2.verify(hash, raw)
            .also { argon2.wipeArray(raw) } // 민감정보 메모리에서 지우기
    }

    private fun getArgon2(): Argon2 {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)
    }
}