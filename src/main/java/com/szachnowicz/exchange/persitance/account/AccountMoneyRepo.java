package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.domian.vo.AccountNumber;
import com.szachnowicz.exchange.domian.vo.Money;

public interface AccountMoneyRepo {

    void depositMoney(Money money, Long userId);

    void withdrawMoney(Money money, Long userId);

    void transferMoney(Money money, AccountNumber from, AccountNumber to);

    void transferMoneyAndExchange(Money money, CurrencyCode exchangeTo, AccountNumber from, AccountNumber to);
}
