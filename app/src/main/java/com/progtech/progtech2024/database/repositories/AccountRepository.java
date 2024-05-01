package com.progtech.progtech2024.database.repositories;

import android.content.Context;

import androidx.room.Room;

import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.daos.AccountDao;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccountRepository {
    private final AccountDao accountDao;
    private final ExecutorService executorService;


    public AccountRepository(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public boolean Register(Account account) throws ExecutionException, InterruptedException {
        if (!IsUsernameAvailable(account.username)) {
            return false;
        }

        Future<Long> newId = executorService.submit(() -> accountDao.register(account));
        return newId.get() > 0;
    }

    public Boolean IsUsernameAvailable(String username) throws ExecutionException, InterruptedException {
        Future<Long> isAvailable = executorService.submit(() -> accountDao.isUsernameAvailable(username));
        return isAvailable.get() < 1;
    }

    public Account Login(String username, String password) throws ExecutionException, InterruptedException {
        Future<Account> account = executorService.submit(() -> accountDao.login(username, password));
        return account.get();
    }

    public boolean ModifyBalance(int userId, int newBalance) throws ExecutionException, InterruptedException {
        Future<Integer> updatedRows = executorService.submit(() -> accountDao.modifyBalance(userId, newBalance));
        return updatedRows.get() == 1;
    }
}
