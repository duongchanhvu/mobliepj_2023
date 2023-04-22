package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;

import android.os.Bundle;

import com.example.quanlychitieu.databinding.ActivityMainBinding;

import bottomnavigation.HomeFrag;
import bottomnavigation.ProfileFrag;
import bottomnavigation.ReportFrag;
import bottomnavigation.TransactionFrag;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFrags(new HomeFrag());

        binding.bottomNavigationView.setBackground(null);

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