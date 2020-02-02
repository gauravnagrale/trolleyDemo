package com.example.newtrolleymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class Rice extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice);
    }
    public void MyPage(View view)
    {
        openUrl("https://www.bigbasket.com/ps/?q=rice");
    }
    public void openUrl(String Url)
    {
        Uri uri = Uri.parse(Url);//making the object for uniforn object resources
        Intent launchweb =new Intent(Intent.ACTION_VIEW,uri);
        startActivity(launchweb);
    }


}
