package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import objects.Transaction;
import objects.User;

public class AddTransaction extends AppCompatActivity {
    String TransIDEdit;
    String amountEdit;
    String dateEdit;
    String noteEdit;
    String TypeEdit;

    EditText groupOfTrans;
    EditText amount;
    EditText note;
    EditText transactionDate, wallet;
    Calendar calendar;

    String isPaying;

    User user;

    FirebaseUser currentUser;
    DatabaseReference  mDatabase;

    String[] options = {
            "Pay",
            "Earn"
    };

    String[] walletList = {
            "Cash" // Can add more later
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));

        amount = findViewById(R.id.transactionAmount);
        groupOfTrans = findViewById(R.id.groupOfTransaction);
        note = findViewById(R.id.transNote);
        transactionDate = findViewById(R.id.dateOfTransaction);
//        wallet = findViewById(R.id.pickWallet);
        calendar = Calendar.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

            TransIDEdit=getIntent().getStringExtra("TransID");
            amountEdit= getIntent().getStringExtra("amount");
            dateEdit=getIntent().getStringExtra("date");
            noteEdit=getIntent().getStringExtra("note");
            TypeEdit=getIntent().getStringExtra("GetPAY");
            amount.setText(amountEdit);
            groupOfTrans.setText(TypeEdit);
            note.setText(noteEdit);
            transactionDate.setText(dateEdit);

//        wallet.setText("Cash");

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = Objects.requireNonNull(currentUser). getEmail();
        String userID = currentUser.getUid();
        user = new User(email, userID);

//        wallet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showWalletOptionDialog();
//            }
//        });


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
                saveTransaction();
//                showUserTransactionData();
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

//    public void showWalletOptionDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Select wallet")
//                .setItems(walletList, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String selectedWallet = walletList[which];
//                        wallet.setText(selectedWallet);
//                        dialog.dismiss();
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }

    public void saveTransaction() {
        String transAmountString = amount.getText().toString();
        double transAmount = Double.parseDouble(transAmountString);
        boolean isPay = groupOfTrans.getText().toString().equals("Pay");
        String transNote = note.getText().toString();
        String transDate = String.valueOf(transactionDate.getText());
        String userID = user.userID;

        Map<String, Object> transactionData = new HashMap<>();
        transactionData.put("Amount", transAmount);
        transactionData.put("IsPay", isPay);
        transactionData.put("Note", transNote);
        transactionData.put("Date", transDate);
        transactionData.put("UserID", userID);
        DatabaseReference transactionRef;

        if(getIntent().getStringExtra("TransID") == null)
        {transactionRef = mDatabase.child("Transactions").push();}
        else
        {transactionRef =mDatabase.child("Transactions").child(TransIDEdit);}
//        Transaction transaction = new Transaction(transAmount, isPay, transNote, transDate, userID);
        transactionRef.setValue(transactionData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {
                    // Transaction data saved successfully
                    Toast.makeText(AddTransaction.this, "Transaction saved successfully", Toast.LENGTH_SHORT).show();

                    // Update the balance in the user table based on the transaction type
                    DatabaseReference userRef = mDatabase.child("Users").child(userID);
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Retrieve the current balance value
                            Long currentBalance = dataSnapshot.child("balance").getValue(Long.class);
                            if (currentBalance != null) {
                                double currentBalanceValue = currentBalance.doubleValue();

                                // Calculate the new balance based on the transaction type
                                double transactionAmount = transAmount;
                                double newBalance = isPay ? currentBalanceValue - transactionAmount : currentBalanceValue + transactionAmount;

                                // Update the balance field in the user table
                                userRef.child("balance").setValue(newBalance);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle the error condition, if any
                            Toast.makeText(AddTransaction.this, "Failed to update balance: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Failed to save transaction data
                    Toast.makeText(AddTransaction.this, "Failed to save transaction: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    public void showUserTransactionData() {
        DatabaseReference transactionRef = mDatabase.child("Transactions");



        Query query = transactionRef.orderByChild("UserID").equalTo(currentUser.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot transactionSnapshot : dataSnapshot.getChildren()) {
                    Double amount = transactionSnapshot.child("Amount").getValue(Double.class);
                    String group = transactionSnapshot.child("GroupOfTransaction").getValue(String.class);
                    String note = transactionSnapshot.child("Note").getValue(String.class);
                    String date = transactionSnapshot.child("Date").getValue(String.class);
                    String userID = transactionSnapshot.child("UserID").getValue(String.class);

                    // Print the retrieved data to the console
                    System.out.println("Amount: " + amount);
                    System.out.println("Group: " + group);
                    System.out.println("Note: " + note);
                    System.out.println("Date: " + date);
                    System.out.println("UserID: " + userID);
                    System.out.println("-------------------------");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error condition, if any
                System.out.println("Failed to read data: " + databaseError.getMessage());
            }
        });

    }

}