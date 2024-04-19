package com.progtech.progtech2024.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accounts")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private int balance;
    @NonNull
    private boolean isJunior;
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private String createdAt;

    public Account(@NonNull String username, @NonNull String password, int balance, boolean isJunior) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.isJunior = isJunior;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isJunior() {
        return isJunior;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

