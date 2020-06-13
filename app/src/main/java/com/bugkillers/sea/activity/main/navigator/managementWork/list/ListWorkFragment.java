package com.bugkillers.sea.activity.main.navigator.managementWork.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.activity.main.navigator.managementWork.update.UpdateWorkFragment;
import com.bugkillers.sea.activity.signUp.SignUp;

public class ListWorkFragment extends Fragment {

    MainActivity mainActivity;

    ImageView imgArtWork;
    TextView textHash;
    Button btnUpdateWork, btnDeleteWork;

    static final int UPDATE = 0;
    static final int DELETE = 1;

    //MainActivity 참조
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    //MainActivity 참조안함
    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        //fragment를 inflate해주고 container에 붙임.
        super.onCreateView(inflater, container, savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_listwork, container, false);

        btnUpdateWork = (Button) root.findViewById(R.id.btnUpdateWork);
        btnDeleteWork = (Button) root.findViewById(R.id.btnDeleteWork);

        //button click
        btnUpdateWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(UPDATE);

            }
        });

        btnDeleteWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainActivity.onFragmentChange(DELETE);

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                alert_confirm.setMessage("작품 리스트에서 삭제하시겠습니까?\n현재 대여중인 작품이라면 대여기간 종료 후, 다시 시도해주세요..").setCancelable(false).setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(),"작품이 삭제되었습니다..",Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                AlertDialog alert = alert_confirm.create();
                alert.show();

            }
        });
        return root;
    }
}