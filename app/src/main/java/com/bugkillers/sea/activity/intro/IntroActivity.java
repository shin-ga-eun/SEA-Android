package com.bugkillers.sea.activity.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.login.Login;
import com.bugkillers.sea.activity.signUp.SignUp;

public class IntroActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent (getApplicationContext(), Login.class);
                startActivity(intent); //다음화면으로 넘어감
                finish();
            }
        },2000);//2초
    }
}
