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
import com.progtech.progtech2024.commands.WithdrawCommand;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.manager.AccountManager;

public class WithdrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_withdraw);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button withdrawBtn = findViewById(R.id.callWithdrawBtn);

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amountText = findViewById(R.id.withdrawAmountTxt);
                if (amountText.getText().toString().isEmpty()){
                    Toast.makeText(WithdrawActivity.this, "Withdraw amount shouldn't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Account loggedIn = AccountManager.getInstance().getLoggedInAccount();
                WithdrawCommand command = new WithdrawCommand(WithdrawActivity.this,
                        Integer.parseInt(amountText.getText().toString()), loggedIn);
                try {
                    command.Call();
                    Intent intent = new Intent(WithdrawActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(WithdrawActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}