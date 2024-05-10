package com.progtech.progtech2024.helper;

import androidx.test.platform.app.InstrumentationRegistry;

import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

public class TestRepositoriesHelper {
    static DatabaseManager dbManager = DatabaseManager.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
    public static AccountRepository GetTestAccountRepository() throws Exception {
        return dbManager.GetTestAccountRepository();
    }
    public static TransactionRepository GetTestTransactionRepository() throws Exception {
        return dbManager.GetTestTransactionRepository();
    }

    public static void DeleteDataFromTestRepositories() throws Exception {
        GetTestAccountRepository().DeleteAll();
    }
}
