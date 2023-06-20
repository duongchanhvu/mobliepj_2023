package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import objects.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyHolder> {
    Context context;
    String userID;
    List<Transaction> transactions;
    public TransactionAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String amount = String.valueOf(transactions.get(position).getTransAmount());
        String date = transactions.get(position).getTransDate();
        String note = transactions.get(position).getTransNote();
        holder.transAmount.setText(amount);
        holder.transDate.setText(date);
        holder.transNote.setText(note);
        if (transactions.get(position).getPay() == false) {
            holder.transImg.setImageResource(R.drawable.income);
        } else {
            holder.transImg.setImageResource(R.drawable.outcome);
        }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView transImg;
        TextView transAmount, transDate, transNote;
        LinearLayout transItem;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            transImg = itemView.findViewById(R.id.item_image_trans);
            transAmount = itemView.findViewById(R.id.item_text_trans);
            transDate = itemView.findViewById(R.id.item_text_trans_2);
            transNote = itemView.findViewById(R.id.item_text_trans_3);
            transItem = itemView.findViewById(R.id.trans_linearlayout);
        }
    }
}