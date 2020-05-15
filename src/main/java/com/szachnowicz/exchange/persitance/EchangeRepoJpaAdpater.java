package com.szachnowicz.exchange.persitance;


import com.szachnowicz.exchange.domian.CurrencyCode;
import com.szachnowicz.exchange.dto.PlnExchangeValue;
import com.szachnowicz.exchange.dto.error.BusinessErrorCodes;
import com.szachnowicz.exchange.dto.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class EchangeRepoJpaAdpater implements ExchangePlnRepo {

    @Autowired
    private final ExchangePlnJpaRepo exchangePlnJpaRepo;

    @Override
    @Transactional
    public PlnExchangeValue findPlnExchangeValue(CurrencyCode currencyCode) {


        if (currencyCode == CurrencyCode.PLN) {
            return PlnExchangeValue.builder()
                    .exchangeDate(ZonedDateTime.now())
                    .currencyCode(CurrencyCode.PLN)
                    .exchangeRatio(BigDecimal.ONE).build();
        }

        PlnExchangeEntity byCurrency =
                exchangePlnJpaRepo.findByCurrencyCode(currencyCode)
                        .orElseThrow(() -> new BusinessException(BusinessErrorCodes.CURRECY_NOT_FOUND));

        return PlnExchangeValue
                .builder()
                .currencyCode(byCurrency.getCurrencyCode())
                .exchangeRatio(byCurrency.getExchangeRatio())
                .exchangeDate(byCurrency.getExchangeDate())
                .build();
    }

    @Override
    @Transactional
    public void saveExchangesInBatch(List<PlnExchangeValue> values) {
        values.forEach(ele -> {
                    PlnExchangeEntity plnExchangeEntity = new PlnExchangeEntity();
                    plnExchangeEntity.setCurrencyCode(ele.getCurrencyCode());
                    plnExchangeEntity.setExchangeDate(ele.getExchangeDate());
                    plnExchangeEntity.setExchangeRatio(ele.getExchangeRatio());
                    exchangePlnJpaRepo.save(plnExchangeEntity);
                }
        );
    }
}
