package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class DepositCommand extends ABankCommand {

    public DepositCommand(Context context, int amount, Account account) {
        super(context, amount, account);
    }

    @Override
    public void Call() throws ExecutionException, InterruptedException {
        if (super.PostTransaction()) {
            int newBalance = fromAccount.balance + amount;
            fromAccount.ModifyBalance(context, newBalance);
        }
    }

    @Override
    public void TestCall() throws ExecutionException, InterruptedException {
        if (super.TestPostTransaction()) {
            int newBalance = fromAccount.balance + amount;
            fromAccount.TestModifyBalance(context, newBalance);
        }
    }

    @Override
    public void Undo() throws ExecutionException, InterruptedException {
        WithdrawCommand withdrawCommand = new WithdrawCommand(context, amount, fromAccount);
        withdrawCommand.Call();
    }

    @Override
    public void TestUndo() throws ExecutionException, InterruptedException {
        WithdrawCommand withdrawCommand = new WithdrawCommand(context, amount, fromAccount);
        withdrawCommand.TestCall();
    }

    @Override
    protected Transaction BuildTransaction() {
        TransactionBuilder tb = new TransactionBuilder();

        return tb.setFromAccountId(fromAccount.id)
                .setToAccountId(fromAccount.id).setAmount(amount).setTransactionType("DEPOSIT").build();
    }
}
