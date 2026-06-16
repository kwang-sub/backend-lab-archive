package org.test.domain.converter

import org.test.SYSTEM_TIME_ZONE
import java.sql.Timestamp
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter(autoApply = true)
class ZonedDateTimeConverter : AttributeConverter<ZonedDateTime, Timestamp> {

    override fun convertToDatabaseColumn(zonedDateTime: ZonedDateTime): Timestamp {
        return Timestamp.from(zonedDateTime.toInstant())
    }

    override fun convertToEntityAttribute(timestamp: Timestamp): ZonedDateTime {
        return ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(SYSTEM_TIME_ZONE))
    }
}