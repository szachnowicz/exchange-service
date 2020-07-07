package com.szachnowicz.exchange.dto;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class UserCreationRequest {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String dateOfBirth;
    @Size(min = 11, max = 11)
    private String pesel;
    private BigDecimal initialBalance;
    private CurrencyCode currencyCode;

}
