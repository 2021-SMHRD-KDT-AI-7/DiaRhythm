package com.example.projecttest1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class Fragment4 extends Fragment {

    ImageView img_read1;
    ImageView img_read2;
    ImageView img_read3;
    TextView tv_read_content;
    TextView tv_read_title;
    TextView tv_previewDate1;
    TextView tv_previewDate2;
    TextView tv_previewDate3;
    ImageView img_letter_open;

    TextView[] tv_previews;
    TextView[] tv_preview_cons;

    ImageView img_preRead;
    ImageView img_readClose;
    ConstraintLayout lo_overView;
    ConstraintLayout lo_preview;
    ConstraintLayout lo_preview1;
    ConstraintLayout lo_preview2;
    ConstraintLayout lo_preview3;


    String result;
    String result2;
    String writeDay;

    int year;
    int month;
    int day;

    JSONObject obj;
    JSONArray jsonArray = new JSONArray();

    // 캘린더 변수
    MaterialCalendarView materialCalendarView;
    SundayDecorator sundayDecorator;
    SaturdayDecorator saturdayDecorator;
    ArrayList<EventDecorator> ed2 = new ArrayList<EventDecorator>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_4, container, false);


        img_read1 = v.findViewById(R.id.img_read1);
        img_read2 = v.findViewById(R.id.img_read2);
        img_read3 = v.findViewById(R.id.img_read3);

        tv_previewDate1 = v.findViewById(R.id.tv_previewDate1);
        tv_previewDate2 = v.findViewById(R.id.tv_previewDate2);
        tv_previewDate3 = v.findViewById(R.id.tv_previewDate3);

        tv_read_content = v.findViewById(R.id.tv_read_content);
        tv_read_title = v.findViewById(R.id.tv_read_title);
        img_letter_open = v.findViewById(R.id.img_letter_open);
        materialCalendarView = v.findViewById(R.id.calendarView);

        img_preRead = v.findViewById(R.id.img_preRead);
        img_readClose = v.findViewById(R.id.img_readClose);

        lo_overView = (ConstraintLayout) v.findViewById(R.id.lo_overView);
        lo_preview = (ConstraintLayout) v.findViewById(R.id.lo_preview);

        lo_preview1 = (ConstraintLayout) v.findViewById(R.id.lo_preview1);
        lo_preview2 = (ConstraintLayout) v.findViewById(R.id.lo_preview2);
        lo_preview3 = (ConstraintLayout) v.findViewById(R.id.lo_preview3);

        // 전역 변수
        String[] array2 = new String[200];
        String[] array3 = new String[200];

        tv_previews = new TextView[50];
        tv_preview_cons = new TextView[50];

        for (int i = 0; i < tv_previews.length; i++) {
            int titleId = getResources().getIdentifier("tv_preview" + (i + 1), "id", getActivity().getPackageName());
            int contentId = getResources().getIdentifier("tv_preview_con" + (i + 1), "id", getActivity().getPackageName());

            tv_previews[i] = v.findViewById(titleId);
            tv_preview_cons[i] = v.findViewById(contentId);
        }

        ArrayList<String> arr_date = new ArrayList<String>();
        ArrayList<Integer> arr_date2 = new ArrayList<Integer>();

        int k = 0;

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

            int j = 0;

            // 날짜값만 arr_date 에 저장
            for (int i = 0; i <= array2.length; i++) {
                if (i % 2 == 0) {
                    Log.v("if문", array2[i]);
                    arr_date.add(array2[i]);
                    Log.v("arr_date 값", arr_date.get(j));
                    j++;
                }
            }

        } catch (Exception e) {
        }

        // year - month - day 문자열 자르기
        for (int i = 0; i < arr_date.size(); i++) {
            array3 = arr_date.get(i).split("-");

            for (int j = 0; j <= 2; j++) {
                arr_date2.add(Integer.parseInt(array3[j]));
                Log.v("arr_date2 값", arr_date2.toString());
            }
        }

        // 달력의 기본 설정
        materialCalendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2017, 0, 1))   // 달력의 최소 날짜 범위
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) //달력의 최대 날짜 범위
                .setCalendarDisplayMode(CalendarMode.MONTHS)    // 월 별로 보여주기 모드
                .commit();

        // Decorator 객체 선언
        for (int i = 0; i < (arr_date2.size()) / 3; i++) {
            ed2.add(new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.from(arr_date2.get(i + k), arr_date2.get(i + k + 1) - 1, arr_date2.get(i + k + 2)))));
            k += 2;
        }

        sundayDecorator = new SundayDecorator();
        saturdayDecorator = new SaturdayDecorator();


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

        // 날짜를 눌렀을때 동작하는 메소드
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                year = widget.getSelectedDate().getYear();
                month = widget.getSelectedDate().getMonth() + 1;
                day = widget.getSelectedDate().getDay();
                String today1;
                String[] array4 = new String[200];

                // if 를 사용하여 10으로 나눴을 때 0일때 --> 1의자리일때 문자열 0 붙이기
                if (day / 10 == 0) {
                    today1 = year + "0" + month + "0" + day + ""; // 1월부터 0월까지는 01 02 가 아니라 1 2 로 출력되서 코드상 0을 넣음
                } else {
                    today1 = year + "0" + month + "" + day + ""; // 1월부터 0월까지는 01 02 가 아니라 1 2 로 출력되서 코드상 0을 넣음
                }

                // db 연동
                try {
                    // 클릭한 해당 일에 일기를 썼는지 판별
                    RegisterWritedayActivity task1 = new RegisterWritedayActivity();
                    writeDay = task1.execute(id, today1).get().replace("    ", "");
                    Log.v("일기썼냐?", writeDay);

                    // 클릭한 날짜에 일기 쓴게 없다면 프리뷰 감추기
                    if (writeDay.equals("null")) {
                        lo_preview.setVisibility(View.INVISIBLE);
                    } else {
                        lo_preview.setVisibility(View.VISIBLE);
                    }

                    // 일기 제목, 내용 데이터 불러오기
                    RegisterPreviewActivity task = new RegisterPreviewActivity();
                    result = task.execute(id, today1).get().replace("    ", "");
                    Log.v("return", result);
                    
                    // JSON 파일의 딕셔너리 형태로 배열에 저장
                    jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        obj = (JSONObject) jsonArray.getJSONObject(i);

                        Log.v("프리뷰일기", obj.getString("title"));
                        Log.v("프리뷰일기", obj.getString("content"));

                        if (i < 3) {
                            tv_previews[i].setText(obj.getString("title"));
                            tv_preview_cons[i].setText(obj.getString("content"));
                        }

                        // 불러올 일기가 1개라면? 1개만 표시
                        if(i <= 0){
                            tv_previewDate1.setText(year + "-" + month + "-" + day);
                            lo_preview1.setVisibility(View.VISIBLE);
                            lo_preview2.setVisibility(View.INVISIBLE);
                            lo_preview3.setVisibility(View.INVISIBLE);

                        // 불러올 일기가 2개라면? 2개만 표시
                        }else if(i <= 1){
                            //tv_previewDate2.setText(Integer.toString(Integer.parseInt(today1)-1));
                            tv_previewDate2.setText(year + "-" + month + "-" + (day-1));
                            lo_preview1.setVisibility(View.VISIBLE);
                            lo_preview2.setVisibility(View.VISIBLE);
                            lo_preview3.setVisibility(View.INVISIBLE);

                        // 불러올 일기가 3개라면? 3개 표시
                        }else if(i <= 2){
                            //tv_previewDate3.setText(Integer.toString(Integer.parseInt(today1)-2));
                            tv_previewDate3.setText(year + "-" + month + "-" + (day-2));
                            lo_preview1.setVisibility(View.VISIBLE);
                            lo_preview2.setVisibility(View.VISIBLE);
                            lo_preview3.setVisibility(View.VISIBLE);
                        }
                    }
                } catch (Exception e) {
                    Log.v("예외 발생!", result);
                }
            }
        });

        // 프리뷰1 클릭시 오버뷰
        img_read1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    img_letter_open.setVisibility(View.VISIBLE);

                    Glide.with(v.getContext()).load(R.drawable.letter_new).into(img_letter_open);

                    // 편지 봉투 로딩
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_letter_open.setVisibility(View.INVISIBLE);
                            lo_overView.setVisibility(View.VISIBLE);
                            
                            try {
                                jsonArray = new JSONArray(result);

                                obj = (JSONObject) jsonArray.getJSONObject(0);

                                Log.v("프리뷰일기", obj.getString("title"));
                                Log.v("프리뷰일기", obj.getString("content"));

                                tv_read_title.setText(obj.getString("title"));
                                tv_read_content.setText(obj.getString("content"));

                                Log.v("데이값",year + "0" + month + "0" + day);

                            } catch (Exception e) {

                            }
                        }
                    }, 1000);

                } catch (Exception e) {
                }

            }
        });

        // 프리뷰2 클릭시 오버뷰
        img_read2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    img_letter_open.setVisibility(View.VISIBLE);

                    Glide.with(v.getContext()).load(R.drawable.letter_new).into(img_letter_open);

                    // 편지 봉투 로딩
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_letter_open.setVisibility(View.INVISIBLE);
                            lo_overView.setVisibility(View.VISIBLE);

                            try {

                                jsonArray = new JSONArray(result);
                                obj = (JSONObject) jsonArray.getJSONObject(1);

                                Log.v("프리뷰일기", obj.getString("title"));
                                Log.v("프리뷰일기", obj.getString("content"));

                                tv_read_title.setText(obj.getString("title"));
                                tv_read_content.setText(obj.getString("content"));
                            } catch (Exception e) {
                            }
                        }
                    }, 1000);
                } catch (Exception e) {
                }
            }
        });

        // 프리뷰3 클릭시 오버뷰
        img_read3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    img_letter_open.setVisibility(View.VISIBLE);

                    Glide.with(v.getContext()).load(R.drawable.letter_new).into(img_letter_open);

                    // 편지 봉투 로딩
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            img_letter_open.setVisibility(View.INVISIBLE);
                            lo_overView.setVisibility(View.VISIBLE);

                            try {
                                jsonArray = new JSONArray(result);

                                obj = (JSONObject) jsonArray.getJSONObject(2);

                                Log.v("프리뷰일기", obj.getString("title"));
                                Log.v("프리뷰일기", obj.getString("content"));

                                tv_read_title.setText(obj.getString("title"));
                                tv_read_content.setText(obj.getString("content"));

                            } catch (Exception e) {
                            }
                        }
                    }, 1000);
                } catch (Exception e) {
                }
            }
        });


        // 일기 overView
        img_preRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // 일기 overView 닫기
        img_readClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lo_overView.setVisibility(View.INVISIBLE);
            }
        });
        return v;
    }
}