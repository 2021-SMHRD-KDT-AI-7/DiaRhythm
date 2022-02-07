package com.example.projecttest1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Collections;


public class Fragment4 extends Fragment {

    TextView tv_title_read;
    TextView tv_content_read ;
    ImageView img_read1;
    ImageView img_read2;
    ImageView img_read3;
    TextView tv_read_content;
    TextView tv_read_title;
    ImageView img_letter_open;

    String result;

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
        img_read1 = v.findViewById(R.id.img_read1);
        img_read2 = v.findViewById(R.id.img_read2);
        img_read3 = v.findViewById(R.id.img_read1);
        tv_read_content = v.findViewById(R.id.tv_read_content);
        tv_read_title = v.findViewById(R.id.tv_read_title);
        img_letter_open = v.findViewById(R.id.img_letter_open);


        // MainActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();

            String id = bundle.getString("id");
            //tv_test.setText(id);
            Log.v("번들 값", id);


        materialCalendarView = v.findViewById(R.id.calendarView);

        // 달력의 기본 설정
        materialCalendarView.state().edit()
//                .setFirstDayOfWeek(Calendar.SUNDAY)
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

            materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    int year = widget.getSelectedDate().getYear();
                    int month = widget.getSelectedDate().getMonth();
                    int day = widget.getSelectedDate().getDay();

                    String today = year + "년 " + month + "월 " + day + "일";
                    Toast.makeText(getActivity(), today, Toast.LENGTH_SHORT).show(); // 선택한 날짜 토스트 출력
                }
            });

        img_read1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    img_letter_open.setVisibility(View.VISIBLE);

                    Glide.with(v.getContext()).load(R.drawable.letter_gif).into(img_letter_open);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_letter_open.setVisibility(View.INVISIBLE);
                        }
                    }, 2500);




                    RegisterReadActivity task = new RegisterReadActivity();
                    result = task.execute(id).get().replace("    ", "");
                    Log.v("return", result);

                    //맨 처음, 맨 마지막 대괄호 제거
                    result = result.replace("[","");
                    result = result.replace("]","");
                    Log.v("return", result);

                    // toString 을 " "(공백)을 기준으로 잘라 array 배열에 저장하기
                    String[] array = result.split(" ");

                    tv_read_title.setText((array[8]));
                    tv_read_content.setText(array[9]);


                } catch (Exception e) {
                }



            }
        });
        
        img_read2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_read_title.setText("ㅆ");
            }
        });


        return v;

    }
}