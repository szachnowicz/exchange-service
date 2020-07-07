package com.szachnowicz.exchange.domian.account;

import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.UserCreationRequest;

public interface AccountPort {

    void createUser(UserCreationRequest userCreationRequest);

    UserAccountsDto getUserAccounts(String pesel);
}
