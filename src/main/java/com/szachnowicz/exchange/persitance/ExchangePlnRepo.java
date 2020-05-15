package com.szachnowicz.exchange.persitance;

import com.szachnowicz.exchange.domian.CurrencyCode;
import com.szachnowicz.exchange.dto.PlnExchangeValue;

import java.util.List;

public interface ExchangePlnRepo {
    PlnExchangeValue findPlnExchangeValue(CurrencyCode currencyCode);


    void saveExchangesInBatch(List<PlnExchangeValue> values);
}
