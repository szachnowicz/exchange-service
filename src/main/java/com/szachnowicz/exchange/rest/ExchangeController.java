package com.szachnowicz.exchange.rest;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.domian.exchange.ExchangePort;
import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
class ExchangeController {
    private final ExchangePort exchangePort;

    @GetMapping
    public ExchangedValueDto exchangeCurrecy(@RequestParam CurrencyCode ammountCurrnecy, @RequestParam CurrencyCode targetCorrect,
                                             @RequestParam Float amount) {
        ExchangeRequest build = ExchangeRequest.builder()
                .amount(BigDecimal.valueOf(amount))
                .amountCurrency(ammountCurrnecy)
                .targetCurrency(targetCorrect)
                .build();

        return exchangePort.exchangeCurrency(build);
    }

}
