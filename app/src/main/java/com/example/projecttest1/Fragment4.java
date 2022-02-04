package com.example.projecttest1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.w3c.dom.Text;

import java.util.Collections;


public class Fragment4 extends Fragment {

    TextView tv_test;
    TextView tv_title_read;
    TextView tv_content_read ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_4, container, false);

        MaterialCalendarView materialCalendarView = v.findViewById(R.id.calendarView);
        materialCalendarView.setSelectedDate(CalendarDay.today());

        materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.today())));

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

        tv_test = v.findViewById(R.id.tv_test);
        tv_content_read = v.findViewById(R.id.tv_content_read);
        tv_title_read = v.findViewById(R.id.tv_title_read);


        // MainActivity에서 전달한 번들 저장
        Bundle bundle = getArguments();
        if(bundle != null) {
            String id = bundle.getString("id");
            tv_test.setText(id);
            Log.v("번들 값", id);
        }


        return v;

    }
}