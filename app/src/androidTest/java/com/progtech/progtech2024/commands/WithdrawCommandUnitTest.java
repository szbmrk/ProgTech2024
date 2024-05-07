package com.progtech.progtech2024.commands;

import static org.junit.Assert.assertEquals;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;

public class WithdrawCommandUnitTest {
    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
    }

    @Test
    public void testWithdrawCall_Success() throws Exception {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        accountRepository.Register(account);
        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                300, account
        );
        withdrawCommand.TestCall();

        assertEquals(200, account.balance);
        assertEquals(true, withdrawCommand.succeeded);
    }

    @Test
    public void testWithdrawCall_Failed() throws Exception {
        Account account = new Account(2, "123456", "user2", "pass2", 500, false);
        accountRepository.Register(account);
        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                600, account
        );
        withdrawCommand.TestCall();

        assertEquals(500, account.balance);
        assertEquals(false, withdrawCommand.succeeded);
    }

    @Test
    public void testWithdrawUndo() throws Exception {
        Account account = new Account(3, "1234567", "user3", "pass3", 500, false);
        accountRepository.Register(account);
        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                300, account
        );
        withdrawCommand.TestCall();
        withdrawCommand.TestUndo();

        assertEquals(true, withdrawCommand.succeeded);
        assertEquals(500, account.balance);
    }
}
