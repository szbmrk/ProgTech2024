package com.progtech.progtech2024.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
}

