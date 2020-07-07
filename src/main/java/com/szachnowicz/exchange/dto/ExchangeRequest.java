package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequest {
    private BigDecimal amount;
    private CurrencyCode amountCurrency;
    private CurrencyCode targetCurrency;
}
