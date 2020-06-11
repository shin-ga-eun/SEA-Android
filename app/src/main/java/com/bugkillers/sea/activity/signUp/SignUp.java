package com.bugkillers.sea.activity.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.login.Login;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    TextInputEditText name, birth, phone, address, email, password;
    Button signUp;
    String isRole;
    String isSignUp;
    RadioButton artist,consumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       RadioGroup role = (RadioGroup) findViewById(R.id.role);
        name = (TextInputEditText) findViewById(R.id.name);
        birth = (TextInputEditText) findViewById(R.id.birth);
        phone = (TextInputEditText) findViewById(R.id.phone);
        address = (TextInputEditText) findViewById(R.id.address);
        email = (TextInputEditText) findViewById(R.id.email);
        password = (TextInputEditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signUp);
        artist=(RadioButton)findViewById(R.id.artist);
        consumer=(RadioButton)findViewById(R.id.consumer);

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRole="artist";
                System.out.println(isRole);
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRole="consumer";
                System.out.println(isRole);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        isSignUp=name.getText().toString()+"님,"+isRole+"의 계정으로 회원가입이 완료되었습니다!";
                        Toast.makeText(SignUp.this, isSignUp, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });
    }
}