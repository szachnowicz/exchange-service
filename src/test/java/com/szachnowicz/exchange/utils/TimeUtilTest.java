package com.szachnowicz.exchange.utils;

import com.szachnowicz.exchange.dto.error.BusinessErrorCodes;
import com.szachnowicz.exchange.dto.error.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeUtilTest {

    @Test
    void parseOfCorrectFormatTest() {

        LocalDate localDate = TimeUtils.parseDate("1999-05-14");

        assertEquals(1999, localDate.getYear());
        assertEquals(Month.MAY, localDate.getMonth());
        assertEquals(14, localDate.getDayOfMonth());
    }

    @Test
    void parseOfInCorrectFormatTest() {


        BusinessException businessException = assertThrows(BusinessException.class, () -> TimeUtils.parseDate("1999/05/14 12:00:11"));
        assertEquals(businessException.getErrorCode(), BusinessErrorCodes.WRONG_DATE_FORMAT);

    }
}