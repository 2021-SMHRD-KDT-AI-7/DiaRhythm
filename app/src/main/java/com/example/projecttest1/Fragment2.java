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

        // DB에서 받아온 문자열 데이터를 필요한 규격에 맞게 슬라이싱
        try {
            Bundle bundle = getArguments();
            String id = bundle.getString("id");
            Log.v("프래그먼트2의 id값은", id);

            RegisterEmotionActivity task = new RegisterEmotionActivity();
            result = task.execute(id).get();
            Log.v("그래프 데이터", result);



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

        // 일주일 감정 문자열값을 emotions 배열에 넣고 포문으로 돌려 일치하는 값에 수치화
        String emotions[] = {num_5, num_3, num_6, num_2, num_4, num_1, num_7};
        ArrayList<Integer> y_date = new ArrayList<>();

        for (int i = 0; i <= 6; i++) {

            if (emotions[i].equals("기쁨")) {
                a = 80;
            } else if (emotions[i].equals("당황")) {
                a = 60;
            } else if (emotions[i].equals("불안")) {
                a = 40;
            } else if (emotions[i].equals("분노")) {
                a = 30;
            } else if (emotions[i].equals("상처")) {
                a = 20;
            } else if (emotions[i].equals("슬픔")) {
                a = 10;
            }y_date.add(a);
        }
        ArrayList<Entry> entries = new ArrayList<>();
        // 차트 데이터값 넣기
        entries.add(new Entry(0, y_date.get(0)));
        entries.add(new Entry(1, y_date.get(1)));
        entries.add(new Entry(2, y_date.get(2)));
        entries.add(new Entry(3, y_date.get(3)));
        entries.add(new Entry(4, y_date.get(4)));
        entries.add(new Entry(5, y_date.get(5)));
        entries.add(new Entry(6, y_date.get(6)));



        // X축 생성
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.enableGridDashedLine(10, 10, 10);  그리드라인
        xAxis.setTextSize(12);
        //xAxis.setTextColor((Color.parseColor("#304ffe")));
        xAxis.setTextColor((Color.parseColor("#FFFFFF")));

        final String[] weekdays = {num1, num2, num3, num4, num5, num6, num7};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));

        // y축 생성
        YAxis yAxisLeft = lineChart.getAxisLeft(); // y축 왼쪽
        YAxis yAxisRight = lineChart.getAxisRight();  // y축 오른쪽

        String happy = "행복지수";
        // y축 min max 값 구하는 법
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
        yAxisLeft.setTextSize(12);
        yAxisLeft.setValueFormatter(new IndexAxisValueFormatter(Collections.singleton(happy)));
        yAxisLeft.setTextColor((Color.parseColor("#fafafa")));


        //yAxisLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.black)); //Y축 텍스트 컬러 설정
        //yAxisLeft.setGridColor(ContextCompat.getColor(getContext(), R.color.white)); // Y축 줄의 컬러 설정

        // y축 오른쪽 활성화 제거
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);


        LineDataSet dataset = new LineDataSet(entries, null);
        // 차트 만들기
        dataset.setLineWidth(5);
        //dataset.setColor(Color.parseColor("#ffa7c4"));
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataset.setDrawCubic(true); //선 둥글게 만들기
        //dataset.setDrawFilled(true); //그래프 밑부분 색칠
        //dataset.setDrawHighlightIndicators(true); // 눌렀을때 라인 표시
        //dataset.setFormSize(20);

        LineData lineData = new LineData(dataset);
        //차트 그리기
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setData(lineData);
        lineChart.animateY(2000);
        lineChart.setDoubleTapToZoomEnabled(false);

        //lineChart.setDrawGridBackground(true); // 배경에 점선 표시
        //Description description = new Description(); // 차트 주석 생성
        //description.setText(""); // 주석 미기입
        //lineChart.setDescription(description); // 주석 그리기

        // wordCloud 가져오기
        String wordCloud =  "http://121.147.52.142:5000/static/wc_img/diary_wc.png";

        Glide.with(this).load(wordCloud).into(img_wordCloud);

        return v;

    }

}







