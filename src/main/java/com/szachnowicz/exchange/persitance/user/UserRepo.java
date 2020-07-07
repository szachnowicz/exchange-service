package com.szachnowicz.exchange.persitance.user;

import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.UserCreationRequest;

public interface UserRepo {

    Long saveUser(UserCreationRequest userCreationRequest);

    UserAccountsDto getUser(String pesel);
}
