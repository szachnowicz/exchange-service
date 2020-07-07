package com.szachnowicz.exchange.domian.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.domian.vo.AccountNumber;
import com.szachnowicz.exchange.domian.vo.Money;
import com.szachnowicz.exchange.dto.DepositRequest;
import com.szachnowicz.exchange.dto.TransferRequest;
import com.szachnowicz.exchange.dto.WithdrawRequest;
import com.szachnowicz.exchange.persitance.account.AccountMoneyRepo;
import com.szachnowicz.exchange.persitance.account.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AccountMoneyApdater implements AccountMoneyPort {

    private final AccountMoneyRepo moneyRepo;
    private final AccountRepo accountRepo;


    @Override
    public void transferMoney(TransferRequest request) {
        Money money = Money.of(request.getAmount(), request.getCurrencyCode());
        AccountNumber from = AccountNumber.from(request.getFromAccount());
        AccountNumber to = AccountNumber.from(request.getToAccount());

        moneyRepo.transferMoney(money, from, to);
    }

    @Override
    public void transferAndExchangeMoney(TransferRequest request, CurrencyCode exchangeTo) {
        Money money = Money.of(request.getAmount(), request.getCurrencyCode());
        AccountNumber from = AccountNumber.from(request.getFromAccount());
        AccountNumber to = AccountNumber.from(request.getToAccount());
        moneyRepo.transferMoneyAndExchange(money, exchangeTo, from, to);

    }

    @Override
    public void deposit(DepositRequest request) {
        Money money = Money.of(request.getAmount(), request.getCurrencyCode());
        Long userId = accountRepo.getUserId(request.getPesel());
        moneyRepo.depositMoney(money, userId);
    }

    @Override
    public void withdraw(WithdrawRequest request) {
        Money money = Money.of(request.getAmount(), request.getCurrencyCode());
        Long userId = accountRepo.getUserId(request.getPesel());
        moneyRepo.withdrawMoney(money, userId);
    }
}
