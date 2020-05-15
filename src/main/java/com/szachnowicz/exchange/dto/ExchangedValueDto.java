package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.CurrencyCode;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
@Getter
public class ExchangedValueDto {
    private BigDecimal amount;
    private CurrencyCode amountCurrency;
    private BigDecimal exchangeRatio;
    private ZonedDateTime exchangeTime;

}
