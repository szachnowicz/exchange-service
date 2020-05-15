package com.szachnowicz.exchange.integraion;

import com.szachnowicz.exchange.domian.CurrencyCode;
import com.szachnowicz.exchange.dto.PlnExchangeValue;
import com.szachnowicz.exchange.persitance.ExchangePlnRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NbpIntegrationService implements ExchangeCurrencyProvider {

    private final String URL = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json";


    private final ExchangePlnRepo exchangePlnRepo;


    @Override
    public void batchCurrencyUpdate() {
        List<PlnExchangeValue> plnExchangeValues = parseResponse();
        exchangePlnRepo.saveExchangesInBatch(plnExchangeValues);
    }


    public List<PlnExchangeValue> parseResponse() {
         final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);

        if (forEntity.getStatusCode() == HttpStatus.OK) {
            JsonParser springParser = JsonParserFactory.getJsonParser();
            List<Object> parsedJson = springParser.parseList(forEntity.getBody());
            if (!parsedJson.isEmpty()) {
                Map<String, Object> map = (Map<String, Object>) parsedJson.get(0);

                List<Map<String, Object>> currecyValues = (List<Map<String, Object>>) map.get("rates");

                return currecyValues.stream().map(e -> {

                    CurrencyCode code = CurrencyCode.valueOf((String) e.get("code"));
                    BigDecimal ratio = BigDecimal.valueOf((Double) e.get("mid"));

                    return PlnExchangeValue.builder().exchangeRatio(ratio)
                            .currencyCode(code)
                            .exchangeDate(ZonedDateTime.now()).build();

                }).collect(Collectors.toList());
            }
        }
        return Collections.emptyList();

    }

}

