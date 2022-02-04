package com.example.projecttest1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Collections;


public class Fragment4 extends Fragment {

    TextView tv_title_read;
    TextView tv_content_read ;

    // 캘린더 변수
    MaterialCalendarView materialCalendarView;
    EventDecorator eventDecorator;
    SundayDecorator sundayDecorator;
    SaturdayDecorator saturdayDecorator;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_4, container, false);

//        tv_content_read = v.findViewById(R.id.tv_content_read);
//        tv_title_read = v.findViewById(R.id.tv_title_read);

        // MainActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();
        if(bundle != null) {
            String id = bundle.getString("id");
            //tv_test.setText(id);
            Log.v("번들 값", id);
        }

        materialCalendarView = v.findViewById(R.id.calendarView);

        // 달력의 기본 설정
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        // Decorator 객체 선언
        eventDecorator = new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.today()));   // 특정일을 녹색 점 포인트 표시!
        sundayDecorator = new SundayDecorator();
        saturdayDecorator = new SaturdayDecorator();



        /////////////////////////// 기능 구현 ///////////////////////////////
        // 시작 날짜를 오늘 날짜로 지정
        materialCalendarView.setSelectedDate(CalendarDay.today());

        // Decorator 사용 선언
        materialCalendarView.addDecorators(
                eventDecorator,          // 오늘 날짜에 녹색 점 찍기
                sundayDecorator,      // 일요일에 빨간색 지정
                saturdayDecorator  // 토요일에 파란색 지정
        
        );

        materialCalendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_content_read.setVisibility(View.VISIBLE);
            }
        });







        return v;

    }
}