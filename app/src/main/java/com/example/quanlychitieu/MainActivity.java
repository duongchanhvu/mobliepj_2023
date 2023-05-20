package com.example.quanlychitieu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.quanlychitieu.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import bottomnavigation.HomeFrag;
import bottomnavigation.ProfileFrag;
import bottomnavigation.ReportFrag;
import bottomnavigation.TransactionFrag;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FloatingActionButton addTrans;
    View view;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFrags(new HomeFrag());
        binding.bottomNavigationView.setBackground(null);
//        addTrans = view.findViewById(R.id.add_transaction_btn);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    replaceFrags(new HomeFrag());
                    break;

                case R.id.transaction:
                    replaceFrags(new TransactionFrag());
                    break;
                case R.id.report:
                    replaceFrags(new ReportFrag());
                    break;

                case R.id.profile:
                    replaceFrags(new ProfileFrag());
                    break;
            }

            return true;
        });
    }

    private void replaceFrags(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}