package com.szachnowicz.exchange.persitance.account;

import com.szachnowicz.exchange.domian.vo.AccountNumber;
import com.szachnowicz.exchange.domian.vo.Money;
import com.szachnowicz.exchange.dto.CurrencyAccountDto;
import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.error.BusinessException;
import com.szachnowicz.exchange.persitance.user.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.ACCOUNT_EXIST;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountJpaRepoAdapter implements AccountRepo {

    private final AccountJpaRepo accountJpaRepo;
    private final UserRepo userRepo;


    @Override
    @Transactional
    public void createAccount(Long userId, Money money) {
        if (accountJpaRepo.findByUserIdAndCurrencyCode(userId, money.getCurrencyCode()).isPresent()) {
            throw new BusinessException(ACCOUNT_EXIST, "Account already exist for given user");
        }
        CurrencyAccountEntity entity = new CurrencyAccountEntity();
        entity.setAccountNumber(AccountNumber.generate());
        entity.setBalance(money.getAmount());
        entity.setUserId(userId);
        entity.setCurrencyCode(money.getCurrencyCode());
        accountJpaRepo.save(entity);

        log.info(String.format("New account created for user - %d, in %s currency.", userId, money.getCurrencyCode()));
    }

    @Override
    @Transactional
    public UserAccountsDto getAccounts(String pesel) {
        UserAccountsDto user = userRepo.getUser(pesel);
        List<CurrencyAccountEntity> allByUserId = accountJpaRepo.findAllByUserId(user.getUserId());
        user.setCurrencyAccounts(
                allByUserId.stream()
                        .map(c -> CurrencyAccountDto.of(c.getBalance(), c.getCurrencyCode(), c.getAccountNumber()))
                        .collect(Collectors.toList()));
        return user;
    }

    @Override
    public Long getUserId(String pesel) {
        UserAccountsDto user = userRepo.getUser(pesel);
        return user.getUserId();
    }


}
