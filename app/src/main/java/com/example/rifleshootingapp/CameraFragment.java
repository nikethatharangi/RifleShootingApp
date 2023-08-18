package com.example.rifleshootingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class CameraFragment extends Fragment {

    private PieChart pieChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        setupPieChart();

        return view;

    }

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, "Correct"));
        entries.add(new PieEntry(60f, "Incorrect"));


        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart Example");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(12f);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        // Additional customization
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Pie Chart");
        pieChart.animateY(1000);

        // Refresh the chart to display the data
        pieChart.invalidate();
    }
}