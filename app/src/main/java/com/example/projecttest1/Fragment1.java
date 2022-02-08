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

import com.bumptech.glide.Glide;

public class Fragment1 extends Fragment {

    TextView tv_saying_content;
    ImageView img_egg;

    String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_1, container, false);

       tv_saying_content = v.findViewById(R.id.tv_saying_content);
        img_egg = v.findViewById(R.id.egg);

        // 명언 가져오기(DB 연동)
        try {


            RegistersayingActivity task = new RegistersayingActivity();
            result = task.execute().get().replace("    ", "");
            Log.v("return", result);

            tv_saying_content.setText(result);

            // gif 를 사용하기 위한 코드(gif 불러오기)
            Glide.with(this).load(R.drawable.egg_line).into(img_egg);

        } catch (Exception e) {
        }

        return v;
    }

}
