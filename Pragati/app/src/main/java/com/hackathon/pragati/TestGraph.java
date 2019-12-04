package com.hackathon.pragati;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
public class TestGraph extends AppCompatActivity {
    GraphView graphView;
    public DataPoint dataPoint[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_graph);
        graphView=findViewById(R.id.graph1);
            int[] a={8,6,2,3,4,7,9};
            int[] b={8,6,2,3,4,7,9};
         dataPoint=new DataPoint[a.length];
            for(int i=0;i<a.length;i++)
            {
                dataPoint[i]= new DataPoint(a[i],b[i]);
            }
//        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(dataPoint);
//        lineGraphSeries.setTitle("Kapil");
//            lineGraphSeries.setColor(Color.RED);
//            lineGraphSeries.setThickness(5);
//            graphView.addSeries(lineGraphSeries);

        BarGraphSeries<DataPoint> barGraphSeries=new BarGraphSeries<>(dataPoint);
            graphView.addSeries(barGraphSeries);
    }
}
