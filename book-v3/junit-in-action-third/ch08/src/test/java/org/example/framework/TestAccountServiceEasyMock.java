package org.example.framework;

import org.assertj.core.api.Assertions;
import org.easymock.EasyMock;
import org.example.account.Account;
import org.example.account.AccountManager;
import org.example.account.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

public class TestAccountServiceEasyMock {
    private AccountManager mockAccountManager;

    @BeforeEach
    void setUp() {
        mockAccountManager = createMock("mockAccountManager",AccountManager.class);
    }

    @Test
    void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertThat(senderAccount.getBalance()).isEqualTo(150);
        assertThat(beneficiaryAccount.getBalance()).isEqualTo(150);
    }

    @AfterEach
    void tearDown() {
        verify(mockAccountManager);
    }
}
