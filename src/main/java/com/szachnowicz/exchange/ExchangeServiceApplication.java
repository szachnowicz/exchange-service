package com.szachnowicz.exchange;

import com.szachnowicz.exchange.integraion.ExchangeCurrencyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeServiceApplication implements ApplicationRunner {

    @Autowired
    private ExchangeCurrencyProvider exchangeCurrencyProvider;

    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        exchangeCurrencyProvider.batchCurrencyUpdate();

    }
}
