package com.progtech.progtech2024.helper;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;

public class DummyAccountCreator {
    static int id = 1;
    public static Account CreateDummyAccountAndPostItToDB(int balance, boolean isJunior) throws Exception {
        Account account = new Account(id, "12345" + id, "user" + id, "pass" + id, balance, isJunior);
        AccountRepository accountRepository = TestRepositoriesHelper.GetTestAccountRepository();
        accountRepository.Register(account);
        id++;
        return account;
    }
}
