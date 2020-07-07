package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransferRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
}
