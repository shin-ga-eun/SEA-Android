package com.bugkillers.sea.activity.main.navigator.managementWork.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bugkillers.sea.R;

import java.util.ArrayList;

public class ListWorkListViewAdapter extends BaseAdapter {

    private ArrayList<ListWorkListViewItem> listViewItemList = new ArrayList<>();

    public ListWorkListViewAdapter() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //roomlist_item 레이아웃을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_fragment_listwork, parent, false);
        }

        //화면에 표시될 View(Layout이 inflate로 된)으로부터 위젯에 대한 참조 획득
        TextView textHash = (TextView) convertView.findViewById(R.id.textHash);
        ImageView imgArtWork = (ImageView) convertView.findViewById(R.id.imgArtWork);

        //Data set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListWorkListViewItem listViewItem = listViewItemList.get(position);

        textHash.setText(listViewItem.getTextHash());

        //버튼 클릭
        Button btnUpdateWork = (Button) convertView.findViewById(R.id.btnUpdateWork);
        btnUpdateWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnDeleteWork = (Button) convertView.findViewById(R.id.btnDeleteWork);
        btnDeleteWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    //아이템 데이터 추가를 위한 함수
    public void addItem(String hashtag){
        ListWorkListViewItem item = new ListWorkListViewItem();

        item.setTextHash(hashtag);

        listViewItemList.add(item);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
