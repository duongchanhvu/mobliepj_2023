package bottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quanlychitieu.LoginActivity;
import com.example.quanlychitieu.MyWallet;
import com.example.quanlychitieu.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class ProfileFrag extends Fragment {

    TextView logOut, myWalletLink;
    FirebaseAuth mAuth;

    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_profile, container, false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myWalletLink = view.findViewById(R.id.manageWallet);

        logOut = view.findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                getActivity().finish();

                Toast.makeText(getActivity(), "You have been signed out.", Toast.LENGTH_SHORT).show();
            }
        });

        myWalletLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWallet.class);
                startActivity(intent);

//                getActivity().finish();
            }
        });
    }

}