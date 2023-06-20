package com.example.quanlychitieu;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import bottomnavigation.TransactionFrag;

public class TransactionDetailActivity extends AppCompatActivity {
    DatabaseReference  mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lavender)));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String TransID = getIntent().getStringExtra("TransID");
        String Amount =  getIntent().getStringExtra("amount");
        String Date =  getIntent().getStringExtra("date");
        String Note =  getIntent().getStringExtra("note");
        String Type =  getIntent().getStringExtra("GetPAY");


        TextView AmountTV = findViewById(R.id.Amount);
        TextView DateTV = findViewById(R.id.Date);
        TextView TypeTV = findViewById(R.id.Type);
        TextView NoteTV = findViewById(R.id.Note);
        Button EditBtn = findViewById(R.id.EditBtn);
        Button DeleteBtn = findViewById(R.id.DeleteBtn);

        AmountTV.setText(Amount);
        DateTV.setText(Date);
        TypeTV.setText(Type);
        NoteTV.setText(Note);

        EditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseReference transactionRef = mDatabase.child("Transactions").child(TransID);
                Intent intent = new Intent(TransactionDetailActivity.this, AddTransaction.class);
                intent.putExtra("TransID", TransID);
                intent.putExtra("amount", Amount);
                intent.putExtra("date", Date);
                intent.putExtra("note", Note);
                intent.putExtra("GetPAY", Type);
                startActivity(intent);
            }
        });
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference transactionRef = mDatabase.child("Transactions").child(TransID);
                transactionRef.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(TransactionDetailActivity.this,"Delete Successful",Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(TransactionDetailActivity.this, TransactionFrag.class);
                        //startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle back button click here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
