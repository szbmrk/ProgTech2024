package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.exceptions.commands.InsufficientFundsException;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public abstract class ABankCommand {
    protected int amount;
    protected Account fromAccount;
    protected Account toAccount;
    protected Context context;
    protected boolean succeeded;

    protected ABankCommand(Context context, int amount, Account fromAccount, Account toAccount) {
        this.context = context;
        this.amount = amount;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
    }

    protected ABankCommand(Context context, int amount, Account fromAccount) {
        this(context, amount, fromAccount, fromAccount);
    }

    protected abstract void Call() throws Exception;
    protected abstract void Undo() throws Exception;

    protected abstract void TestCall() throws Exception;
    protected abstract void TestUndo() throws Exception;

    protected abstract Transaction BuildTransaction();
    protected boolean PostTransaction() throws ExecutionException, InterruptedException {
        TransactionRepository transactionRepository = DatabaseManager.getInstance(context).GetTransactionRepository();
        succeeded = transactionRepository.PostTransaction(BuildTransaction());
        return succeeded;
    }

    protected boolean TestPostTransaction() throws ExecutionException, InterruptedException {
        TransactionRepository transactionRepository = DatabaseManager.getInstance(context).GetTestTransactionRepository();
        succeeded = transactionRepository.PostTransaction(BuildTransaction());
        return succeeded;
    }
}
