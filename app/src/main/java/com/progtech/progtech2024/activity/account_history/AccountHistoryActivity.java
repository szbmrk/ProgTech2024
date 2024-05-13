package com.progtech.progtech2024.activity.account_history;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.progtech.progtech2024.R;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.TransactionRepository;
import com.progtech.progtech2024.manager.AccountManager;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AccountHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TransactionRepository repo = DatabaseManager.getInstance(this).GetTransactionRepository();
        ListView transactionListView = findViewById(R.id.transactionHistoryListView);

        try {
            List<Transaction> transactionList = repo.
                    GetTransactionsForAccount(AccountManager.getInstance().getLoggedInAccount().id);
            AccountHistoryAdapter sensorListAdapter = new AccountHistoryAdapter(this, transactionList);
            transactionListView.setAdapter(sensorListAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}