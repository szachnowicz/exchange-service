package com.szachnowicz.exchange.rest;

import com.szachnowicz.exchange.domian.account.AccountPort;
import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.UserCreationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts/")
@RequiredArgsConstructor
class AcconutContoller {

    private final AccountPort accountPort;

    @PostMapping
    public void createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
         accountPort.createUser(userCreationRequest);
    }

    @GetMapping
    public UserAccountsDto userAccountInfo(@RequestParam String pesel) {
        return accountPort.getUserAccounts(pesel);
    }



}
