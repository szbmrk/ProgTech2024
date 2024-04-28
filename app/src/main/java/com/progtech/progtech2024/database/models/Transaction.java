package com.progtech.progtech2024.database.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "transactions", foreignKeys = { @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "fromAccountId",
        onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Account.class,
        parentColumns = "id",
        childColumns = "toAccountId",
        onDelete = ForeignKey.CASCADE)})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String message;

    @NonNull
    public int fromAccountId;

    public int toAccountId;

    @NonNull
    public String transactionType;

    @NonNull
    public int amount;

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    public String createdAt;

    public Transaction(String message, @NonNull int fromAccountId, int toAccountId, @NonNull String transactionType, @NonNull int amount) {
        this.message = message;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Ignore
    public Transaction(@NonNull int fromAccountId, @NonNull String transactionType, @NonNull int amount) {
        this(null, fromAccountId, fromAccountId, transactionType, amount);
    }
}