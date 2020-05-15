package com.szachnowicz.exchange.domian;

import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;
import com.szachnowicz.exchange.dto.PlnExchangeValue;
import com.szachnowicz.exchange.persitance.ExchangePlnRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExchangeAdapterTest {

    private ExchangeAdapter exchangeAdapter;

    @BeforeEach
    void setUp() {
        exchangeAdapter = new ExchangeAdapter(new ExchangePlnRepo() {
            @Override
            public PlnExchangeValue findPlnExchangeValue(CurrencyCode currencyCode) {

                if (currencyCode == CurrencyCode.PLN)
                    return PlnExchangeValue.builder()
                            .currencyCode(CurrencyCode.PLN)
                            .exchangeDate(ZonedDateTime.now())
                            .exchangeRatio(BigDecimal.ONE)
                            .build();

                if (currencyCode == CurrencyCode.GBP)
                    return PlnExchangeValue.builder()
                            .currencyCode(CurrencyCode.GBP)
                            .exchangeDate(ZonedDateTime.now())
                            .exchangeRatio(BigDecimal.valueOf(5.00))
                            .build();

                if (currencyCode == CurrencyCode.USD)
                    return PlnExchangeValue.builder()
                            .currencyCode(CurrencyCode.GBP)
                            .exchangeDate(ZonedDateTime.now())
                            .exchangeRatio(BigDecimal.valueOf(4.5552))
                            .build();

                return null;
            }

            @Override
            public void saveExchangesInBatch(List<PlnExchangeValue> values) {

            }
        });
    }

    @Test
    void exchangeUSDtoPLN() {

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setAmount(BigDecimal.valueOf(100));
        exchangeRequest.setAmountCurrency(CurrencyCode.USD);
        exchangeRequest.setTargetCurrency(CurrencyCode.PLN);


        ExchangedValueDto exchangedValueDto = exchangeAdapter.exchangeCurrency(exchangeRequest);

        BigDecimal exchangeRatio = BigDecimal.valueOf(4.5552).divide(BigDecimal.ONE, RoundingMode.HALF_DOWN);

        assertEquals(exchangedValueDto.getAmount(), BigDecimal.valueOf(100).multiply(exchangeRatio));
        assertEquals(exchangedValueDto.getAmountCurrency(), CurrencyCode.PLN);
        assertEquals(exchangedValueDto.getExchangeRatio(), exchangeRatio);


    }

    @Test
    void exchangeGPBtoPLN() {

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setAmount(BigDecimal.valueOf(100));
        exchangeRequest.setAmountCurrency(CurrencyCode.GBP);
        exchangeRequest.setTargetCurrency(CurrencyCode.PLN);


        ExchangedValueDto exchangedValueDto = exchangeAdapter.exchangeCurrency(exchangeRequest);

        BigDecimal exchangeRatio = BigDecimal.valueOf(5.00).divide(BigDecimal.ONE, RoundingMode.HALF_DOWN);

        assertEquals(exchangedValueDto.getAmount(), BigDecimal.valueOf(100).multiply(exchangeRatio));

        assertEquals(exchangedValueDto.getAmountCurrency(), CurrencyCode.PLN);
        assertEquals(exchangedValueDto.getExchangeRatio(), BigDecimal.valueOf(5.00));


    }


    @Test
    void exchangeGPBtoUSD() {

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setAmount(BigDecimal.valueOf(100));
        exchangeRequest.setAmountCurrency(CurrencyCode.GBP);
        exchangeRequest.setTargetCurrency(CurrencyCode.USD);


        ExchangedValueDto exchangedValueDto = exchangeAdapter.exchangeCurrency(exchangeRequest);

        BigDecimal exchangeRatio = BigDecimal.valueOf(5.00).divide(BigDecimal.valueOf(4.5552), RoundingMode.HALF_DOWN);

        assertEquals(exchangedValueDto.getAmount(), BigDecimal.valueOf(100).multiply(exchangeRatio));
        assertEquals(exchangedValueDto.getAmountCurrency(), CurrencyCode.USD);
        assertEquals(exchangedValueDto.getExchangeRatio(), exchangeRatio);


    }

    @Test
    void exchangeUSDtoGPB() {

        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setAmount(BigDecimal.valueOf(100));
        exchangeRequest.setAmountCurrency(CurrencyCode.USD);
        exchangeRequest.setTargetCurrency(CurrencyCode.GBP);


        ExchangedValueDto exchangedValueDto = exchangeAdapter.exchangeCurrency(exchangeRequest);

        BigDecimal exchangeRatio = BigDecimal.valueOf(4.5552).divide(BigDecimal.valueOf(5.00), RoundingMode.HALF_DOWN);

        assertEquals(exchangedValueDto.getAmount(), BigDecimal.valueOf(100).multiply(exchangeRatio));
        assertEquals(exchangedValueDto.getAmountCurrency(), CurrencyCode.GBP);
        assertEquals(exchangedValueDto.getExchangeRatio(), exchangeRatio);


    }


}