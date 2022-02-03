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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_4, container, false);

        MaterialCalendarView materialCalendarView = v.findViewById(R.id.calendarView);
        materialCalendarView.setSelectedDate(CalendarDay.today());

        materialCalendarView.addDecorator(new EventDecorator(Color.GREEN, Collections.singleton(CalendarDay.today())));

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

        TextView tv_title_read = v.findViewById(R.id.tv_title_read);
        TextView tv_content_read = v.findViewById(R.id.tv_content_read);


        try {
            //RequestActivity에서 전달한 번들 저장
            Bundle bundle = getArguments();

            //번들 안의 텍스트 불러오기
            String text = bundle.getString("id");

            //fragment1의 TextView에 전달 받은 text 띄우기
            tv_title_read.setText(text);
        }
        catch (Exception e){

        }

        // 일기 가져오기(DB 연동)
        /*try {
            String result;

            RegisterReaddiaryActivity task = new RegisterReaddiaryActivity();
            result = task.execute(id).get();
            Log.v("return", result);

            tv_title_read.setText(result);
            tv_content_read.setText(result);

        } catch (Exception e) {
        }
*/
        return v;

    }



}