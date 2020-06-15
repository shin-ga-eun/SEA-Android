package com.bugkillers.sea.activity.main.navigator.managementWork.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
import com.bugkillers.sea.network.NetRetrofit;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;

public class ListWorkFragment extends Fragment {

    MainActivity mainActivity;

    ImageView imgArtWork;
    TextView textTitle;
    Button btnDetail;

    static final int UPDATE = 0;
    static final int LIST = 1;
    static final int DETAIL = 2;

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
        final View root = inflater.inflate(R.layout.item_fragment_listwork, container, false);

        imgArtWork = (ImageView) root.findViewById(R.id.imgArtWork);
        btnDetail = (Button) root.findViewById(R.id.btnDetail);
        textTitle = (TextView) root.findViewById(R.id.textTitle);

        //btnDetail click
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onFragmentChange(DETAIL);
            }
        });

        return root;
    }
}