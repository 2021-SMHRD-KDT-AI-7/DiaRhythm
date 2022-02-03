package com.example.projecttest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;
//    private static final int num_pages = 5;
//    private ViewPager2 pager;
//    private FragmentStateAdapter pagerAdapter;
    ImageView img_write;
    TextView tv_id;
    Fragment4  fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnview = findViewById(R.id.bnview);
//        pager = findViewById(R.id.pager);
//        pagerAdapter = new ScreeSlidePagerAdapter(this);
//        pager.setAdapter(pagerAdapter);
        img_write = findViewById(R.id.img_write);
        tv_id = findViewById(R.id.tv_id);


        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.tab1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();

                } else if (item.getItemId() == R.id.tab2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment2()).commit();

                } else if (item.getItemId() == R.id.tab3) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment3()).commit();

                } else if (item.getItemId() == R.id.tab4) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment4()).commit();

                } else if (item.getItemId() == R.id.tab5) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment5()).commit();


                }

                return true;
            }
        });

        // id 값 받고 출력
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("string");
        tv_id.setText(id);

        //번들객체 생성, text값 저장 
        Bundle bundle = new Bundle(); 
        bundle.putString("id",id);


        // fragment4로 전달
        fragment4= new Fragment4();
        fragment4.setArguments(bundle);
        Log.v("frg4 id",id);

        // diary write
        img_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("string", id);
                startActivity(intent);

            }
        });


        int i = extras.getInt("integer");
    }




/*
    //뒤로가기 눌렀을때 처리
    // 뷰페이저2 동작하는 것과 상관 없으므로 (onBackPressed 메소드)생략 가능
    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem()==0){
            Toast.makeText(this, "뒤로가기가 눌렸습니다.", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        else{
            pager.setCurrentItem(pager.getCurrentItem()-1);
        }
    }
    //페이저와 프래그먼트 이어주기
    private class ScreeSlidePagerAdapter extends FragmentStateAdapter{
        public ScreeSlidePagerAdapter(FragmentActivity fa){
            super (fa);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {//포지션마다 있을 fragment설정
            if(position==0) return new Fragment1();
            else if(position==1) return new Fragment2();
            else if(position==2) return new Fragment3();
            else if(position==3) return new Fragment4();
            else return new Fragment5();
        }
        @Override
        public int getItemCount() {
            return num_pages; //페이지 수 지정.
            }
    }

*/
}