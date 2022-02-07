package com.example.projecttest1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.ExecutionException;


public class Fragment4 extends Fragment {

    TextView tv_title_read;
    TextView tv_content_read;
    ImageView img_read1;
    ImageView img_read2;
    ImageView img_read3;
    TextView tv_read_content;
    TextView tv_read_title;
    ImageView img_letter_open;

    String result;
    String result2;

    // 캘린더 변수
    MaterialCalendarView materialCalendarView;
    EventDecorator eventDecorator;
    EventDecorator ed1;
    SundayDecorator sundayDecorator;
    SaturdayDecorator saturdayDecorator;
    ArrayList<EventDecorator> ed2 = new ArrayList<EventDecorator>();

    // 전역 변수
    String[] array2 = new String[200];
    String[] array3 = new String[200];
    ArrayList<String> arr_date = new ArrayList<String>();
    ArrayList<Integer> arr_date2 = new ArrayList<Integer>();
    int k = 0;

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
        materialCalendarView = v.findViewById(R.id.calendarView);

        // MainActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();

        String id = bundle.getString("id");
        Log.v("번들 값", id);

        // 로그인한 사람이 이전에 작성한 날짜에 점찍기
        try {

            // DB 연동
            RegisterCalandarActivity task2 = new RegisterCalandarActivity();
            result2 = task2.execute(id).get().replace("    ", "");

            //맨 처음, 맨 마지막 대괄호 제거
            result2 = result2.replace("[", "");
            result2 = result2.replace("]", "");
            Log.v("캘린더 점찍기값", result2);

            // toString 을 " "(공백)을 기준으로 잘라 array 배열에 저장하기
            array2 = result2.split(" ");

            // array2 의 날짜값을 골라서 담아줄 가변 배열 선언
            //ArrayList<String> arr_date = new ArrayList<String>();
            int j = 0;

            // 날짜값만 arr_date 에 저장
            for(int i = 0; i <= array2.length; i++){
                if(i % 2 == 0){
                Log.v("if문",array2[i]);
                    arr_date.add(array2[i]);
                    Log.v("arr_date 값",arr_date.get(j));
                    j++;
                }
            }

        }catch (Exception e){
        }

        // year - month - day 문자열 자르기
        for(int i = 0; i < arr_date.size(); i++) {
            array3 = arr_date.get(i).split("-");

            for(int j = 0; j <= 2; j++) {
                arr_date2.add(Integer.parseInt(array3[j]));
                Log.v("arr_date2 값",arr_date2.toString());
            }
        }

        // 달력의 기본 설정
        materialCalendarView.state().edit()
//                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1))   // 달력의 최소 날짜 범위
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) //달력의 최대 날짜 범위
                .setCalendarDisplayMode(CalendarMode.MONTHS)    // 월 별로 보여주기 모드
                .commit();

        // Decorator 객체 선언
        for(int i = 0; i < (arr_date2.size())/3; i++) {
            ed2.add(new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.from(arr_date2.get(i+k), arr_date2.get(i+k+1)-1, arr_date2.get(i+k+2)))));
            k+=2;
        }

        sundayDecorator = new SundayDecorator();
        saturdayDecorator = new SaturdayDecorator();


        /////////////////////////// 기능 구현 ///////////////////////////////
        // 시작 날짜를 오늘 날짜로 지정
        materialCalendarView.setSelectedDate(CalendarDay.today());

        // Decorator 사용 선언
        materialCalendarView.addDecorators(
                sundayDecorator,      // 일요일에 빨간색 지정
                saturdayDecorator  // 토요일에 파란색 지정

        );
        // 점찍기
        for (int i = 0; i < ed2.size(); i++) {
        materialCalendarView.addDecorators(
            ed2.get(i)
        );
        }

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
                    result = result.replace("[", "");
                    result = result.replace("]", "");
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