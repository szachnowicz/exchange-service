package com.szachnowicz.exchange.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;
import java.util.stream.Collectors;
@Getter
@AllArgsConstructor
public class AccountNumber {
    private String number;

    public static  AccountNumber from(String number) {
        return new AccountNumber(number);
    }

    public static String generate() {
        return new Random().ints(16, 0, 9)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }


}
