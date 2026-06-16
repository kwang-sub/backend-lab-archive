package org.example.level2bookmanagementsystem.security.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.example.level2bookmanagementsystem.security.util.CryptoUtil

/**
 * Argon2를 이용한 단방향 암호화 컨버터
 * 데이터베이스에 저장할 때는 해시 값으로 변환하고, 읽을 때는 해시 값을 그대로 반환
 */
@Converter
class Argon2HashConverter(
    private val cryptoUtil: CryptoUtil
) : AttributeConverter<String, String> {

    /**
     * 엔티티의 속성값을 데이터베이스 컬럼값으로 변환 (암호화)
     * @param attribute 원본 문자열
     * @return 해시된 문자열
     */
    override fun convertToDatabaseColumn(attribute: String?): String? {
        return attribute?.let { cryptoUtil.hashArgon2(it) }
    }

    /**
     * 데이터베이스 컬럼값을 엔티티 속성값으로 변환 (해시값 그대로 반환)
     * @param dbData 데이터베이스에 저장된 해시값
     * @return 해시값 (복호화 불가능)
     */
    override fun convertToEntityAttribute(dbData: String?): String? {
        return dbData // 해시값을 그대로 반환 (복호화 불가능)
    }
}
