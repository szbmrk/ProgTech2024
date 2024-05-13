package com.progtech.progtech2024.database.repositories;

import android.content.Context;

import androidx.room.Room;

import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.daos.AccountDao;
import com.progtech.progtech2024.exceptions.database.AccountNumDoesntExistException;
import com.progtech.progtech2024.exceptions.database.FailedQueryException;
import com.progtech.progtech2024.exceptions.database.InvalidUsernameOrPasswordException;
import com.progtech.progtech2024.exceptions.database.UserAlreadyTakenException;
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

    public long Register(Account account) throws ExecutionException, InterruptedException, UserAlreadyTakenException, FailedQueryException {
        if (!IsUsernameAvailable(account.username)) {
            throw new UserAlreadyTakenException();
        }

        Future<Long> newId = executorService.submit(() -> accountDao.register(account));

        if (newId.get() < 0) {
            throw new FailedQueryException("Register failed!");
        }

        return newId.get();
    }

    public Boolean IsUsernameAvailable(String username) throws ExecutionException, InterruptedException {
        Future<Long> isAvailable = executorService.submit(() -> accountDao.isUsernameAvailable(username));
        return isAvailable.get() < 1;
    }

    public Account Login(String username, String password) throws ExecutionException, InterruptedException, InvalidUsernameOrPasswordException {
        Future<Account> account = executorService.submit(() -> accountDao.login(username, password));

        if (account.get() == null) {
            throw new InvalidUsernameOrPasswordException();
        }

        return account.get();
    }
    public Account GetAccountByAccountNum(String accountNum) throws ExecutionException, InterruptedException, AccountNumDoesntExistException {
        Future<Account> account = executorService.submit(() -> accountDao.getAccountByAccountNum(accountNum));

        if (account.get() == null) {
            throw new AccountNumDoesntExistException();
        }

        return account.get();
    }

    public boolean ModifyBalance(int userId, int newBalance) throws ExecutionException, InterruptedException {
        Future<Integer> updatedRows = executorService.submit(() -> accountDao.modifyBalance(userId, newBalance));
        return updatedRows.get() == 1;
    }

    public int DeleteAll() throws ExecutionException, InterruptedException {
        Future<Integer> deletedRows = executorService.submit(() -> accountDao.deleteAll());
        return deletedRows.get();
    }
}
