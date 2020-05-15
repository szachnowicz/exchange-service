package com.szachnowicz.exchange.domian;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
class PlnExchangeValue {

    private CurrencyCode targetCurrency; // usd
    private BigDecimal exchangeRatio; // 4.5552
    private final CurrencyCode currencyCode = CurrencyCode.PLN;

}
