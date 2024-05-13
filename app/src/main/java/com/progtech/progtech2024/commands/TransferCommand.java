package com.progtech.progtech2024.commands;

import android.content.Context;

import com.progtech.progtech2024.builder.TransactionBuilder;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.exceptions.commands.CannotUndoTransferActionException;
import com.progtech.progtech2024.exceptions.commands.InsufficientFundsException;

import java.util.AbstractMap;
import java.util.concurrent.ExecutionException;

public class TransferCommand extends ABankCommand {
    private String msg;
    public TransferCommand(Context context, int amount, Account fromAccount, Account toAccount,String msg){
        super(context, amount, fromAccount,toAccount);
        this.msg = msg;
    }

    @Override
    public void Call() throws ExecutionException, InterruptedException, InsufficientFundsException {
        if(!fromAccount.isJunior)
        {
            if (fromAccount.balance < (amount + (int)(amount * 0.03)) ) {
                succeeded = false;
                throw new InsufficientFundsException();
            }
        }
        if (fromAccount.balance < amount ) {
            succeeded = false;
            throw new InsufficientFundsException();
        }

        if (PostTransaction()) {
            int newBalance = fromAccount.balance - amount;
            if (!fromAccount.isJunior) {
                newBalance = fromAccount.balance - amount - (int) (amount * 0.03);
                WithdrawCommand esetiMegbizas = new WithdrawCommand(context, (int) (amount * 0.03), fromAccount);
                esetiMegbizas.Call();
            }


            succeeded = fromAccount.ModifyBalance(context, newBalance);
            succeeded = toAccount.ModifyBalance(context, toAccount.balance + amount);
        }
    }

    @Override
    public void TestCall() throws ExecutionException, InterruptedException, InsufficientFundsException {
        if(!fromAccount.isJunior)
        {
            if (fromAccount.balance < (amount + (int)(amount * 0.03)) ) {
                succeeded = false;
                throw new InsufficientFundsException();
            }
        }
        if (fromAccount.balance < amount ) {
            succeeded = false;
            throw new InsufficientFundsException();
        }

        if (TestPostTransaction()) {
            int newBalance = fromAccount.balance - amount;
            if(!fromAccount.isJunior)
            {
                newBalance = fromAccount.balance - amount - (int)(amount * 0.03);
                WithdrawCommand esetiMegbizas = new WithdrawCommand(context, (int)(amount*0.03), fromAccount);
                esetiMegbizas.TestCall();
            }


            succeeded = fromAccount.TestModifyBalance(context, newBalance);
            succeeded = toAccount.TestModifyBalance(context, toAccount.balance + amount);
        }
    }

    @Override
    public void Undo() throws CannotUndoTransferActionException {
        throw new CannotUndoTransferActionException();
    }

    @Override
    public void TestUndo() throws CannotUndoTransferActionException {
        throw new CannotUndoTransferActionException();
    }

    @Override
    protected Transaction BuildTransaction() {
        TransactionBuilder tb = new TransactionBuilder();

        return tb.setFromAccountId(fromAccount.id)
                .setToAccountId(toAccount.id).setAmount(amount).setTransactionType("TRANSFER").setMessage(msg).build();
    }
}
