package com.progtech.progtech2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.progtech.progtech2024.commands.DepositCommand;
import com.progtech.progtech2024.commands.WithdrawCommand;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.manager.AccountManager;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager db = DatabaseManager.getInstance(this);

        Account account = new Account("123123", "a", "w", 500, true);
        AccountRepository repository = DatabaseManager.getInstance(this).GetAccountRepository();

        //EXAMPLE REG LOGIN WITHDRAW
        try {
            boolean succ = repository.Register(account);
            Log.d("succ", succ ? "succ" : "aaaa");


            Account loggedIn = repository.Login(account.username, account.password);
            AccountManager.getInstance().setLoggedInAccount(loggedIn);

            if (loggedIn != null) {
                WithdrawCommand command = new WithdrawCommand(this, 200, loggedIn);
                command.Call();
                command.Undo();
                DepositCommand depositCommand = new DepositCommand(this, 500, loggedIn);
                depositCommand.Call();
                command.Call();
                Log.d("withdrawed", AccountManager.getInstance().getLoggedInAccount().toString());
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}