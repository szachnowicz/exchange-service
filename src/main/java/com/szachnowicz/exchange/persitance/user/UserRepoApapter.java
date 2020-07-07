package com.szachnowicz.exchange.persitance.user;

import com.szachnowicz.exchange.dto.UserAccountsDto;
import com.szachnowicz.exchange.dto.UserCreationRequest;
import com.szachnowicz.exchange.dto.error.BusinessException;
import com.szachnowicz.exchange.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.ACCOUNT_NOT_EXIST;
import static com.szachnowicz.exchange.dto.error.BusinessErrorCodes.PESEL_EXIST;


@Service
@RequiredArgsConstructor
class UserRepoApapter implements UserRepo {

    private final UserJpaRepo userJpaRepo;

    @Override
    @Transactional
    public Long saveUser(UserCreationRequest userCreationRequest) {
        validateIfPeselExist(userCreationRequest.getPesel());

        UserEntity entity = new UserEntity();
        entity.setName(userCreationRequest.getName());
        entity.setLastName(userCreationRequest.getLastName());
        entity.setPesel(userCreationRequest.getPesel());
        entity.setBirthDay(TimeUtils.parseDate(userCreationRequest.getDateOfBirth()));
        return userJpaRepo.save(entity).getId();
    }

    @Override
    public UserAccountsDto getUser(String pesel) {
        Optional<UserEntity> optional = userJpaRepo.findByPesel(pesel);
        UserEntity entity = optional.orElseThrow(()
                -> new BusinessException(ACCOUNT_NOT_EXIST, "User with given pesel not exist."));

        return UserAccountsDto
                .builder()
                .userId(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .pesel(entity.getPesel())
                .birthDay(entity.getBirthDay())
                .build();
    }

    private void validateIfPeselExist(String pesel) {
        if (userJpaRepo.findByPesel(pesel).isPresent()) {
            throw new BusinessException(PESEL_EXIST, "User with given pesel already exist.");
        }
    }
}
