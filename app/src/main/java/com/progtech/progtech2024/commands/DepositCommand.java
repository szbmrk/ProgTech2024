package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class DepositCommand implements IBankCommand {
    private int amount;
    protected Account account;
    private Context context;

    public DepositCommand(Context context, int amount, Account account){
        this.context = context;
        this.amount = amount;
        this.account = account;
    }

    @Override
    public void Call() throws ExecutionException, InterruptedException {
        if (PostTransaction()) {
            int newBalance = account.balance + amount;
            account.ModifyBalance(context, newBalance);
        }
    }

    @Override
    public void Undo() throws ExecutionException, InterruptedException {
        WithdrawCommand withdrawCommand = new WithdrawCommand(context, amount, account);
        withdrawCommand.Call();
    }

    @Override
    public boolean PostTransaction() throws ExecutionException, InterruptedException {
        TransactionBuilder tb = new TransactionBuilder();
        Transaction transaction = tb.setFromAccountId(account.id)
                .setToAccountId(account.id).setAmount(amount).setTransactionType("DEPOSIT").build();

        DatabaseManager dbManager = DatabaseManager.getInstance(context);

        return dbManager.PostTransaction(transaction);
    }
}
