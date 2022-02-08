package com.example.projecttest1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class JoinActivity extends AppCompatActivity {

    EditText et_id_j,et_pw_j,et_name,et_address,et_pn;
    RadioGroup radioGroup;
    Button btn_join_j,btn_cancel;
    ListView lv;
    String gender="", date="sysdate", admin= "n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_id_j =findViewById(R.id.et_id_j);
        et_pw_j =findViewById(R.id.et_pw_j);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_pn = findViewById(R.id.et_pn);
        radioGroup = findViewById(R.id.rg_gender);
        btn_join_j = findViewById(R.id.btn_join_j);
        btn_cancel =findViewById(R.id.btn_cancel);
        lv = findViewById(R.id.lv);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rg_male:
                        gender = "m";
                        break;
                    case R.id.rg_female:
                        gender = "w";
                        break;
                }
            }
        });



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

                    // 아이디 중복시  토스트 넣기

                    RegisterJoinActivity task = new RegisterJoinActivity();
                    result = task.execute(id, pw,name,address,pn,gender,date,admin).get();
                    Log.v("MY", result);
                } catch (Exception e) {

                }
                //if(result.equale)
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        List<String> privacy = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.privacy,privacy);
        lv.setAdapter(adapter);
        privacy.add("다이어리듬은 「개인정보보호법」 제15조제1항제1호, 제17조제1항제1호, 제23조제1호, 제24조제1항 제1호에 따라\n" +
                "아래와 같이 개인정보의 수집. 이용에 관하여 귀하의 동의를 얻고자 합니다.\n" +
                "\n" +
                "다이어리듬은 이용자의 사전 동의 없이는 이용자의 개인정보를 함부로 공개하지 않으며, 수집된 정보는 아래와 같이 이용하고 있습니다.\n" +
                "이용자가 제공한 모든 정보는 아래의 목적에 필요한 용도 이외로는 사용되지 않으며 이용 목적이 변경될 시에는 이를 알리고 동의를 구할 것입니다.\n" +
                "\n" +
                "개인정보의 수집 및 이용 동의\n" +
                "1. 개인정보의 수집 및 이용 목적\n" +
                "가. 서비스 제공에 관한 업무 이행 - 컨텐츠 제공, 특정 맞춤 서비스 제공(일기데이터, 감정 그래프 등), 기업 애로상담\n" +
                "나. 회원관리\n" +
                "- 회원제 서비스 이용 및 제한적 본인 확인제에 따른 본인확인, 개인식별, 가입의사 확인, 가입 및 가입횟수 제한, 추후 법정 대리인 본인확인, 분쟁 조정을 위한 기록보존, 불만처리 등 민원처리, 공지사항 전달\n" +
                "\n" +
                "2. 수집하는 개인정보의 항목\n" +
                "<개인회원 가입>\n" +
                "필수항목 : 아이디, 비밀번호, 이름, 핸드폰번호, 이메일, 암호화된 이용자 확인값(CI)\n" +
                "선택항목 : 이메일 수신여부, 문자수신여부, 웹진구독여부\n" +
                "<자동수집>\n" +
                "IP주소, 쿠키, 서비스 이용기록, 방문기록 등\n" +
                "\n" +
                "3. 개인정보의 보유 및 이용기간\n" +
                "기업마당은 원칙적으로 보유기간의 경과, 개인정보의 수집 및 이용목적의 달성 등 그 개인정보가 불필요하게 되었을 때에는 지체 없이 파기합니다. 다만, 다른 법령에 따라 보존하여야 하는 경우에는 그러하지 않을 수 있습니다. 불필요하게 되었을 때에는 지체 없이 해당 개인정보를 파기합니다.\n" +
                "\n" +
                "회원정보\n" +
                "-탈퇴 후 지체없이 파기\n" +
                "\n" +
                "4. 동의거부권 및 불이익\n" +
                "정보주체는 개인정보 수집에 동의를 거부할 권리가 있습니다. 다만, 필수 항목에 대한 동의를 거부할 시 저희가 제공하는 서비스를 이용할 수 없습니다.");
        adapter.notifyDataSetChanged();

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