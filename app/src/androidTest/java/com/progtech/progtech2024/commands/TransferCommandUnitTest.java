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

public class TransferCommandUnitTest {
    AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
        TestRepositoriesHelper.DeleteDataFromTestRepositories();
    }

    @Test
    public void testTransferCall_SuccessWithAmount300() throws Exception {
        Account account1 = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, true);
        Account account2 = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        TransferCommand transferCommand = new TransferCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                300, account1,account2,"TEST"
        );
        transferCommand.TestCall();

        assertEquals(200, account1.balance);
        assertEquals(800, account2.balance);
        assertEquals(true, transferCommand.succeeded);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testTransferCall_FailedWithAmount600_ThrowsInsufficientFundsException() throws Exception {
        Account account1 = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);
        Account account2 = DummyAccountCreator.CreateDummyAccountAndPostItToDB(800, false);

        TransferCommand transferCommand = new TransferCommand(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                600, account1,account2,"TEST"
        );

        transferCommand.TestCall();
    }
}
