package com.progtech.progtech2024.database.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.progtech.progtech2024.helper.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        this.createdAt = DateFormatter.DateToString(new Date());
    }

    @Ignore
    public Transaction(int id, String message, int fromAccountId, int toAccountId, @NonNull String transactionType, int amount) {
        this.id = id;
        this.message = message;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.createdAt = DateFormatter.DateToString(new Date());
    }

    @Ignore @Override
    public String toString() {
        return "Transaction:" + transactionType +
                "\nDate of transaction: " + createdAt +
                "\nUsed account number: " + fromAccountId +
                "\nTransaction amount: " + amount +
                "\nAccount where the amount was sent: " + toAccountId +
                "-------------------------------------";
    }
    @Ignore @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Transaction)) return false;

        Transaction other = (Transaction)obj;

        return this.id == other.id;
    }
}