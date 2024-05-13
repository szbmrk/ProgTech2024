package com.progtech.progtech2024.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.progtech.progtech2024.R;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.manager.DatabaseManager;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Switch isJuniorSwitch;
    Button generateAccountNumButton;
    TextView accountNumberToShow;
    Button btnToLogin;
    Button btnRegister;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AssignButtons();

    }
    public void AssignButtons()
    {
        generateAccountNumButton = findViewById(R.id.generateAccountNumberBtn);
        generateAccountNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccountRepository repo = DatabaseManager.getInstance(RegisterActivity.this).GetAccountRepository();
                while(true)
                {
                    String accountNumber = generateRandomString(11);
                    accountNumberToShow = findViewById(R.id.accountNumText);
                    accountNumberToShow.setText(accountNumber);
                    try {
                        if (null == repo.GetAccountByAccountNum(accountNumber)) break;
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        });
        btnToLogin = findViewById(R.id.goToLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister = findViewById(R.id.registerBtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MakeAccountWithGivenParameters();
                    AccountRepository repo = DatabaseManager.getInstance(RegisterActivity.this).GetAccountRepository();
                    repo.Register(account);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String alphabet = "-0123456789";  // You can modify this string to include desired characters

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    public void MakeAccountWithGivenParameters() throws Exception {

        usernameText = findViewById(R.id.usernameField);
        passwordText = findViewById(R.id.passwordField);
        isJuniorSwitch = findViewById(R.id.isJunior);

        if(usernameText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){
            throw new Exception("You must fill all the fields!");
        }
        if(accountNumberToShow.getText().toString().isEmpty() || accountNumberToShow.getText().toString().equals("Example Account Number"))
        {
            throw new Exception("generate account number first!");
        }
        account = new Account(accountNumberToShow.getText().toString(),usernameText.getText().toString(), passwordText.getText().toString(),0, isJuniorSwitch.isChecked());
    }
}