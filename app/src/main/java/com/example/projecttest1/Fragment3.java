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


public class Fragment3 extends Fragment {

    String result;
    TextView tv_song_id;
    ImageView img_album1;
    ImageView img_album2;
    ImageView img_album3;
    TextView tv_album1;
    TextView tv_album2;
    TextView tv_album3;
    ImageView img_item1;
    ImageView img_item2;
    TextView tv_item1;
    TextView tv_item2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3, container, false);

        Bundle bundle = getArguments();
        String id = bundle.getString("id");

        tv_song_id = v.findViewById(R.id.tv_song_id);

        img_album1 = v.findViewById(R.id.img_album1);
        img_album2 = v.findViewById(R.id.img_album2);
        img_album3 = v.findViewById(R.id.img_album3);

        tv_album1 = v.findViewById(R.id.tv_album1);
        tv_album2 = v.findViewById(R.id.tv_album2);
        tv_album3 = v.findViewById(R.id.tv_album3);

        img_item1 = v.findViewById(R.id.img_item1);
        img_item2 = v.findViewById(R.id.img_read3);
        tv_item1 = v.findViewById(R.id.tv_item1);
        tv_item2 = v.findViewById(R.id.tv_item2);


        try {

            RegisterMusicActivity task = new RegisterMusicActivity();
            result = task.execute(id).get().replace("    ", "");


            //맨 처음, 맨 마지막 대괄호 제거
            result = result.replace("[","");
            result = result.replace("]","");
            result = result.replace(",","");
            Log.v("return", result);
            
            // toString 을 " "(공백)을 기준으로 잘라 array 배열에 저장하기
            String[] array = result.split("&&");


            tv_song_id.setText(id+"님을 위한 노래");

            // 노래 제목 + 가수 이름
            tv_album1.setText(array[0]+" - "+array[1]);
            tv_album2.setText(array[5]+" - "+array[6]);
            tv_album3.setText(array[10]+" - "+array[11]);
            // 이미지 url 불러오기
            String albumUrl1 = array[2];
            String albumUrl2 = array[7];
            String albumUrl3 = array[12];
            Log.v("콜드플레이 주소값",albumUrl3);
            Glide.with(this).load(albumUrl1).into(img_album1);
            Glide.with(this).load(albumUrl2).into(img_album2);
            Glide.with(this).load(albumUrl3).into(img_album3);



            tv_item1.setText(array[3]);
            tv_item2.setText(array[8]);
            String goodsUrl1 = array[4];
            String goodsUrl2 = array[9];
            Glide.with(this).load(goodsUrl1).into(img_item1);
            Glide.with(this).load(goodsUrl2).into(img_item2);



        } catch (Exception e) {

        }




        return v;


    }


}