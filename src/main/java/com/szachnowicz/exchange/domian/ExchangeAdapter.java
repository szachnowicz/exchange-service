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

        PlnExchangeValue panValueForAmountCurrency = exchangePlnRepo.findPlnExchangeValue(amountCurrency);

        PlnExchangeValue plnValueForTargetCurrecy = exchangePlnRepo.findPlnExchangeValue(targetCurrency);

        BigDecimal exchangeRatio = panValueForAmountCurrency.getExchangeRatio()
                .divide(plnValueForTargetCurrecy.getExchangeRatio()
                        , RoundingMode.HALF_DOWN);

        BigDecimal exchangedAmount = exchangeRequest.getAmount().multiply(exchangeRatio);


        return ExchangedValueDto.builder()
                .amount(exchangedAmount)
                .exchangeRatio(exchangeRatio)
                .amountCurrency(targetCurrency)
                .exchangeTime(ZonedDateTime.now())
                .build();


    }
}
