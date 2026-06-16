package org.example.level2bookmanagementsystem.security.service

import org.example.level2bookmanagementsystem.security.util.CryptoUtil
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CryptoUtilTest : BehaviorSpec({

    val cryptoUtil = CryptoUtil("HOUcUnw574DticDirb6Y6y1MVkniCvtsfVHPq1fSjS8=")

    Given("encryptAesGcm") {
        When("암호화 결과는 원문과 달라야 한다") {
            Then("encrypted는 원문과 다르고 비어있지 않다") {
                val input = "testString"
                val encrypted = cryptoUtil.encryptAesGcm(input)
                encrypted shouldNotBe input
                encrypted.isNotBlank() shouldBe true
            }
        }
    }

    Given("decryptAesGcm") {
        When("복호화시 원문과 같아야 한다") {
            Then("decrypted가 원문과 동일") {
                val input = "testString"
                val encrypted = cryptoUtil.encryptAesGcm(input)
                val decrypted = cryptoUtil.decryptAesGcm(encrypted)
                decrypted shouldBe input
            }
        }
    }

    Given("hashArgon2") {
        When("해시 결과는 원문과 달라야 한다") {
            Then("hashed는 원문과 다르다") {
                val input = "testString"
                val hashed = cryptoUtil.hashArgon2(input)
                hashed shouldNotBe input
            }
        }
    }

    Given("verifyArgon2") {
        When("해시 검증 성공") {
            Then("verifyArgon2가 true를 반환한다") {
                val input = "testString"
                val hashed = cryptoUtil.hashArgon2(input)
                val verified = cryptoUtil.verifyArgon2(hashed, input.toCharArray())
                verified.shouldBeTrue()
            }
        }
    }

})
