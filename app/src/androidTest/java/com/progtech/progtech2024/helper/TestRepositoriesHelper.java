package com.progtech.progtech2024.helper;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

public class TestRepositoriesHelper {
    static DatabaseManager dbManager = DatabaseManager.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
    public static AccountRepository GetTestAccountRepository() throws Exception {
        AccountRepository accountRepository = dbManager.GetTestAccountRepository();
        accountRepository.DeleteAll();
        return accountRepository;
    }

    public static TransactionRepository GetTestTransactionRepository() throws Exception {
        TransactionRepository transactionRepository = dbManager.GetTestTransactionRepository();
        dbManager.GetTestAccountRepository().DeleteAll();
        return transactionRepository;
    }
}
