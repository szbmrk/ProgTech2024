package com.progtech.progtech2024.activity.auth;

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
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.manager.AccountManager;
import com.progtech.progtech2024.manager.DatabaseManager;

public class LoginActivity extends AppCompatActivity {
    EditText usernameText;
    EditText passwordText;
    Button btnToRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usernameText = findViewById(R.id.usernameField);
        passwordText = findViewById(R.id.passwordField);
        AssignButtons();
    }

    public void AssignButtons()
    {
        btnToRegister = findViewById(R.id.goToLogin);
        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = findViewById(R.id.registerBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AccountRepository repo = DatabaseManager.getInstance(LoginActivity.this).GetAccountRepository();
                    Account account = repo.Login(usernameText.getText().toString(), passwordText.getText().toString());
                    AccountManager.getInstance().setLoggedInAccount(account);
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG);
                }
            }
        });
    }
}