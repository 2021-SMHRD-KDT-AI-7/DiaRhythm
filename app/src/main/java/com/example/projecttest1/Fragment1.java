package com.example.projecttest1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Fragment1 extends Fragment {

    ImageView img_write;
    ImageView jo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_1, container, false);

//        jo = v.findViewById(R.id.jo);
        img_write = v.findViewById(R.id.img_write);

        img_write.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {

               Intent intent = new Intent(getContext(),DiaryActivity.class);
               startActivity(intent);

         }
       });
        return v;





    }
}