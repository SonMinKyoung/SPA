package com.example.spa.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.spa.R;
import com.example.spa.Request.SignupRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    private EditText et_id, et_pw, et_name, et_pn, et_email, et_cn;
    private Button btn_signup;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // activity 실생 시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_name = findViewById(R.id.et_name);
        et_pn = findViewById(R.id.et_pn);
        et_email = findViewById(R.id.et_email);
        et_cn = findViewById(R.id.et_cn);

        // 회원가입 버튼 클릭시 실행
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //EditText에 현재 입력되어있는 값을 get()해온다.
                String userID= et_id.getText().toString();
                String userPassword= et_pw.getText().toString();
                String userEmail= et_email.getText().toString();
                String userName= et_name.getText().toString();
                String userPhone= et_pn.getText().toString();
                String userCarnumber= et_cn.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //json & ajacks -> posing?을 해준다
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // 성공연결여부를 알려준다.
                            boolean success = jsonObject.getBoolean("success");

                            if (success) { //회원등록 성공
                                Toast.makeText(getApplicationContext(), "회원 가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 회원등록 실패
                                Toast.makeText(getApplicationContext(), "회원 가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                };
                SignupRequest signupRequest = new SignupRequest(userID,userPassword,userPassword,userName,userPhone,userEmail,userCarnumber);
                RequestQueue queue =Volley.newRequestQueue(signupRequest.this);
                queue.add(signupRequest)

            }
        });





    }
}