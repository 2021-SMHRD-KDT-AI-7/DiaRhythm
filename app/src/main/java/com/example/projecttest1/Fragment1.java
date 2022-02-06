package com.example.projecttest1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment1 extends Fragment {
    Fragment4 fragment4;
    String result_rep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_1, container, false);

        TextView tv_saying_content = v.findViewById(R.id.tv_saying_content);

        // 명언 가져오기(DB 연동)
        try {
            String result;


            RegistersayingActivity task = new RegistersayingActivity();
            result = task.execute().get().replace("    ", "");
            Log.v("return", result);


            tv_saying_content.setText(result);

        } catch (Exception e) {
        }

        return v;
    }

}
