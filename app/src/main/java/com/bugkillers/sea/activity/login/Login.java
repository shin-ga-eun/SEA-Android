package com.bugkillers.sea.activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.activity.signUp.SignUp;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText id,password;
    Button login_btn,login_kakao_btn;
    TextView findPassword,signUp;
    String isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=(TextInputEditText)findViewById(R.id.id);
        password=(TextInputEditText)findViewById(R.id.password);
        login_btn=(Button)findViewById(R.id.login_btn);
        login_kakao_btn=(Button)findViewById(R.id.login_kakao_btn);
        signUp=(TextView)findViewById(R.id.signUp);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin=id.getText().toString()+"/"+password.getText().toString();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(Login.this,"로그인정보: "+isLogin,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });

        login_kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLogin=id.getText().toString()+"/"+password.getText().toString();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(Login.this,"로그인정보: "+isLogin,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(Login.this,"회원가입 화면으로 넘어갑니다",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), SignUp.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });
    }
}