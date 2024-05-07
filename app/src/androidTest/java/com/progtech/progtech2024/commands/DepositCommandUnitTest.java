package com.progtech.progtech2024.commands;

import static org.junit.Assert.assertEquals;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;

public class DepositCommandUnitTest {
    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
    }

    @Test
    public void testDepositCall() throws Exception {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        accountRepository.Register(account);
        DepositCommand depositCommand = new DepositCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                500, account
        );
        depositCommand.TestCall();

        assertEquals(1000, account.balance);
        assertEquals(true, depositCommand.succeeded);
    }

    @Test
    public void testDepositUndo() throws Exception {
        Account account = new Account(2, "123456", "user2", "pass2", 500, false);
        accountRepository.Register(account);
        DepositCommand depositCommand = new DepositCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                500, account
        );
        depositCommand.TestCall();
        depositCommand.TestUndo();

        assertEquals(true, depositCommand.succeeded);
        assertEquals(500, account.balance);
    }
}
