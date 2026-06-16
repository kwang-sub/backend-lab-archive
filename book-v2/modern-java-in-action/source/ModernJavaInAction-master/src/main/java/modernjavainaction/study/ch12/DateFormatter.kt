package modernjavainaction.study.ch12

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


fun main() {
    // 날짜타입을 원하는 포맷에 문자열로 변경하는 방법
    val now = LocalDate.now()
    val s1 = now.format(DateTimeFormatter.BASIC_ISO_DATE) // yyyyMMdd
    val s2 = now.format(DateTimeFormatter.ISO_LOCAL_DATE) // yyyy-MM-dd

    // 포맷에 맞는 문자열을 날짜 타입으로 변환하는 방법
    val parseDate = LocalDate.parse("20231217", DateTimeFormatter.BASIC_ISO_DATE)

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy yyyy!!")
    val customFormat = now.format(formatter)

    val koreanFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd", Locale.KOREAN)
    val koreanDate = now.format(koreanFormatter)
}