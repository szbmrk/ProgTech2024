package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class WithdrawCommand implements IBankCommand {
    private int amount;
    protected Account account;
    private Context context;
    private boolean succeeded;

    public WithdrawCommand(Context context, int amount, Account account){
        this.context = context;
        this.amount = amount;
        this.account = account;
    }

    @Override
    public void Call() throws ExecutionException, InterruptedException {
        if (account.balance < amount) {
            succeeded = false;
            return;
        }

        succeeded = PostTransaction();
        if (!succeeded) return;

        int newBalance = account.balance - amount;
        succeeded = account.ModifyBalance(context, newBalance);
        if (!succeeded) return;

        succeeded = true;
    }

    @Override
    public void Undo() throws ExecutionException, InterruptedException {
        if (!succeeded)
            return;

        DepositCommand depositCommand = new DepositCommand(context, amount, account);
        depositCommand.Call();
    }

    @Override
    public boolean PostTransaction() throws ExecutionException, InterruptedException {
        TransactionBuilder tb = new TransactionBuilder();
        Transaction transaction = tb.setFromAccountId(account.id)
                .setToAccountId(account.id).setAmount(amount).setTransactionType("WITHDRAW").build();

        DatabaseManager dbManager = DatabaseManager.getInstance(context);

        return dbManager.PostTransaction(transaction);
    }
}
