package com.progtech.progtech2024.builder;

import androidx.annotation.NonNull;

import com.progtech.progtech2024.database.models.Transaction;

public class TransactionBuilder {

    private String message;
    private int fromAccountId;
    private int toAccountId;
    private String transactionType;
    private int amount;

    public TransactionBuilder() {
    }
    public TransactionBuilder setFromAccountId(int id) {
        this.fromAccountId = id;
        return this;
    }

    public TransactionBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public TransactionBuilder setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public TransactionBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public  TransactionBuilder setTransactionType(String type) {
        this.transactionType = type;
        return this;
    }

    public Transaction build() {
        return new Transaction(message, fromAccountId, toAccountId, transactionType, amount);
    }
}
