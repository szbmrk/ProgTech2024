package com.progtech.progtech2024.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.helper.DummyAccountCreator;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;

public class DepositCommandUnitTest {
    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
        TestRepositoriesHelper.DeleteDataFromTestRepositories();
    }

    @Test
    public void testDepositCall_WithAmount500Success() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);
        DepositCommand depositCommand = new DepositCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                500, account
        );
        depositCommand.TestCall();

        assertEquals(1000, account.balance);
        assertTrue(depositCommand.succeeded);
    }

    @Test
    public void testDepositUndo_WithAmount500Success() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);
        DepositCommand depositCommand = new DepositCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                500, account
        );
        depositCommand.TestCall();
        depositCommand.TestUndo();

        assertTrue(depositCommand.succeeded);
        assertEquals(500, account.balance);
    }
}
