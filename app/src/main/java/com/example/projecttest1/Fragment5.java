package com.example.projecttest1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Fragment5 extends Fragment {

    ListView lv;
    TextView tv_day, tv_name_f5,tv_join_f5,tv_grade,btn_logout;
    String result;
    String result_rep;
    String result2;
    String result_rep2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_5, container, false);
        tv_day = v.findViewById(R.id.tv_day);
        tv_name_f5 = v.findViewById(R.id.tv_name_f5);
        tv_join_f5 = v.findViewById(R.id.tv_join_f5);
        tv_grade = v.findViewById(R.id.tv_grade);
        btn_logout= v.findViewById(R.id.btn_logout);
        lv = v.findViewById(R.id.lv);

        List<String> info = new ArrayList<>();

        // 가입일자 Dday+ 코드
        try {
            RegisterDdayActivity task = new RegisterDdayActivity();

            Bundle bundle = getArguments();

            String id = bundle.getString("id");

            result2 = task.execute(id).get();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            long now = System.currentTimeMillis();
            Date date = new Date(now);

            String date1 =result2;

            String getTime = format.format(date);

            Date FirstDate = format.parse(getTime);
            Date SecondDate = format.parse(date1);


            // Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수를 초기화 하고 있다.
            // 연산결과 -950400000. long type 으로 return 된다.
            long calDate = FirstDate.getTime() - SecondDate.getTime();

            // Date.getTime() 은 해당날짜를 기준으로1970년 00:00:00 부터 몇 초가 흘렀는지를 반환해준다.
            // 이제 24*60*60*1000(각 시간값에 따른 차이점) 을 나눠주면 일수가 나온다.
//          long calDateDays = calDate / ( 24*60*60*1000); 아래코드와 같음
            long calDateDays = calDate / 86400000;

            calDateDays = Math.abs(calDateDays);


            tv_day.setText("Day+"+calDateDays);

        }catch (Exception e){

        }


        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.info,info);
        lv.setAdapter(adapter);


        // 내정보 일기쓴 횟수 코드
        try {
            RegisterInfoActivity task = new RegisterInfoActivity();

            Bundle bundle = getArguments();

            String id = bundle.getString("id");

            result = task.execute(id).get();

            Log.v("MY", result);
            result_rep = result.replace(" ", "");


        info.add("내가 쓴 일기 횟수" +"       "+ result);
        info.add("공지사항");
        info.add("개인정보약관동의");
        info.add("앱버전"+ "                      " +    "     1.0 v");


        }catch (Exception e){

        }

        // 로그아웃 기능

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);

            }
        });

        
        adapter.notifyDataSetChanged();
        return v;

    }
}