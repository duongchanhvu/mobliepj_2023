package bottomnavigation;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quanlychitieu.R;
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)view.findViewById(R.id.list_trans);
        ListViewTransactionAdapter lvTransAdapter = new ListViewTransactionAdapter(getActivity().getApplicationContext(), itemList,itemText2, itemText3, itemImage);
        listView.setAdapter(lvTransAdapter);
    }
}