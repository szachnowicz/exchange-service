package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyAccountDto {
    private CurrencyCode currencyCode;
    private BigDecimal amount;
    private String accountNumber;


    public static CurrencyAccountDto of(BigDecimal amount, CurrencyCode currencyCode,String accountNumber) {
        return new CurrencyAccountDto(currencyCode, amount,accountNumber);
    }

}
