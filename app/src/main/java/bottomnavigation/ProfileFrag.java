package bottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlychitieu.LoginActivity;
import com.example.quanlychitieu.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFrag extends Fragment {

    TextView logOut;
    FirebaseAuth mAuth;

    Button logOutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logOut = view.findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFrag profileFrag = new ProfileFrag();
                mAuth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                getActivity().finish();

                Toast.makeText(getActivity(), "You have been signed out.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}