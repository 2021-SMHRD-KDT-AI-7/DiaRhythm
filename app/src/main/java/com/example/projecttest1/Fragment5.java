package com.example.projecttest1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Fragment5 extends Fragment {

    ListView lv;
    TextView tv_ga, tv_name_f5,tv_join_f5,tv_grade,btn_logout;
    String result;
    String result_rep;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_5, container, false);
        tv_ga = v.findViewById(R.id.tv_ga);
        tv_name_f5 = v.findViewById(R.id.tv_name_f5);
        tv_join_f5 = v.findViewById(R.id.tv_join_f5);
        tv_grade = v.findViewById(R.id.tv_grade);
        btn_logout= v.findViewById(R.id.btn_logout);
        lv = v.findViewById(R.id.lv);

        List<String> info = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.info,info);
        lv.setAdapter(adapter);
        try {
            RegisterInfoActivity task = new RegisterInfoActivity();

            Bundle bundle = getArguments();

            String id = bundle.getString("id");

            result = task.execute(id).get();

            Log.v("MY", result);
            result_rep = result.replace(" ", "");
            Log.v("일기쓴횟수", result_rep);

        }catch (Exception e){

        }

        info.add("내가 쓴 일기 횟수" + result);
        info.add("연속 작성 횟수");
        info.add("공지사항");
        info.add("개인정보약관동의");
        info.add("앱버전");



        adapter.notifyDataSetChanged();
        return v;

    }
}