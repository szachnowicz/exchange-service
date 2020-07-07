package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.exchange.CurrencyCode;
import com.szachnowicz.exchange.domian.exchange.ExchangePort;
import com.szachnowicz.exchange.domian.vo.AccountNumber;
import com.szachnowicz.exchange.domian.vo.Money;
import com.szachnowicz.exchange.dto.ExchangeRequest;
import com.szachnowicz.exchange.dto.ExchangedValueDto;
import com.szachnowicz.exchange.dto.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.ACCOUNT_NOT_EXIST;
import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.NOT_ENOUGH_FOUNDS;

@Service
@RequiredArgsConstructor
class AccoutnMoneyJpaRepo implements AccountMoneyRepo {
    private final AccountJpaRepo accountJpaRepo;
    private final ExchangePort exchangePort;
    private final AccountRepo accountRepo;


    @Override
    @Transactional
    public void depositMoney(Money money, Long userId) {
        Optional<CurrencyAccountEntity> entityOptional = accountJpaRepo.findByUserIdAndCurrencyCode(userId, money.getCurrencyCode());
        if (entityOptional.isPresent()) {
            CurrencyAccountEntity entity = entityOptional.get();
            entity.setBalance(money.add(entity.getBalance()));
            accountJpaRepo.save(entity);
        } else {
            accountRepo.createAccount(userId, money);
        }
    }

    @Override
    @Transactional
    public void withdrawMoney(Money money, Long userId) {
        Optional<CurrencyAccountEntity> entityOptional = accountJpaRepo.findByUserIdAndCurrencyCode(userId, money.getCurrencyCode());

        CurrencyAccountEntity entity = entityOptional.orElseThrow(() ->
                new BusinessException(ACCOUNT_NOT_EXIST, String.format("Account not exist for given user %d with given currency %s", userId, money.getCurrencyCode())));

        BigDecimal balance = entity.getBalance();
        if (money.isLessThen(balance) || money.isEqual(entity.getBalance())) {
            BigDecimal amountAfter = balance.subtract(money.getAmount());
            entity.setBalance(amountAfter);
            accountJpaRepo.save(entity);
        } else {
            throw new BusinessException(NOT_ENOUGH_FOUNDS, "Not enough founds on account");
        }
    }

    @Override
    public void transferMoney(Money money, AccountNumber owner, AccountNumber destination) {
        Optional<CurrencyAccountEntity> optionalFrom = accountJpaRepo.findByAccountNumber(owner.getNumber());
        Optional<CurrencyAccountEntity> optionalTo = accountJpaRepo.findByAccountNumber(destination.getNumber());

        CurrencyAccountEntity ownerEntity = optionalFrom.orElseThrow(() ->
                new BusinessException(ACCOUNT_NOT_EXIST, "Owner Account not exist"));
        CurrencyAccountEntity destinationEntity = optionalTo.orElseThrow(() ->
                new BusinessException(ACCOUNT_NOT_EXIST, "Destination Account not exist."));

        withdrawMoney(money, ownerEntity.getUserId());
        depositMoney(money, destinationEntity.getUserId());
    }

    @Override
    public void transferMoneyAndExchange(Money money, CurrencyCode targetCurrency, AccountNumber from, AccountNumber to) {
        ExchangeRequest request = ExchangeRequest.builder()
                .amount(money.getAmount())
                .amountCurrency(money.getCurrencyCode())
                .targetCurrency(targetCurrency).build();

        ExchangedValueDto exchangedValueDto = exchangePort.exchangeCurrency(request);
        Money exchanged = Money.of(exchangedValueDto.getAmount(), exchangedValueDto.getAmountCurrency());
        transferMoney(exchanged, from, to);
    }

}
