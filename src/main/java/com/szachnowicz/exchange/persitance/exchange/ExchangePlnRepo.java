package com.szachnowicz.exchange.persitance.exchange;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.dto.PlnExchangeValue;

import java.util.List;

public interface ExchangePlnRepo {
    PlnExchangeValue findPlnExchangeValue(CurrencyCode currencyCode);

    void saveExchangesInBatch(List<PlnExchangeValue> values);
}
