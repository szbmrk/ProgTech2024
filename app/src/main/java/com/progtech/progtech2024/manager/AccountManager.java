package com.progtech.progtech2024.manager;

import android.app.Application;
import android.content.Context;

import com.progtech.progtech2024.database.Account;
import com.progtech.progtech2024.database.AccountDao;
import com.progtech.progtech2024.database.BankDatabase;

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
