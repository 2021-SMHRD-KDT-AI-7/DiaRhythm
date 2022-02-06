package com.example.projecttest1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fragment2 extends Fragment {
    LineChart lineChart;
    String result;
    String result_rep;
    TextView tv_test1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        lineChart = v.findViewById(R.id.linechart);
        tv_test1 = v.findViewById(R.id.tv_test1);


        try {
            RegisterEmotionActivity task = new RegisterEmotionActivity();

            Bundle bundle = getArguments();
            String id = bundle.getString("id");

            result = task.execute(id).get();
            Log.v("MY", result);
            result_rep = result.replace(" ", "");

            tv_test1.setText(result);

        }catch (Exception e){

        }
        ArrayList<Integer> y_date = new ArrayList<>();
        y_date.add(3);
        y_date.add(6);
        y_date.add(3);
        y_date.add(14);
        y_date.add(18);
        y_date.add(20);
        y_date.add(4);

        //        ArrayList<String> X_date = new ArrayList<>();
//        X_date.add("월");
//        X_date.add("화");
//        X_date.add("수");
//        X_date.add("목");
//        X_date.add("금");
//        X_date.add("토");
//        X_date.add("일");

        // X축 생성
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.enableGridDashedLine(10, 10, 10); // 그리드라인
        xAxis.setTextSize(15);
//        xAxis.setTextColor((Color.parseColor("#304ffe")));
        xAxis.setTextColor((Color.parseColor("#FFFFFF")));

        final String[] weekdays = {"월", "화", "수", "목", "금", "토", "일"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));


        // y축 생성
        YAxis yAxisLeft = lineChart.getAxisLeft(); // y축 왼쪽
        YAxis yAxisRight = lineChart.getAxisRight();  // y축 오른쪽

        String happy = "행복지수";
        // y축 min max 값 구하는 법
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisMaximum(100);
        yAxisLeft.setTextSize(15);
        yAxisLeft.setValueFormatter(new IndexAxisValueFormatter(Collections.singleton(happy)));
        yAxisLeft.setTextColor((Color.parseColor("#fafafa")));



//        yAxisLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.black)); //Y축 텍스트 컬러 설정
//        yAxisLeft.setGridColor(ContextCompat.getColor(getContext(), R.color.white)); // Y축 줄의 컬러 설정

        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
//        // y축 오른쪽 활성화 제거

        ArrayList<Entry> entries = new ArrayList<>();
        // 차트 데이터값 넣기
        entries.add(new Entry(0, y_date.get(0)));
        entries.add(new Entry(1, y_date.get(1)));
        entries.add(new Entry(2, y_date.get(2)));
        entries.add(new Entry(3, y_date.get(3)));
        entries.add(new Entry(4, y_date.get(4)));
        entries.add(new Entry(5, y_date.get(5)));
        entries.add(new Entry(6, y_date.get(6)));

        LineDataSet dataset = new LineDataSet(entries,null);
        // 차트 만들기
        dataset.setLineWidth(4);
//        dataset.setColor(Color.parseColor("#ffa7c4"));
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //dataset.setDrawCubic(true); //선 둥글게 만들기
        //dataset.setDrawFilled(true); //그래프 밑부분 색칠
//        dataset.setDrawHighlightIndicators(true); // 눌렀을때 라인 표시

//        dataset.setFormSize(20);



        LineData lineData = new LineData(dataset);
        // 차트 그리기
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.setData(lineData);
        lineChart.animateY(2000);
        lineChart.setDoubleTapToZoomEnabled(false);

//        lineChart.setDrawGridBackground(true); // 배경에 점선 표시
//        Description description = new Description(); // 차트 주석 생성
//        description.setText(""); // 주석 미기입
//        lineChart.setDescription(description); // 주석 그리기




        return v;


    }

}








