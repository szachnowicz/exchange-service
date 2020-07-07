package com.szachnowicz.exchange.domian.vo;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyTest {

    Money money;

    @BeforeEach
    void setUp() {
        money = Money.of(BigDecimal.ONE, CurrencyCode.PLN);
    }

    @Test
    void add() {
        money.add(BigDecimal.ONE);
        assertEquals(money.getAmount(), BigDecimal.valueOf(2));
    }

    @Test
    void subtract() {
        money.subtract(BigDecimal.ONE);
        assertEquals(money.getAmount(), BigDecimal.ZERO);
    }

    @Test
    void multiply() {
        money.multiply(BigDecimal.TEN);
        assertEquals(money.getAmount(), BigDecimal.TEN);
    }

    @Test
    void divide() {
        money.divide(BigDecimal.valueOf(0.1));
        assertEquals(0, money.getAmount().compareTo(BigDecimal.TEN));
    }

    @Test
    void isMoreThen() {
        assertFalse(money.isMoreThen(BigDecimal.ONE));
        assertTrue(money.isMoreThen(BigDecimal.valueOf(0.9)));
    }

    @Test
    void isLessThen() {
        assertTrue(money.isLessThen(BigDecimal.TEN));
        assertFalse(money.isLessThen(BigDecimal.valueOf(0.6)));
    }

    @Test
    void isEqual() {
        assertFalse(money.isMoreThen(BigDecimal.ONE));
    }


}