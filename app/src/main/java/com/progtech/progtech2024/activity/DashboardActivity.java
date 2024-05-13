package com.progtech.progtech2024.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.progtech.progtech2024.R;
import com.progtech.progtech2024.activity.commands.DepositActivity;
import com.progtech.progtech2024.activity.commands.TransferActivity;
import com.progtech.progtech2024.activity.commands.WithdrawActivity;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.manager.AccountManager;

public class DashboardActivity extends AppCompatActivity {

    TextView usernameText;
    TextView accountNumberText;
    TextView balanceText;
    Button depositBtn;
    Button withdrawBtn;
    Button transferBtn;
    Button accountHistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AsssignTexts();
        AsssignButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsssignTexts();
    }

    public void AsssignTexts() {
        usernameText = findViewById(R.id.currentUsernameTxt);
        accountNumberText = findViewById(R.id.currentAccountNumberTxt);
        balanceText = findViewById(R.id.currentBalance);
        Account loggedIn = AccountManager.getInstance().getLoggedInAccount();
        usernameText.setText(loggedIn.username);
        accountNumberText.setText(loggedIn.accountNumber);
        balanceText.setText(loggedIn.balance);
    }
    public void AsssignButtons() {
        depositBtn = findViewById(R.id.depositBtn);
        withdrawBtn = findViewById(R.id.withdrawBtn);
        transferBtn = findViewById(R.id.transferBtn);
        accountHistoryBtn = findViewById(R.id.accountHistoryBtn);

        depositBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, DepositActivity.class);
                startActivity(intent);
            }
        });
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        });
        transferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });
        accountHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AccountHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}