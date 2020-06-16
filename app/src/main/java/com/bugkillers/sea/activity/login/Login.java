package com.bugkillers.sea.activity.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.CustomerActivity;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.activity.signUp.SignUp;
import com.bugkillers.sea.domain.dto.member.IsMemberDto;
import com.bugkillers.sea.domain.dto.member.LoginResponseDto;
import com.bugkillers.sea.domain.dto.member.MemberLoginDto;
import com.bugkillers.sea.network.NetRetrofit;
import com.google.android.material.textfield.TextInputEditText;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextInputEditText id,password;
    Button login_btn,login_kakao_btn;
    TextView findPassword,signUp;
    SharedPreferences memberInfo;
    SharedPreferences.Editor loginEditor;
    LoginResponseDto loginResponseDto;
    LoginResponseDto kakaoResponseDto;
    String getToken, getRole;
    SessionCallback sessionCallback = new SessionCallback();
    Session session;
    String kakaoEmail=null;
    MemberLoginDto originalMemberLoginDto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=(TextInputEditText)findViewById(R.id.id);
        password=(TextInputEditText)findViewById(R.id.password);
        login_btn=(Button)findViewById(R.id.login_btn);
        login_kakao_btn=(Button)findViewById(R.id.login_kakao_btn);
        signUp=(TextView)findViewById(R.id.signUp);
        memberInfo=getSharedPreferences("memberInformation", Activity.MODE_PRIVATE);
        loginEditor=memberInfo.edit();

        session = Session.getCurrentSession();
        session.addCallback(sessionCallback);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originalMemberLoginDto =new MemberLoginDto();
                originalMemberLoginDto.setEmail(id.getText().toString());
                originalMemberLoginDto.setPassword(password.getText().toString());

                Call<LoginResponseDto> response = NetRetrofit.getInstance().getNetRetrofitInterface().loginMember(originalMemberLoginDto);
                response.enqueue(new Callback<LoginResponseDto>() {
                    @Override
                    public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {

                        if(response.isSuccessful()) {

                            loginResponseDto = response.body();
                            getToken = loginResponseDto.getToken();
                            getRole = loginResponseDto.getRole();

                            loginEditor.putString("TOKEN",getToken);
                            loginEditor.putString("ROLE", getRole);
                            loginEditor.commit();

                        }
                        System.out.println("role>>>>>>"+getRole);
                        if(getRole.equals("CUSTOMER")){
                            Toast.makeText(Login.this,"ROLE: "+memberInfo.getString("ROLE","")+"계정으로 로그인 완료",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (getApplicationContext(), CustomerActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        if(getRole.equals("ARTIST")) {
                            Toast.makeText(Login.this, "ROLE: " + memberInfo.getString("ROLE", "") + "계정으로 로그인 완료", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                        Toast.makeText(Login.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        login_kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.open(AuthType.KAKAO_LOGIN_ALL,Login.this);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Toast.makeText(Login.this,"회원가입 화면으로 넘어갑니다",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), SignUp.class);
                        startActivity(intent);
                        finish();

            }
        });
    }

    public class SessionCallback implements ISessionCallback {

        // 로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            requestMe();
        }

        // 로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
        }

        // 사용자 정보 요청
        public void requestMe() {
            UserManagement.getInstance()
                    .me(new MeV2ResponseCallback() {
                        @Override
                        public void onSessionClosed(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                        }

                        @Override
                        public void onFailure(ErrorResult errorResult) {
                            Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                        }

                        @Override
                        public void onSuccess(MeV2Response result) {
                            final Intent intent = new Intent(getApplicationContext(), SignUp.class);

                            Log.i("KAKAO_API", "사용자 아이디: " + result.getId());

                            UserAccount kakaoAccount = result.getKakaoAccount();
                            if (kakaoAccount != null) {

                                // 이메일
                                String email = kakaoAccount.getEmail();

                                if (email != null) {
                                    Log.i("KAKAO_API", "email: " + email);
                                    kakaoEmail =email;
                                    System.out.println("카카오 메소드 안에서의 카카오 이메일 "+ kakaoEmail);
                                    intent.putExtra("email",email);
                                    IsMemberDto isMemberDto = new IsMemberDto();
                                    isMemberDto.setEmail(kakaoEmail);
                                    System.out.println("isMemberDto에서의 이메일 "+isMemberDto.getEmail());
                                    Call<LoginResponseDto> kakaoResponse = NetRetrofit.getInstance().getNetRetrofitInterface().isMember(isMemberDto);
                                    kakaoResponse.enqueue(new Callback<LoginResponseDto>() {
                                        @Override
                                        public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                                            if(response.isSuccessful()){
                                                 kakaoResponseDto = response.body();
                                                 if(kakaoResponseDto.getRole().equals("None")){

                                                 }
                                                if(!(kakaoResponseDto.getRole().equals("None"))){
                                                    loginEditor.putString("TOKEN",kakaoResponseDto.getToken());
                                                    loginEditor.putString("ROLE", kakaoResponseDto.getRole());
                                                    loginEditor.commit();

                                                    if(memberInfo.getString("ROLE","").equals("CUSTOMER")){
                                                        Toast.makeText(Login.this,"ROLE: "+memberInfo.getString("ROLE","")+"계정으로 로그인 완료",Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent (getApplicationContext(), CustomerActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    if(memberInfo.getString("ROLE","").equals("ARTIST")) {
                                                        Toast.makeText(Login.this, "ROLE: " + memberInfo.getString("ROLE", "") + "계정으로 로그인 완료", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<LoginResponseDto> call, Throwable t) {

                                        }
                                    });

                                } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 이메일 획득 가능
                                    // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.

                                } else {
                                    // 이메일 획득 불가
                                }

                                // 프로필
                                Profile profile = kakaoAccount.getProfile();

                                if (profile != null) {
                                    Log.d("KAKAO_API", "nickname: " + profile.getNickname());
                                    intent.putExtra("name",profile.getNickname());
                                    Log.d("KAKAO_API", "profile image: " + profile.getProfileImageUrl());
                                    Log.d("KAKAO_API", "thumbnail image: " + profile.getThumbnailImageUrl());

                                } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                                    // 동의 요청 후 프로필 정보 획득 가능

                                } else {
                                    // 프로필 획득 불가
                                }

                                startActivity(intent);
                                finish();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        // Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}