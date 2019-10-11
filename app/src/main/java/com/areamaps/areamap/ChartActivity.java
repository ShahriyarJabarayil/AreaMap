package com.areamaps.areamap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    LineChart lineChart;
    private BarChart barChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        //lineChart=findViewById(R.id.idchart);
       barChart=(BarChart) findViewById(R.id.idbarchart);
       pieChart=findViewById(R.id.idpiechart);

        float [] y={10f,20f,30f,60f};
        String [] x={"on","iyirmi","otuz","qirx"};
        drawBarChart(y,x);
        drawpieChart(y,x);
    }


    public void drawBarChart(float[]y,String [] x) {
        barChart.setContentDescription("Test chart");

       ArrayList<BarEntry> yData=new ArrayList<>();
        for (int i=0; i<y.length;i++) {
            yData.add(new BarEntry(y[i], i,R.drawable.ic_close_infoview));
        }

        ArrayList<String> xData=new ArrayList<>();
        for (int i=0; i<x.length;i++) {
            xData.add(x[i]);
        }
        BarDataSet barDataSet=new BarDataSet(yData,"Layiheler");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData barData= new BarData(barDataSet);
        barData.setValueTextSize(13f);
        //barData.addDataSet((IBarDataSet) xData);

        barData.setValueTextColor(Color.BLACK);

        barChart.setData(barData);
        barData.setBarWidth(0.9f);
        barChart.invalidate();



    }

    public void  drawpieChart(float[]y,String [] x)
    {
        pieChart.setContentDescription("Test chart");



        ArrayList<String> xData=new ArrayList<>();
        for (int i=0; i<x.length;i++) {
            xData.add(x[i]);
        }
        ArrayList<PieEntry> yData=new ArrayList<>();
        for (int i=0; i<y.length;i++) {
            yData.add(new PieEntry(y[i],xData.get(i).toString()));
        }

        PieDataSet pieDataSet=new PieDataSet(yData,"Ededler");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        PieData pieData= new PieData(pieDataSet);
        //pieData.setValueTextSize(13f);
        //barData.addDataSet((IBarDataSet) xData);

        pieData.setValueTextColor(Color.BLACK);

        pieChart.setData(pieData);
        barChart.invalidate();


    }


}
