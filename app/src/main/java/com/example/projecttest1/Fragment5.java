package com.example.projecttest1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


public class Fragment5 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_5, container, false);

//    sc_view.post(new Runnable() {
//        @Override
//        public void run() {
//            sc_view.fullScroll((sc_view.FOCUS_UP));
//        }
//    });







        return v;

    }
}