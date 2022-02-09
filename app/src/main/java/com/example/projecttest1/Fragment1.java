package com.example.projecttest1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    TextView tv_saying_content;
    TextView tv_saying_man;
    ImageView img_egg;

    String result;
    String[] word;
    ArrayList<String> str = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_1, container, false);

        tv_saying_content = v.findViewById(R.id.tv_saying_content);
        tv_saying_man = v.findViewById(R.id.tv_saying_man);
        img_egg = v.findViewById(R.id.egg);

        // 명언 가져오기(DB 연동)
        try {


            RegistersayingActivity task = new RegistersayingActivity();
            result = task.execute().get().replace("    ", "");
            Log.v("명언", result);

            word = result.split("-");
            Log.v("word값은?",word[1]);

            for(int i = 0; i < word.length; i++) {
                str.add(word[i]);
            }


            tv_saying_content.setText(str.get(0));
            tv_saying_man.setText(str.get(1));

            // gif 를 사용하기 위한 코드(gif 불러오기)
            Glide.with(this).load(R.drawable.egg_line).into(img_egg);

        } catch (Exception e) {
        }

        return v;
    }

}
