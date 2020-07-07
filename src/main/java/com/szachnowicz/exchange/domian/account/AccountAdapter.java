package com.szachnowicz.exchange.domian.account;

import com.szachnowicz.exchange.domian.vo.Money;
import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.UserCreationRequest;
import com.szachnowicz.exchange.dto.error.BusinessErrorCodes;
import com.szachnowicz.exchange.dto.error.BusinessException;
import com.szachnowicz.exchange.persitance.account.AccountRepo;
import com.szachnowicz.exchange.persitance.user.UserRepo;
import com.szachnowicz.exchange.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
class AccountAdapter implements AccountPort {

    private final UserRepo userRepo;
    private final AccountRepo accountRepo;


    @Override
    public void createUser(UserCreationRequest userCreationRequest) {
        validateBirthDay(userCreationRequest.getDateOfBirth());
        Long userId = userRepo.saveUser(userCreationRequest);
        Money money = Money.of(userCreationRequest.getInitialBalance(), userCreationRequest.getCurrencyCode());
        createDefaultAccountForUser(userId, money);

    }

    @Override
    public UserAccountsDto getUserAccounts(String pesel) {
        return accountRepo.getAccounts(pesel);

    }

    private void createDefaultAccountForUser(Long userId, Money money) {
        accountRepo.createAccount(userId, money);
    }

    private void validateBirthDay(String dateOfBirth) {
        LocalDate birthDay = TimeUtils.parseDate(dateOfBirth);
        if (LocalDate.now().minusYears(18).isBefore(birthDay)) {
            throw new BusinessException(BusinessErrorCodes.IS_NOT_ADULT, "User is must by at least 18 years old");
        }
    }


}
