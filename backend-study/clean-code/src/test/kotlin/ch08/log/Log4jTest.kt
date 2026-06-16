package ch08.log

import org.apache.log4j.ConsoleAppender
import org.apache.log4j.Logger
import org.apache.log4j.PatternLayout
import org.junit.jupiter.api.Test


class Log4jTest {

    @Test
    fun testLogCreate() {
        val logger = Logger.getLogger("MyLogger")
        logger.info("hello")
    }

    @Test
    fun testLogAddAppender() {
        // given
        val logger = Logger.getLogger(this::class.java)
        val appender = ConsoleAppender(PatternLayout("%p %t %m%n"), ConsoleAppender.SYSTEM_OUT)
        logger.addAppender(appender)

        // when
        logger.info("hello!!")

        //then
    }

}