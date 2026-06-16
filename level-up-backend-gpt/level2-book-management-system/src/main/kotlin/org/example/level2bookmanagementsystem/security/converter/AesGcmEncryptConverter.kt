package org.example.level2bookmanagementsystem.security.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.example.level2bookmanagementsystem.security.util.CryptoUtil

/**
 * AES-GCM을 이용한 양방향 암호화 컨버터
 * 데이터베이스에 저장할 때는 암호화하고, 읽을 때는 복호화
 */
@Converter
class AesGcmEncryptConverter(
    private val cryptoUtil: CryptoUtil
) : AttributeConverter<String, String> {


    /**
     * 엔티티의 속성값을 데이터베이스 컬럼값으로 변환 (암호화)
     * @param attribute 원본 평문
     * @return 암호화된 문자열
     */
    override fun convertToDatabaseColumn(attribute: String?): String? {
        return attribute?.let { cryptoUtil.encryptAesGcm(it) }
    }

    /**
     * 데이터베이스 컬럼값을 엔티티 속성값으로 변환 (복호화)
     * @param dbData 데이터베이스에 저장된 암호화된 데이터
     * @return 복호화된 평문
     */
    override fun convertToEntityAttribute(dbData: String?): String? {
        return dbData?.let { cryptoUtil.decryptAesGcm(it) }
    }
}
