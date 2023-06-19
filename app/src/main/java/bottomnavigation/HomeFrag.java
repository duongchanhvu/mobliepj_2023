package bottomnavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.TransactionDetailActivity;

import adapter.ListViewTransactionAdapter;


public class HomeFrag extends Fragment {

    String itemList[] = {"Trans 1", "Trans 2", "Trans 3", "Trans 4", "Trans 5", "Trans 6", "Trans 7"};
    String itemText2[] = {"Date: ", "Date: ", "Date: ", "Date: ", "Date: ", "Date: ", "Date: "};
    String itemText3[] = {"Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: ", "Cash: "};
    int itemImage[] = {R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon, R.drawable.transaction_item_icon};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Hide the app title bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }

        listView = (ListView)view.findViewById(R.id.list_trans_home);

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
        listView = (ListView)view.findViewById(R.id.list_trans_home);
        ListViewTransactionAdapter lvTransAdapter = new ListViewTransactionAdapter(getActivity().getApplicationContext(), itemList,itemText2, itemText3, itemImage);
        listView.setAdapter(lvTransAdapter);
    }
}