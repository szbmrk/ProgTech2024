package com.progtech.progtech2024.manager;

import android.content.Context;

import com.progtech.progtech2024.database.models.Account;

public class AccountManager {

    private static AccountManager instance;
    private Account loggedInAccount;
    private Context context;

    private AccountManager(Context context) {
        this.context = context;
    }

    public static AccountManager getInstance(Context context) {
        if (instance == null) {
            instance = new AccountManager(context);
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
