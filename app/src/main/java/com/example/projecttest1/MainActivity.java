package com.example.projecttest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnview = findViewById(R.id.bnview);

        bnview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.tab1){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment1()).commit();

                }else if(item.getItemId()==R.id.tab2){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment2()).commit();

                }else if(item.getItemId()==R.id.tab3){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment3()).commit();

                }else if(item.getItemId()==R.id.tab4){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment4()).commit();

                }else if(item.getItemId()==R.id.tab5){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,new Fragment5()).commit();

                }

                return true;
            }
        });



    }
}