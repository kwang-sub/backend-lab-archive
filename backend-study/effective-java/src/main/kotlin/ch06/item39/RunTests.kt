package ch06.item39

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

fun main() {
    var tests = 0
    var passed = 0
    val testClass = Sample2::class.java

    for (m: Method in testClass.getDeclaredMethods()) {
        if (m.isAnnotationPresent(ExceptionTest::class.java)) {
            tests += 1
            try {
                m.invoke(null)
                println("테스트 $m 실패 : 예외를 던지지 않음")
            } catch (wrappedExe: InvocationTargetException) {
                val exc = wrappedExe.cause
                val excType = m.getAnnotation(ExceptionTest::class.java).value
                if (excType.isInstance(exc)) {
                    passed++
                } else {
                    println("테스트 $m 실패 : 기대한 예외 ${excType.simpleName} 발생한 예외 $exc")
                }
            } catch (exc: Exception) {
                println("잘못 사용한 @ExceptionTest: $m")
            }
        }
    }
    println("성공 $passed 실패 ${tests - passed}")
}