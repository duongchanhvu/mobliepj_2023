package bottomnavigation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.SendMailActivity;
import com.example.quanlychitieu.TransactionDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import adapter.ListViewTransactionAdapter;
import objects.Transaction;

public class TransactionFrag extends Fragment {

    String itemList[];
    String itemText2[];
    String itemText3[];
    int itemImage[];
    ListView listView;

    RecyclerView recyclerView;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    DatabaseReference transactionRef;
    DatabaseReference transKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
//        listView = (ListView)view.findViewById(R.id.list_trans);
        String userID = currentUser.getUid();
        transKey = FirebaseDatabase.getInstance().getReference().child("");
        transactionRef = FirebaseDatabase.getInstance().getReference().child("Transactions").child(userID);
        recyclerView = view.findViewById(R.id.list_trans);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Transaction> options =
                new FirebaseRecyclerOptions.Builder<Transaction>()
                        .setQuery(transactionRef, Transaction.class)
                        .build();
        FirebaseRecyclerAdapter<Transaction, MyViewHolder> adapter =
                new FirebaseRecyclerAdapter<Transaction, MyViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Transaction model) {
                        holder.setDate(model.getTransDate());
                        holder.setAmount(String.valueOf(model.getTransAmount()));
                    }

                    @NonNull
                    @Override
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        // Tạo ViewHolder mới
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
                        return new MyViewHolder(view);
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public MyViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        private void setDate(String date){
            TextView mDate = mView.findViewById(R.id.item_text_trans);
            mDate.setText(date);
        }
        private void setAmount(String amount){
            TextView mAmount = mView.findViewById(R.id.item_text_trans_2);
            mAmount.setText(amount);
        }
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        listView = (ListView)view.findViewById(R.id.list_trans);
//        ListViewTransactionAdapter lvTransAdapter = new ListViewTransactionAdapter(getActivity().getApplicationContext(),
//                itemList,itemText2, itemText3, itemImage);
//        listView.setAdapter(lvTransAdapter);
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

}