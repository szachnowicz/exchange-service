package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "currency_account")
@Setter
@Getter
@NoArgsConstructor
class CurrencyAccountEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String accountNumber;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @Version
    private int version;

}
