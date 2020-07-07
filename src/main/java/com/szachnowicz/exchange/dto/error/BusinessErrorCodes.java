package com.szachnowicz.exchange.dto.error;

public enum BusinessErrorCodes implements ErrorCode {
    VALIDATION_EXCEPTION,
    ACCOUNT_EXIST,
    CURRENCY_NOT_FOUND,
    WRONG_DATE_FORMAT,
    IS_NOT_ADULT,
    PESEL_EXIST,
    ACCOUNT_NOT_EXIST,
    NOT_ENOUGH_FOUNDS

}
