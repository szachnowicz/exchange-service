package com.szachnowicz.exchange.rest;

import com.szachnowicz.exchange.domian.account.AccountMoneyPort;
import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.dto.DepositRequest;
import com.szachnowicz.exchange.dto.TransferRequest;
import com.szachnowicz.exchange.dto.WithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/money/")
@RequiredArgsConstructor
class AccountMoneyContoller {

    private final AccountMoneyPort accountMoneyPort;

    @PutMapping("/deposit")
    public void deposit(@RequestBody DepositRequest depositRequest) {
        accountMoneyPort.deposit(depositRequest);
    }

    @PutMapping("/withdraw")
    public void withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        accountMoneyPort.withdraw(withdrawRequest);
    }

    @PutMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transferRequest) {
        accountMoneyPort.transferMoney(transferRequest);
    }

    @PutMapping("/transfer&exchange")
    public void transferWithExchange(@RequestBody TransferRequest transferRequest,@RequestParam CurrencyCode currencyCode) {
        accountMoneyPort.transferAndExchangeMoney(transferRequest, currencyCode);
    }


}
