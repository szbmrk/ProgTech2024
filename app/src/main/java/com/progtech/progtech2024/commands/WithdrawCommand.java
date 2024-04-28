package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.database.Account;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.Transaction;
import com.progtech.progtech2024.database.TransactionDao;

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
    public void Call() {
        if (account.balance < amount) {
            succeeded = false;
            return;
        }
        succeeded = PostTransaction();

        if (!succeeded) return;

        account.balance += amount;

        succeeded = true;
    }

    @Override
    public void Undo() {
        if (!succeeded)
            return;

        // TODO: 2024. 04. 28. implement deposit to undo
    }

    @Override
    public boolean PostTransaction() {
        Transaction transaction = new Transaction(account.id, "WITHDRAW", amount);
        TransactionDao transactionDao = BankDatabase.getInstance(context).transactionDao();

        long newTransactionId = transactionDao.insert(transaction);
        if (newTransactionId < 1)
            return false;

        return true;
    }
}
