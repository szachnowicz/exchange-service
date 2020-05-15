package com.szachnowicz.exchange.domian;

import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;
import com.szachnowicz.exchange.dto.PlnExchangeValue;
import com.szachnowicz.exchange.persitance.ExchangePlnRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;


@Service
@RequiredArgsConstructor
class ExchangeAdapter implements ExchangePort {

    private final ExchangePlnRepo exchangePlnRepo;


    @Override
    public ExchangedValueDto exchangeCurrency(ExchangeRequest exchangeRequest) {

        CurrencyCode amountCurrency = exchangeRequest.getAmountCurrency();

        CurrencyCode targetCurrency = exchangeRequest.getTargetCurrency();

        PlnExchangeValue plnValueForAmountCurrency = exchangePlnRepo.findPlnExchangeValue(amountCurrency);

        PlnExchangeValue panValueForTargetCurrency = exchangePlnRepo.findPlnExchangeValue(targetCurrency);

        BigDecimal exchangeRatio = plnValueForAmountCurrency.getExchangeRatio()
                .divide(panValueForTargetCurrency.getExchangeRatio(),
                       3 , RoundingMode.HALF_DOWN);

        BigDecimal exchangedAmount = exchangeRequest.getAmount().multiply(exchangeRatio);


        return ExchangedValueDto.builder()
                .amount(exchangedAmount)
                .exchangeRatio(exchangeRatio)
                .amountCurrency(targetCurrency)
                .exchangeTime(ZonedDateTime.now())
                .build();


    }
}
