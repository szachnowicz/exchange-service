package com.szachnowicz.exchange.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
public class UserAccountsDto {
    private Long userId;
    private String pesel;
    private String name;
    private String lastName;
    private LocalDate birthDay;
    private List<CurrencyAccountDto> currencyAccounts;
}
