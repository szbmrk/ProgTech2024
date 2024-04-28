package com.progtech.progtech2024.manager;

import com.progtech.progtech2024.database.Account;

public class AccountManager {

    private static AccountManager instance;
    private Account loggedInAccount;

    private AccountManager() {}

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public void setLoggedInAccount(Account account) {
        loggedInAccount = account;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
}
