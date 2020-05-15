package com.szachnowicz.exchange.domian;

import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;

public interface ExchangePort {
     ExchangedValueDto exchangeCurrency(ExchangeRequest exchangeRequest);
}
