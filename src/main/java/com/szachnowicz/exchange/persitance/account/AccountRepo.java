package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.vo.Money;
import com.szachnowicz.exchange.dto.UserAccountsDto;

public interface AccountRepo {

    void createAccount(Long userId, Money money);

    UserAccountsDto getAccounts(String pesel);

    Long getUserId(String pesel);
}
