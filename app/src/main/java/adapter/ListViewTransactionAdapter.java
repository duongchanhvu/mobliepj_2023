package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlychitieu.R;

public class ListViewTransactionAdapter extends BaseAdapter {

    Context context;
    String itemList[];
    String itemText2[];
    String itemText3[];
    int itemImage[];
    LayoutInflater inflater;
    public ListViewTransactionAdapter(Context ctx, String[] itemList,String[] itemText2, String[] itemText3, int[] itemImage){
        this.context = context;
        this.itemList = itemList;
        this.itemText2 = itemText2;
        this.itemText3 = itemText3;
        this.itemImage = itemImage;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return itemList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_transaction, null);
        TextView itemText = (TextView) convertView.findViewById(R.id.item_text_trans);
        TextView itemTextn2 = (TextView) convertView.findViewById(R.id.item_text_trans_2);
        TextView itemTextn3 = (TextView) convertView.findViewById(R.id.item_text_trans_3);
        ImageView itemImg = (ImageView) convertView.findViewById(R.id.item_image_trans);
        itemText.setText(itemList[position]);
        itemTextn2.setText(itemText2[position]);
        itemTextn3.setText(itemText3[position]);
        itemImg.setImageResource(itemImage[position]);
        return convertView;
    }
}
