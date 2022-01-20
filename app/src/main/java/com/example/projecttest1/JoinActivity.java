package com.example.projecttest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class JoinActivity extends AppCompatActivity {

    EditText et_id_j,et_pw_j,et_name,et_address,et_pn;
    RadioButton rg_male,rg_female;
    Button btn_join_j,btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_id_j =findViewById(R.id.et_id_j);
        et_pw_j =findViewById(R.id.et_pw_j);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_pn = findViewById(R.id.et_pn);
        rg_male = findViewById(R.id.rg_male);
        rg_female = findViewById(R.id.rg_female);
        btn_join_j = findViewById(R.id.btn_join_j);
        btn_cancel =findViewById(R.id.btn_cancel);



        // 회원가입 메소드
        btn_join_j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String result;
                    String id = et_id_j.getText().toString();
                    String pw = et_pw_j.getText().toString();
                    String name = et_name.getText().toString();
                    String address = et_address.getText().toString();
                    String pn = et_pn.getText().toString();
                    String male = rg_male.getText().toString();
                    String female = rg_female.getText().toString();



                    RegisterActivity task = new RegisterActivity();
                    result = task.execute(id, pw,name,address,pn,male,female).get();
                } catch (Exception e) {
                    //Log.i("DB test", ".....ERROR.....!");
                }
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });



        // btn_cancel 메소드
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}