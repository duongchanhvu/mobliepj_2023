package bottomnavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlychitieu.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ReportFrag extends Fragment {

    BarChart barChart;

    PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        // Retrieve data from Firebase using the user ID
        String userId = "your_user_id"; // Replace with the actual user ID
        databaseRef.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Initialize Array
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                ArrayList<PieEntry> pieEntries = new ArrayList<>();

                // This method is called when the data is retrieved successfully

                // Clear the existing data arrays
                barEntries.clear();
                pieEntries.clear();
                int i =0;
                // Loop through the retrieved data and add it to the arrays
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the value from the snapshot
                    String value = snapshot.getValue(String.class);

                    // Convert the value to float
                    float floatValue = Float.parseFloat(value);

                    // Create BarEntry and PieEntry objects
                    BarEntry barEntry = new BarEntry(i, floatValue);
                    PieEntry pieEntry = new PieEntry(i, floatValue);

                    // Add the entries to the arrays
                    barEntries.add(barEntry);
                    pieEntries.add(pieEntry);
                }

                // Update the chart data with the retrieved entries
                BarDataSet barDataSet = new BarDataSet(barEntries, "Tien gi do");
                // Set other properties for barDataSet if needed

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Tien gi do");
                // Set other properties for pieDataSet if needed

                // Update the chart data and refresh the chart
                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);
                barChart.invalidate(); // Refresh the chart

                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.invalidate(); // Refresh the chart
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method is called if there is an error in retrieving the data
                // Handle the error appropriately
            }
        });

        return inflater.inflate(R.layout.fragment_report, container, false);
    }
}