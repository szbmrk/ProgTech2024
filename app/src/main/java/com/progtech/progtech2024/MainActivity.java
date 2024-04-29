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

        Account account = new Account("1234511-123312", "yyyxxay", "aaaa", 500, true);
        AccountRepository repository = db.accountRepository();

        //EXAMPLE REG LOGIN WITHDRAW
        try {
            long newUserId = repository.register(account);

            if (newUserId > 0) {
                Log.d("reg", "success");
            }
            else {
                Log.d("reg", "no success");
            }

            Account loggedIn = repository.login(account.username, account.password);
            AccountManager.getInstance(this).setLoggedInAccount(loggedIn);

            WithdrawCommand command = new WithdrawCommand(this, 200, loggedIn);
            command.Call();
            command.Undo();

            DepositCommand depositCommand = new DepositCommand(this, 500, loggedIn);
            depositCommand.Call();

            command.Call();

            Log.d("withdrawed", AccountManager.getInstance(this).getLoggedInAccount().toString());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}