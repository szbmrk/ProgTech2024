package com.progtech.progtech2024.database.repositories;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.manager.DatabaseManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class TransactionRepositoryUnitTest {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        DatabaseManager dbManager = DatabaseManager.getInstance
                (InstrumentationRegistry.getInstrumentation().getTargetContext());

        accountRepository = dbManager.GetAccountRepository();
        transactionRepository = dbManager.GetTransactionRepository();

        accountRepository.DeleteAll();
    }

    @Test
    public void testPostTransaction() throws Exception {
        Account account = new Account(1, "12345", "user1", "pass1", 500, false);
        accountRepository.Register(account);

        TransactionBuilder tb = new TransactionBuilder();
        Transaction transaction = tb
                .setToAccountId(1).setFromAccountId(1)
                .setTransactionType("DEPOSIT").setAmount(500).build();
        boolean success = transactionRepository.PostTransaction(transaction);
        assertEquals(true, success);
    }
}
