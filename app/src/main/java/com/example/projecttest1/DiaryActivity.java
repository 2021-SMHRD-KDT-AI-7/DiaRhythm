package com.example.projecttest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiaryActivity extends AppCompatActivity {

    EditText et_content;
    EditText et_title;
    Button btn_submit;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        et_content = findViewById(R.id.et_write_content);
        et_title = findViewById(R.id.et_write_title);
        btn_submit = findViewById(R.id.btn_write_submit);
        btn_cancel = findViewById(R.id.btn_write_cancel);


        // MainActivity 의 id 값 가져오기
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("string");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(et_title.getText().length() !=0 && et_content.getText().length()!=0){
                    try {
                        String result;
                        String title = et_title.getText().toString();
                        String content = et_content.getText().toString();
                        RegisterwriteActivity task = new RegisterwriteActivity();
                        result = task.execute(title,content,id).get();

                        Log.v("return", result);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("string",id);
                        startActivity(intent);
                        finish();

                    } catch (Exception e) {

                    }

                }else if(et_title.getText().toString().length()==0){
                        Toast.makeText(getApplicationContext(),"제목을 입력해주세요",Toast.LENGTH_SHORT).show();

                }else if(et_content.getText().toString().length()==0){

                    Toast.makeText(getApplicationContext(),"내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                }




            }

        });


        // btn_cancel 메소드
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
                intent.putExtra("string",id);
                startActivity(intent);
                finish();
            }
        });




    }
}