package com.example.projecttest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;
    
    ImageView img_write;
    TextView tv_id;
    
    // fragment 선언
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;

    /* 뷰페이저 선언
    private static final int num_pages = 5;
    private ViewPager2 pager;
    private FragmentStateAdapter pagerAdapter;
*/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        // fragment 생성
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();

        bnview = findViewById(R.id.bnview);
        img_write = findViewById(R.id.img_write);
        tv_id = findViewById(R.id.tv_id);

/*     뷰페이저 변수담기
        pager = findViewById(R.id.pager);
        pagerAdapter = new ScreeSlidePagerAdapter(this);
        pager.setAdapter(pagerAdapter);
*/

        // LoginActivity에서 id 값 넘겨받아 변수에 저장
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("string");

        // id 값 확인용 Textview
        //tv_id.setText(id);

        // BottomNavigationView 클릭 동작 메소드
        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // Activity -> fragment 로 데이터를 전달 할 Bundle 클래스 생성
                Bundle bundle = new Bundle();
                // bundle 변수에 id 값을 넣고 "id" 라는 key값 부여(값을 넘겨받을 때 key값으로 구분)
                bundle.putString("id",id);
                
                // 해당 탭 클릭 시 화면 전환
                if (item.getItemId() == R.id.tab1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                    fragment1.setArguments(bundle);
                    writeOn();

                } else if (item.getItemId() == R.id.tab2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();

                    fragment2.setArguments(bundle);
                    writeOn();

                } else if (item.getItemId() == R.id.tab3) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                    fragment3.setArguments(bundle);
                    writeOn();

                } else if (item.getItemId() == R.id.tab4) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment4).commit();

                    // 일기 읽기 페이지는 일기 쓰기 버튼 숨기기
                    img_write.setVisibility(View.INVISIBLE);

                    fragment4.setArguments(bundle);

                } else if (item.getItemId() == R.id.tab5) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();

                    fragment5.setArguments(bundle);
                    writeOn();

                }


                return true;
            }

        });


        // 일기 쓰기 메소드
        img_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("string", id);
                startActivity(intent);




            }
        });


       // int i = extras.getInt("integer");
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
    
    // 일기 쓰기 보여주기 메소드
    public void writeOn() {
        img_write.setVisibility(View.VISIBLE);
    }



}


