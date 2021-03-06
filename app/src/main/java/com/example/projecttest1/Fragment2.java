package com.example.projecttest1;

import static java.lang.Integer.*;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Fragment2 extends Fragment {
    LineChart lineChart;
    String result;
    String result_rep;
    ImageView img_wordCloud;


    String num1, num_1, num2, num_2, num3, num_3, num4, num_4, num5, num_5, num6, num_6, num7, num_7;

    int a;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        lineChart = v.findViewById(R.id.linechart);
        img_wordCloud = v.findViewById(R.id.img_wordCloud);

        // DB?????? ????????? ????????? ???????????? ????????? ????????? ?????? ????????????
        try {
            Bundle bundle = getArguments();
            String id = bundle.getString("id");
            Log.v("???????????????2??? id??????", id);

            RegisterEmotionActivity task = new RegisterEmotionActivity();
            result = task.execute(id).get();
            Log.v("????????? ?????????", result);



            num1 = result.substring(10, 15);
            num_1 = result.substring(25, 27);

            num2 = result.substring(34, 40);
            num_2 = result.substring(49, 51);

            num3 = result.substring(58, 64);
            num_3 = result.substring(73, 75);

            num4 = result.substring(82, 88);
            num_4 = result.substring(97, 99);

            num5 = result.substring(106, 112);
            num_5 = result.substring(121, 123);

            num6 = result.substring(130, 136);
            num_6 = result.substring(145, 147);

            num7 = result.substring(154, 160);
            num_7 = result.substring(169, 171);


        } catch (Exception e) {

        }

        // ????????? ?????? ??????????????? emotions ????????? ?????? ???????????? ?????? ???????????? ?????? ?????????
        String emotions[] = {num_5, num_3, num_6, num_2, num_4, num_1, num_7};
        ArrayList<Integer> y_date = new ArrayList<>();

        for (int i = 0; i <= 6; i++) {

            if (emotions[i].equals("??????")) {
                a = 80;
            } else if (emotions[i].equals("??????")) {
                a = 60;
            } else if (emotions[i].equals("??????")) {
                a = 40;
            } else if (emotions[i].equals("??????")) {
                a = 30;
            } else if (emotions[i].equals("??????")) {
                a = 20;
            } else if (emotions[i].equals("??????")) {
                a = 10;
            }y_date.add(a);
        }
        ArrayList<Entry> entries = new ArrayList<>();
        // ?????? ???????????? ??????
        entries.add(new Entry(0, y_date.get(0)));
        entries.add(new Entry(1, y_date.get(1)));
        entries.add(new Entry(2, y_date.get(2)));
        entries.add(new Entry(3, y_date.get(3)));
        entries.add(new Entry(4, y_date.get(4)));
        entries.add(new Entry(5, y_date.get(5)));
        entries.add(new Entry(6, y_date.get(6)));



        // X??? ??????
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.enableGridDashedLine(10, 10, 10);  ???????????????
        xAxis.setTextSize(12);
        //xAxis.setTextColor((Color.parseColor("#304ffe")));
        xAxis.setTextColor((Color.parseColor("#FFFFFF")));

        final String[] weekdays = {num1, num2, num3, num4, num5, num6, num7};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));

        // y??? ??????
        YAxis yAxisLeft = lineChart.getAxisLeft(); // y??? ??????
        YAxis yAxisRight = lineChart.getAxisRight();  // y??? ?????????

        String happy = "????????????";
        // y??? min max ??? ????????? ???
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
        yAxisLeft.setTextSize(12);
        yAxisLeft.setValueFormatter(new IndexAxisValueFormatter(Collections.singleton(happy)));
        yAxisLeft.setTextColor((Color.parseColor("#fafafa")));


        //yAxisLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.black)); //Y??? ????????? ?????? ??????
        //yAxisLeft.setGridColor(ContextCompat.getColor(getContext(), R.color.white)); // Y??? ?????? ?????? ??????

        // y??? ????????? ????????? ??????
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);


        LineDataSet dataset = new LineDataSet(entries, null);
        // ?????? ?????????
        dataset.setLineWidth(5);
        //dataset.setColor(Color.parseColor("#ffa7c4"));
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataset.setDrawCubic(true); //??? ????????? ?????????
        //dataset.setDrawFilled(true); //????????? ????????? ??????
        //dataset.setDrawHighlightIndicators(true); // ???????????? ?????? ??????
        //dataset.setFormSize(20);

        LineData lineData = new LineData(dataset);
        //?????? ?????????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setData(lineData);
        lineChart.animateY(2000);
        lineChart.setDoubleTapToZoomEnabled(false);

        //lineChart.setDrawGridBackground(true); // ????????? ?????? ??????
        //Description description = new Description(); // ?????? ?????? ??????
        //description.setText(""); // ?????? ?????????
        //lineChart.setDescription(description); // ?????? ?????????

        // wordCloud ????????????
        String wordCloud =  "http://121.147.52.142:5000/static/wc_img/diary_wc02.png";

        Glide.with(this).load(wordCloud).into(img_wordCloud);

        return v;

    }

}







