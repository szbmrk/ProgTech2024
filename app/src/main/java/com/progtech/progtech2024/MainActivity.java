package com.progtech.progtech2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.progtech.progtech2024.commands.DepositCommand;
import com.progtech.progtech2024.commands.WithdrawCommand;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.database.BankDatabase;
import com.progtech.progtech2024.exceptions.database.FailedQueryException;
import com.progtech.progtech2024.exceptions.database.InvalidUsernameOrPasswordException;
import com.progtech.progtech2024.exceptions.database.UserAlreadyTakenException;
import com.progtech.progtech2024.manager.AccountManager;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager db = DatabaseManager.getInstance(this);

        Account account = new Account("123123", "aaaaaaaaaa", "waaaaaaaaaaaa", 500, true);
        AccountRepository repository = DatabaseManager.getInstance(this).GetAccountRepository();

        //EXAMPLE REG LOGIN WITHDRAW
        try {

            try {
                repository.Register(account);
            } catch (FailedQueryException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (UserAlreadyTakenException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
            }

            try {
                Account loggedIn = repository.Login(account.username, account.password);
                AccountManager.getInstance().setLoggedInAccount(loggedIn);
                WithdrawCommand command = new WithdrawCommand(this, 200, loggedIn);
                command.Call();
                command.Undo();
                DepositCommand depositCommand = new DepositCommand(this, 500, loggedIn);
                depositCommand.Call();
                command.Call();
                Log.d("withdrawed", AccountManager.getInstance().getLoggedInAccount().toString());
            } catch (InvalidUsernameOrPasswordException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}