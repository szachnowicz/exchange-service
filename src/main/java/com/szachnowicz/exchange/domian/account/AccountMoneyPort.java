package com.szachnowicz.exchange.domian.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.dto.DepositRequest;
import com.szachnowicz.exchange.dto.TransferRequest;
import com.szachnowicz.exchange.dto.WithdrawRequest;

public interface AccountMoneyPort {


    void transferMoney(TransferRequest transferRquest);

    void transferAndExchangeMoney(TransferRequest transferRquest, CurrencyCode exchangeTo);


    void deposit(DepositRequest depositRequest);

    void withdraw(WithdrawRequest withdrawRequest);


}
