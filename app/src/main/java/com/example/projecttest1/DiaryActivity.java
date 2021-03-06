package com.example.projecttest1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DiaryActivity extends AppCompatActivity {

    EditText et_content;
    EditText et_title;
    Button btn_submit;
    Button btn_cancel;
    RequestQueue queue;
    String emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        et_content = findViewById(R.id.et_write_content);
        et_title = findViewById(R.id.et_write_title);
        btn_submit = findViewById(R.id.btn_write_submit);
        btn_cancel = findViewById(R.id.btn_write_cancel);


        queue = Volley.newRequestQueue(getApplicationContext());
        // MainActivity 의 id 값 가져오기
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("string");

        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                //flask서버의 ip주소로 변경할 것
                //뒤에 라우터 경로 작성할 것
                String flask_url = "http://121.147.52.194:80/emotions";


                StringRequest request = new StringRequest(Request.Method.POST, flask_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                //Flask서버의 return문에 작성한 결과값을 response변수를 통해서 접근
                                Log.v("Flask응답값>> ", response);
                                emotion = response;
                                showCustomDialog();

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();

                        //flask서버로 전달할 데이터를
                        params.put("num1", et_title.getText().toString());
                        params.put("num2", et_content.getText().toString());
                        params.put("num3", id);

                        // params.put("num2","2");

                        return params;

                    }
                };

                queue.add(request);

                if (et_title.getText().length() != 0 && et_content.getText().length() != 0) {
                    try {
                        String result;
                        String title = et_title.getText().toString();
                        String content = et_content.getText().toString();
                        RegisterwriteActivity task = new RegisterwriteActivity();

                        result = task.execute(title, content, id, emotion).get();

                        Log.v("return", result);

//
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        intent.putExtra("string", id);
//                        startActivity(intent);


                    } catch (Exception e) {

                    }



                } else if (et_title.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "제목을 입력해주세요", Toast.LENGTH_SHORT).show();

                } else if (et_content.getText().toString().length() == 0) {

                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                }








            }





        });




        // btn_cancel 메소드
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
                intent.putExtra("string", id);
                startActivity(intent);
                finish();
            }
        });


    }




    private void showCustomDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this
                , R.style.AlertDialogTheme);

        View view = LayoutInflater.from(DiaryActivity.this).inflate(
                R.layout.dialog_custom, (LinearLayout)findViewById(R.id.layoutDialog));

        //다이얼로그 텍스트 설정
        builder.setView(view);
        ((TextView)view.findViewById(R.id.textTitle)).setText("AI Comment");
        ((TextView)view.findViewById(R.id.textMessage)).setText("그동안 고생하셨어요");
        ((Button)view.findViewById(R.id.btnOk)).setText("확인");

        AlertDialog alertDialog = builder.create();



        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                alertDialog.dismiss();

                    finish();
            }
        });

        //다이얼로그 형태 지우기
        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
    }

}