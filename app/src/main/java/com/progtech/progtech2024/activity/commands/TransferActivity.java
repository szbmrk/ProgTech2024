package com.progtech.progtech2024.activity.commands;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.progtech.progtech2024.R;
import com.progtech.progtech2024.activity.DashboardActivity;
import com.progtech.progtech2024.commands.DepositCommand;
import com.progtech.progtech2024.commands.TransferCommand;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.manager.AccountManager;
import com.progtech.progtech2024.manager.DatabaseManager;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button transferButton = findViewById(R.id.callTransferBtn);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amountText = findViewById(R.id.transferAmountTxt);
                if (amountText.getText().toString().isEmpty()){
                    Toast.makeText(TransferActivity.this, "Deposit amount shouldn't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Account loggedIn = AccountManager.getInstance().getLoggedInAccount();
                AccountRepository repo = DatabaseManager.getInstance(TransferActivity.this).GetAccountRepository();
                EditText toAccountText = findViewById(R.id.toAccountNumberTxt);
                EditText msgTxt = findViewById(R.id.transferMsgTxt);
                try {
                    Account toAccount = repo.GetAccountByAccountNum(toAccountText.getText().toString());
                    TransferCommand command = new TransferCommand(TransferActivity.this,
                            Integer.parseInt(amountText.getText().toString()), loggedIn, toAccount, msgTxt.getText().toString());
                    command.Call();
                    Intent intent = new Intent(TransferActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(TransferActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}