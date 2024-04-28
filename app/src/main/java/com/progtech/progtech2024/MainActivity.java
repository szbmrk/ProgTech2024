package com.progtech.progtech2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.progtech.progtech2024.commands.WithdrawCommand;
import com.progtech.progtech2024.database.Account;
import com.progtech.progtech2024.database.AccountDao;
import com.progtech.progtech2024.database.AccountRepository;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.manager.AccountManager;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BankDatabase db = BankDatabase.getInstance(this);

        Account account = new Account("test user", "password", 500, true);
        AccountRepository repository = db.accountRepository();

        //EXAMPLE REG LOGIN WITHDRAW

        try {
            long newUserId = repository.register(account).get();

            if (newUserId < 1) {
                Log.d("reg", "success");
            }
            else {
                Log.d("reg", "no success");
            }

            Account loggedIn = repository.login(account.username, account.password).get();
            AccountManager.getInstance(this).setLoggedInAccount(loggedIn);


            WithdrawCommand command = new WithdrawCommand(this, 200, loggedIn);
            command.Call();

            Log.d("withdrawed", loggedIn.toString());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}