package com.szachnowicz.exchange.domian.account;

import com.szachnowicz.exchange.dto.UserCreationRequest;
import com.szachnowicz.exchange.dto.error.BusinessErrorCodes;
import com.szachnowicz.exchange.dto.error.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountAdapterTest {

    private AccountPort accountPort;

    @BeforeEach
    void setUp() {
        accountPort = new AccountAdapter(null, null);
    }

    @Test
    void testOfNotAdultUserCreation() {
        UserCreationRequest request = UserCreationRequest.builder()
                .dateOfBirth("2020-01-01")
                .name("Anon")
                .pesel("123123123123")
                .lastName("Anonowicz")
                .build();

        BusinessException businessException = assertThrows(BusinessException.class, () -> accountPort.createUser(request));
        assertEquals(businessException.getErrorCode(), BusinessErrorCodes.IS_NOT_ADULT);
    }

    @Test
    void testOfAdultUserCreation() {
        UserCreationRequest request = UserCreationRequest.builder()
                .dateOfBirth("1999-01-01")
                .name("Anon")
                .pesel("123123123123")
                .lastName("Anonowicz")
                .build();
        accountPort.createUser(request);
    }
}