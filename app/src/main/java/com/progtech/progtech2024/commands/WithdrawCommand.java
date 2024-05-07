package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.exceptions.commands.InsufficientFundsException;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class WithdrawCommand extends ABankCommand {

    public WithdrawCommand(Context context, int amount, Account account){
        super(context, amount, account);
    }

    @Override
    public void Call() throws ExecutionException, InterruptedException, InsufficientFundsException {
        if (fromAccount.balance < amount) {
            succeeded = false;
            throw new InsufficientFundsException();
        }

        if (PostTransaction()) {
            int newBalance = fromAccount.balance - amount;
            succeeded = fromAccount.ModifyBalance(context, newBalance);
        }
    }

    @Override
    public void TestCall() throws ExecutionException, InterruptedException, InsufficientFundsException {
        if (fromAccount.balance < amount) {
            succeeded = false;
            throw new InsufficientFundsException();
        }

        if (TestPostTransaction()) {
            int newBalance = fromAccount.balance - amount;
            succeeded = fromAccount.TestModifyBalance(context, newBalance);
        }
    }

    @Override
    public void Undo() throws ExecutionException, InterruptedException {
        if (!succeeded)
            return;

        DepositCommand depositCommand = new DepositCommand(context, amount, fromAccount);
        depositCommand.Call();
    }

    @Override
    public void TestUndo() throws ExecutionException, InterruptedException {
        if (!succeeded)
            return;

        DepositCommand depositCommand = new DepositCommand(context, amount, fromAccount);
        depositCommand.TestCall();
    }

    @Override
    protected Transaction BuildTransaction() {
        TransactionBuilder tb = new TransactionBuilder();

        return tb.setFromAccountId(fromAccount.id)
                .setToAccountId(fromAccount.id).setAmount(amount).setTransactionType("WITHDRAW").build();
    }
}
