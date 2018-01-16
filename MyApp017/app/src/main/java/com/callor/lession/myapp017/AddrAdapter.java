package com.callor.lession.myapp017;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by callor on 2018-01-09.
 */


/*
 1. Adapter 클래스 생성
 2. RecyclerView.Adapter를 상속
 3. imprement method
  */
public class AddrAdapter extends RecyclerView.Adapter{


    ArrayList<AddrVO> items;
    Context context ;


    // 외부에서 Adapter에 데이터를 전달해주기 위해
    // Adapter에 생성자를 만들어준다.
    // 4. 생성자를 만들어서 외부로부터 데이터를 전달 받기 위한 통로열기
    public AddrAdapter(ArrayList<AddrVO> items, Context context) {
        this.items = items;
        this.context = context;
    }

    // 6. onCreateViweHolder Method 정의
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.itemview,parent,false);

        AddrHolder holder = new AddrHolder(view);
        return holder; // return null  앱 오류가 발생해서 시작

    }

    // 데이터와 holder를 서로 연결해주는 부분
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        AddrHolder addrHolder = (AddrHolder)holder; // holder를 형변환 시켜야 한다.

//        addrHolder.imageView.setImageResource(items.get(position).getIntImage());
        addrHolder.txt_name.setText(items.get(position).getStrName());
        addrHolder.txt_remark.setText(items.get(position).getStrRemark());

    }

    // getItemCount 의 return 값을 0으로 두면
    // RecyclerView에 아무 것도 나타 나지 않는다.
    // 5. getItemCount의 리턴값을 수정
    @Override
    public int getItemCount() {

        return items.size(); // return 0  => 설정하지 않으면 list가 전혀 보이지 않는다.
    } //


    class AddrHolder extends RecyclerView.ViewHolder {

        public ImageView imageView ;
        public TextView txt_name ;
        public TextView txt_remark ;

        public AddrHolder(View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.my_icon);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_remark = itemView.findViewById(R.id.txt_remark);
        }
    }
}
