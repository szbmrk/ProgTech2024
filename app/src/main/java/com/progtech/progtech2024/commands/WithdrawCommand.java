package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;

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
    public void Undo() {
        if (!succeeded)
            return;

        // TODO: 2024. 04. 28. implement deposit to undo
    }

    @Override
    public boolean PostTransaction() throws ExecutionException, InterruptedException {
        Transaction transaction = new Transaction(account.id, "WITHDRAW", amount);
        TransactionRepository repository = BankDatabase.getInstance(context).transactionRepository();

        long newTransactionId = repository.insert(transaction);
        if (newTransactionId < 1)
            return false;

        return true;
    }
}
