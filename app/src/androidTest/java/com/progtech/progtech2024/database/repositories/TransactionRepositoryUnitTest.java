package com.progtech.progtech2024.database.repositories;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.helper.DummyAccountCreator;
import com.progtech.progtech2024.helper.TestRepositoriesHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TransactionRepositoryUnitTest {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Before
    public void SetUp() throws Exception {
        accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
        transactionRepository = TestRepositoriesHelper.GetTestTransactionRepository();
        TestRepositoriesHelper.DeleteDataFromTestRepositories();
    }

    @Test
    public void testPostTransaction_Deposit_WithAmount500() throws Exception {
        Account account = DummyAccountCreator.CreateDummyAccountAndPostItToDB(500, false);

        TransactionBuilder tb = new TransactionBuilder();
        Transaction transaction = tb
                .setToAccountId(account.id).setFromAccountId(account.id)
                .setTransactionType("DEPOSIT").setAmount(500).build();
        boolean success = transactionRepository.PostTransaction(transaction);
        assertEquals(true, success);
    }
}
