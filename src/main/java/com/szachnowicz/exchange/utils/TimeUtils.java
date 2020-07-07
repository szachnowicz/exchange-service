package com.szachnowicz.exchange.utils;

import com.szachnowicz.exchange.dto.error.BusinessException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.WRONG_DATE_FORMAT;

public class TimeUtils {
    public static LocalDate parseDate(String dateOfBirth) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateOfBirth, format);
        } catch (DateTimeParseException timeParseException) {
            timeParseException.printStackTrace();
            throw new BusinessException(WRONG_DATE_FORMAT, "Wrong date format please, fallow this - 'yyyy-MM-dd' pattern");
        }
    }
}
