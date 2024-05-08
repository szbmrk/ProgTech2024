package com.progtech.progtech2024.commands;

import static org.junit.Assert.assertEquals;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.exceptions.commands.InsufficientFundsException;
import com.progtech.progtech2024.helper.DummyAccountCreator;
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
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

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
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                600, account
        );

        try {
            withdrawCommand.TestCall();
        }
        catch (Exception e) {
            assertEquals(true, e instanceof InsufficientFundsException);
        }

        assertEquals(500, account.balance);
        assertEquals(false, withdrawCommand.succeeded);
    }

    @Test
    public void testWithdrawUndo() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

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
