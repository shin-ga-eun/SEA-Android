package com.bugkillers.sea.activity.main.navigator.managementWork.list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.bugkillers.sea.R;
import com.bugkillers.sea.activity.main.MainActivity;
import com.bugkillers.sea.domain.dto.artItem.GetAnoAndTitleDto;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의 -> 입장 버튼에 사용.
    public interface ListBtnClickListener {
        void onListBtnClick(Long ano) ;
    }
    private ListBtnClickListener listBtnClickListener;

    public ListViewAdapter(ListBtnClickListener listBtnClickListener) {
        this.listBtnClickListener = listBtnClickListener;
    }

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;


    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_fragment_listwork, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView imgArtWork = (ImageView) convertView.findViewById(R.id.imgArtWork) ;
        final TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle) ;
        final TextView textAno = (TextView) convertView.findViewById(R.id.textAno);
        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textTitle.setText(listViewItem.getTitle());
        textAno.setText(""+listViewItem.getAno());
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBtnClickListener.onListBtnClick(Long.parseLong(""+listViewItem.getAno()));
            }
        });

        return convertView;
    }


    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(GetAnoAndTitleDto getAnoAndTitleDto) {
        ListViewItem item = new ListViewItem();

        item.setAno(getAnoAndTitleDto.getAno());
        item.setTitle(getAnoAndTitleDto.getTitle());

        listViewItemList.add(item);
    }

}
