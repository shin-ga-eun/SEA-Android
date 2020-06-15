package com.bugkillers.sea.activity.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.login.Login;
import com.bugkillers.sea.domain.dto.member.IsMemberDto;
import com.bugkillers.sea.domain.dto.member.MemberJoinDto;
import com.bugkillers.sea.domain.dto.member.MemberLoginDto;
import com.bugkillers.sea.domain.entity.member.MemberRole;
import com.bugkillers.sea.network.NetRetrofit;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    TextInputEditText name, birth, phone, address, email, password;
    Button signUp;
    MemberRole isRole;
    String isSignUp;
    RadioButton artist,consumer;
    MemberLoginDto memberLoginDto;

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
        Intent intent = getIntent();

        if(intent.getStringExtra("email") !=null){
            final String alreadyEmail = intent.getStringExtra("email");
            final String alreadyName = intent.getStringExtra("name");

            Log.d("alreadyEmail", alreadyEmail);
            Log.d("alreadyName", alreadyName);
            IsMemberDto isMemberDto = new IsMemberDto();
            isMemberDto.setEmail(alreadyEmail);

            Call<MemberLoginDto> response =NetRetrofit.getInstance().getNetRetrofitInterface().isMember(isMemberDto);
            response.enqueue(new Callback<MemberLoginDto>() {
                @Override
                public void onResponse(Call<MemberLoginDto> call, Response<MemberLoginDto> response) {
                    if(response.isSuccessful()){
                        memberLoginDto = response.body();
                        String isEmail = memberLoginDto.getEmail();
                        String isPassword = memberLoginDto.getPassword();

                        if(!(isEmail.equals("None"))){
                            Intent loginIntent = new Intent(getApplicationContext(),Login.class);
                            loginIntent.putExtra("email",isEmail);
                            loginIntent.putExtra("password",isPassword);
                            startActivity(loginIntent);
                            finish();
                        }
                        if(memberLoginDto.getEmail().equals("None")){
                            System.out.println("회원이 아닙니다 ");
                        }
                    }
                }

                @Override
                public void onFailure(Call<MemberLoginDto> call, Throwable t) {
                    Log.d("Err", t.getMessage());
                }
            });
        }

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRole=MemberRole.ARTIST;
                System.out.println(isRole);
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRole=MemberRole.CUSTOMER;
                System.out.println(isRole);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberJoinDto memberJoinDto = new MemberJoinDto();
                memberJoinDto.setName(name.getText().toString());
                memberJoinDto.setBirthday(birth.getText().toString());
                memberJoinDto.setPhone(phone.getText().toString());
                memberJoinDto.setLocation(address.getText().toString());
                memberJoinDto.setEmail(email.getText().toString());
                memberJoinDto.setPassword(password.getText().toString());
                memberJoinDto.setRole(isRole);

                Call<Void> response= NetRetrofit.getInstance().getNetRetrofitInterface().joinMember(memberJoinDto);
                response.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        isSignUp = name.getText().toString() + "님," + isRole.toString() + "의 계정으로 회원가입이 완료되었습니다!";
                        Toast.makeText(SignUp.this, isSignUp, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(SignUp.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}