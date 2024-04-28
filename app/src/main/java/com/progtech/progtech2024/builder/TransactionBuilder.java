package com.progtech.progtech2024.builder;

import androidx.annotation.NonNull;

import com.progtech.progtech2024.database.models.Transaction;

public class TransactionBuilder {

    private String message;
    private String fromAccountId;
    private String toAccountId;
    private String transactionType;
    private int amount;

    public TransactionBuilder() {
    }

    public TransactionBuilder setFromAccountId(String id) {
        this.fromAccountId = id;
        return this;
    }

    public TransactionBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public TransactionBuilder setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public TransactionBuilder setAmount(int amount) {
        this.amount = amount;
        return  this;
    }

    public Transaction build() {
        return new Transaction(message, fromAccountId, toAccountId, transactionType, amount);
    }
}
