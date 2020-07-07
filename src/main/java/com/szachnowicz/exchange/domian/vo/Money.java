package com.szachnowicz.exchange.domian.vo;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Money {

    private CurrencyCode currencyCode;
    private BigDecimal amount;

    public Money(CurrencyCode currencyCode, BigDecimal amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public static Money of(BigDecimal initialBalance, CurrencyCode currencyCode) {
        return new Money(currencyCode, initialBalance);
    }

    public BigDecimal add(BigDecimal adding) {
        this.amount = amount.add(adding);
        return amount;
    }

    public BigDecimal subtract(BigDecimal subtract) {
        this.amount = amount.subtract(subtract);
        return amount;
    }

    public BigDecimal multiply(BigDecimal multiply) {
        this.amount = amount.multiply(multiply);
        return amount;
    }

    public BigDecimal divide(BigDecimal divide) {
        this.amount = amount.divide(divide, 2, RoundingMode.HALF_DOWN);
        return amount;
    }

    public boolean isMoreThen(BigDecimal more) {
        return amount.compareTo(more) > 0;
    }

    public boolean isLessThen(BigDecimal less) {
        return amount.compareTo(less) < 0;
    }

    public boolean isEqual(BigDecimal equal) {
        return amount.compareTo(equal) == 0;
    }


}
