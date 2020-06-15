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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.activity.main.navigator.managementWork.update.UpdateWorkFragment;
import com.bugkillers.sea.activity.signUp.SignUp;
import com.bugkillers.sea.domain.dto.artItem.GetAnoAndTitleDto;
import com.bugkillers.sea.network.NetRetrofit;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.DELETE;

public class ListWorkFragment extends ListFragment implements ListViewAdapter.ListBtnClickListener {

    MainActivity mainActivity;

    static final int UPDATE = 0;
    static final int LIST = 1;
    static final int DETAIL = 2;

    ListViewAdapter adapter;

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

        adapter = new ListViewAdapter(this);
        setListAdapter(adapter);

        //list 서버연동
        Call<List<GetAnoAndTitleDto>> response = NetRetrofit.getInstance().getNetRetrofitInterface().getListArtItem();
        response.enqueue(new Callback<List<GetAnoAndTitleDto>>() {
            @Override
            public void onResponse(Call<List<GetAnoAndTitleDto>> call, Response<List<GetAnoAndTitleDto>> response) {
                if(response.isSuccessful()){
                    List<GetAnoAndTitleDto> list = response.body();

                    for(GetAnoAndTitleDto getAnoAndTitleDto: list){
                        adapter.addItem(getAnoAndTitleDto);
                        Log.d("Retrofit title: ",getAnoAndTitleDto.getTitle());
                        Log.d("Retrofit ano: ",getAnoAndTitleDto.getAno().toString());

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<GetAnoAndTitleDto>> call, Throwable t) {
                Log.d("Err", t.getMessage());
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onListBtnClick(Long ano) {
        mainActivity.setAno(ano);
        mainActivity.onFragmentChange(DETAIL);
    }
}