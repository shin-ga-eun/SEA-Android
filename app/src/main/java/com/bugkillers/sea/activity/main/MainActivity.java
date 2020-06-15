package com.bugkillers.sea.activity.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.navigator.managementWork.list.ListWorkFragment;
import com.bugkillers.sea.activity.main.navigator.managementWork.update.UpdateWorkFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    static final int UPDATE = 0;
    static final int LIST = 1;

    private AppBarConfiguration mAppBarConfiguration;
    ListWorkFragment listWorkFragment;
    UpdateWorkFragment updateWorkFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //메인네비게이터
        setContentView(R.layout.activity_main);

        //프래그먼트 화면 등록
        listWorkFragment = new ListWorkFragment();
        updateWorkFragment = new UpdateWorkFragment();

        //헤더 툴바
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //우측하단 챗봇버튼
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "챗봇버튼", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //메인네비게이터
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_mainhome, R.id.nav_registerwork, R.id.nav_listwork, R.id.nav_rentalstatus, R.id.nav_chat, R.id.nav_notice)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    //프래그먼트 화면 전환
    public void onFragmentChange(int index){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        if(index == UPDATE){
            navController.navigate(R.id.nav_updatework);
        }
        else if(index == LIST){
            navController.navigate(R.id.nav_listwork);
        }
    }

    //메인헤더 관련 - 마이페이지 아이콘 클릭시 이동하는 방법?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onGroupItemClick(MenuItem item) {
        Toast.makeText(getApplicationContext(),"마이페이지", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}