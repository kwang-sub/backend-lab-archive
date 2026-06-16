package org.example.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void testTransferOk() {
        // given
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        MockAccountManager mockAccountManager = new MockAccountManager();
        mockAccountManager.addAccount("1", senderAccount);
        mockAccountManager.addAccount("2", beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

        // when
        accountService.transfer("1", "2", 50);

        // then
        assertThat(senderAccount.getBalance()).isEqualTo(150);
        assertThat(beneficiaryAccount.getBalance()).isEqualTo(150);
    }

}