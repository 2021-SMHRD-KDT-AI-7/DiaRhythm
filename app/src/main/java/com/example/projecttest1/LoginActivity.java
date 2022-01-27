package com.example.projecttest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et_id,et_pw;
    Button btn_login, btn_join;
    String result;
    String result_rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setTitle("ORACLE");

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_join = (Button)findViewById(R.id.btn_join);
        et_id = (EditText)findViewById(R.id.et_id);
        et_pw = (EditText)findViewById(R.id.et_pw);



        // 로그인 Activity 메소드
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    //String result;

                    String id = et_id.getText().toString();
                    String pw = et_pw.getText().toString();


                    RegisterLoginActivity task = new RegisterLoginActivity();
                    result = task.execute(id, pw).get();
                    Log.v("MY", result);
                    result_rep = result.replace(" ","");
                    Log.v("MY", result_rep);

                } catch (Exception e) {

                }
                if(result_rep.equals("1")) {

                    String id = et_id.getText().toString();

                    Toast.makeText(getApplicationContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("string",id);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT).show();
                }

            }

        });
        // 회원가입 Activity 메소드
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //String id = et_id.getText().toString();

        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        Intent intent = new Intent();
//                intent.putExtra("string",id);
//        startActivity(intent);


    }
}