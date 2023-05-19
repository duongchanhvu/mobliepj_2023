package bottomnavigation;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.auth.FirebaseAuth;

import adapter.ListViewTransactionAdapter;

public class TransactionFrag extends Fragment {

    String itemList[] = {"Trans 1", "Trans 2", "Trans 3", "Trans 4", "Trans 5", "Trans 6", "Trans 7"};
    String itemText2[] = {"Date: ", "Date: ", "Date: ", "Date: ", "Date: ", "Date: ", "Date: "};
    String itemText3[] = {"Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: "};
    int itemImage[] = {R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon};
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        listView = (ListView)view.findViewById(R.id.list_trans);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), TransactionDetailActivity.class);
                    startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.list_trans);
        ListViewTransactionAdapter lvTransAdapter = new ListViewTransactionAdapter(getActivity().getApplicationContext(), itemList,itemText2, itemText3, itemImage);
        listView.setAdapter(lvTransAdapter);
    }

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