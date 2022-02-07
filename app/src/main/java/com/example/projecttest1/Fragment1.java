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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_1, container, false);

        /*
        if(bundle != null) {
             Bundle bundle = getArguments();
             String id = bundle.getString("id");
        }
*/
        TextView tv_saying_content = v.findViewById(R.id.tv_saying_content);

        // 명언 가져오기(DB 연동)
        try {
            String result;

            //Log.v("프래그먼트 1의 id 값은?",id);


            RegistersayingActivity task = new RegistersayingActivity();
            result = task.execute().get().replace("    ", "");
            Log.v("return", result);


            tv_saying_content.setText(result);

        } catch (Exception e) {
        }

        return v;
    }

}
