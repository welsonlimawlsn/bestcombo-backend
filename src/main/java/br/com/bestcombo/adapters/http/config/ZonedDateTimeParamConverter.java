package br.com.bestcombo.adapters.http.config;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.ext.ParamConverter;

public class ZonedDateTimeParamConverter implements ParamConverter<ZonedDateTime> {

    @Override
    public ZonedDateTime fromString(String s) {
        return ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public String toString(ZonedDateTime zonedDateTime) {
        return DateTimeFormatter.ISO_DATE_TIME.format(zonedDateTime);
    }

}
