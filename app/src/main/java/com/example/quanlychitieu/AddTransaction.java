package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTransaction extends AppCompatActivity {

    EditText groupOfTrans;
    EditText transactionDate, wallet;
    Calendar calendar;

    String[] options = {
            "Eating",
            "Transporting",
            "Renting",
            "Electricity bill",
            "Water bill",
            "Internet bill",
            "Gas",
            "Others"
    };

    String[] walletList = {
            "Cash",
            "Momo",
            "ZaloPay",
            "BIDV",
            "Others",
            "Cash",
            "Momo",
            "ZaloPay",
            "BIDV",
            "Others",
            "Cash",
            "Momo",
            "ZaloPay",
            "BIDV",
            "Others",
            "Cash",
            "Momo",
            "ZaloPay",
            "BIDV",
            "Others"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        groupOfTrans = findViewById(R.id.groupOfTransaction);
        transactionDate = findViewById(R.id.dateOfTransaction);
        wallet = findViewById(R.id.pickWallet);
        calendar = Calendar.getInstance();

        wallet.setText("Cash");

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWalletOptionDialog();
            }
        });


        //set transactionDate = today by default
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());
        transactionDate.setText(currentDate);

        transactionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        groupOfTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle back button click here
                finish();
                return true;
            case R.id.action_save:
//                saveInformation(); new method to call when saving the data, haven't handled
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showOptionsDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select group of transaction")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedOption = options[which];
                        groupOfTrans.setText(selectedOption);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showDateTimePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String selectedDate = dateFormat.format(calendar.getTime());
                transactionDate.setText(selectedDate);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void showWalletOptionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select wallet")
                .setItems(walletList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedWallet = walletList[which];
                        wallet.setText(selectedWallet);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}