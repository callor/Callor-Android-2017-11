package com.callor.lession.myapp016;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by callor on 2018-01-02.
 */

public class ItemAdapter extends RecyclerView.Adapter {

    ArrayList<ItemVO> items ;
    Context context ;

    public ItemAdapter(ArrayList<ItemVO> items, Context myContext) {
        this.context = myContext;
        this.items = items;
    }
     // * item을 보여주기 위해 최초로 생성해야할 부분
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // itemView.xml 파일을 불러와서 사용할 준비
        View v = LayoutInflater.from(context)
                    .inflate(R.layout.item_view,parent,false);

        ItemHolder holder = new ItemHolder(v);
        return holder;
    }
    // 데이터를 View에 표현부분
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // RecylerView.ViewHolder 를 우리 만든 ItemHolder로 형변환
        ItemHolder itemHolder = (ItemHolder)holder;
        itemHolder.imageView.setImageResource(items.get(position).getImageName());
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 내장 클래스
    // 데이터와 itemview.xml(cardview)를 연동하는 클래스
    class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView imageText ;

        public ItemHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
