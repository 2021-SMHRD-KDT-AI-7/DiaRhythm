package com.example.projecttest1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RegisterReaddiaryActivity extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    InputStream inputStream = null;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;


            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://211.228.61.227:8081/project/ReaddiaryDB.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "&id=" + strings[0];

            Log.v("data ", sendMsg);
            System.out.println(sendMsg);

            osw.write(sendMsg);
            osw.flush();

            //jsp 와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                // jsp 에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                // 통신 실패
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //jsp 로부터 받은 리턴 값
        return receiveMsg;
    }

}