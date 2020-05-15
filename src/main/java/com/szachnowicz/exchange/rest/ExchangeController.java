package com.szachnowicz.exchange.rest;

import com.szachnowicz.exchange.domian.CurrencyCode;
import com.szachnowicz.exchange.domian.ExchangePort;
import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ExchangeController {
    private final ExchangePort exchangePort;

    @GetMapping("/exchange")
    public ExchangedValueDto exchangeCurrecy(@RequestParam CurrencyCode ammountCurrnecy, @RequestParam CurrencyCode targedCurrecy,
                                             @RequestParam   Float ammount) {
        ExchangeRequest build = ExchangeRequest.builder().amount(BigDecimal.valueOf(ammount)).amountCurrency(ammountCurrnecy).targetCurrency(targedCurrecy).build();

        return exchangePort.exchangeCurrency(build);
    }

}
