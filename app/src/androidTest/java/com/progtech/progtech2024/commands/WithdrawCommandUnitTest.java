package com.progtech.progtech2024.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.exceptions.commands.InsufficientFundsException;
import com.progtech.progtech2024.helper.DummyAccountCreator;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WithdrawCommandUnitTest {
    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
    }

    @Test
    public void testWithdrawCall_SuccessWithAmount300() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                300, account
        );
        withdrawCommand.TestCall();

        assertEquals(200, account.balance);
        assertEquals(true, withdrawCommand.succeeded);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawCall_FailedWithAmount600_ThrowsInsufficientFundsException() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        WithdrawCommand withdrawCommand = new WithdrawCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                600, account
        );

        withdrawCommand.TestCall();
    }

    @Test
    public void testWithdrawUndo_WithAmount300() throws Exception {
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
