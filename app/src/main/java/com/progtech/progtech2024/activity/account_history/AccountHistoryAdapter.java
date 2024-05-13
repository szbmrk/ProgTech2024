package com.progtech.progtech2024.activity.account_history;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.progtech.progtech2024.R;
import com.progtech.progtech2024.database.models.Account;
import com.progtech.progtech2024.database.models.Transaction;
import com.progtech.progtech2024.database.repositories.AccountRepository;
import com.progtech.progtech2024.exceptions.database.AccountWithIdDoesntExistException;
import com.progtech.progtech2024.manager.DatabaseManager;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AccountHistoryAdapter extends ArrayAdapter<Transaction> {
    private Context mContext;

    public AccountHistoryAdapter(@NonNull Context context, @NonNull List<Transaction> objects) {
        super(context, R.layout.list_item_transaction, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_item_transaction, parent, false);
        }

        Transaction transaction = getItem(position);

        if (transaction != null) {
            TextView fromTextView = convertView.findViewById(R.id.from_account_number);
            TextView toTextView = convertView.findViewById(R.id.to_account_number);
            TextView transactionTypeTextView = convertView.findViewById(R.id.transaction_type);
            TextView amountTextView = convertView.findViewById(R.id.amount);
            TextView messageTextView = convertView.findViewById(R.id.msg);

            AccountRepository repo = DatabaseManager.getInstance(mContext).GetAccountRepository();
            try {
                Account from = repo.GetAccountById(transaction.fromAccountId);
                Account to = repo.GetAccountById(transaction.toAccountId);

                fromTextView.setText("From: " + from.accountNumber);
                if (transaction.transactionType.equals("TRANSFER"))
                    toTextView.setText("To: " + to.accountNumber);
                else
                    toTextView.setText("");
                transactionTypeTextView.setText("Type: " + transaction.transactionType);
                amountTextView.setText("Amount: " + transaction.amount);
                if (transaction.transactionType.equals("TRANSFER"))
                    messageTextView.setText("Message: " + transaction.message);
                else
                    messageTextView.setText("");
            } catch (Exception e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        return convertView;
    }
}
