package com.progtech.progtech2024.database.models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.repositories.AccountRepository;

import java.util.concurrent.ExecutionException;

@Entity(tableName = "accounts")
public class Account {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String username;
    @NonNull
    public String password;
    @NonNull
    public int balance;
    @NonNull
    public boolean isJunior;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    public String createdAt;

    public Account(@NonNull String username, @NonNull String password, int balance, boolean isJunior) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.isJunior = isJunior;
    }

    @Ignore
    public boolean ModifyBalance(Context context, int newBalance) throws ExecutionException, InterruptedException {
        AccountRepository repository = BankDatabase.getInstance(context).accountRepository();
        int updatedRows = repository.modifyBalance(id, newBalance).get();

        return updatedRows == 1;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isJunior=" + isJunior +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}

