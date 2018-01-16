package com.callor.lession.my0018;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by callor on 2018-01-16.
 */

public class MemoAdapter extends RecyclerView.Adapter {

    Context context = null ;
    List<MemoVO> memos = new ArrayList<MemoVO>();

    // 외부에서 Adapter에 데이터를 보내주기 위한 통로
    public MemoAdapter(Context context, List<MemoVO> memos) {
        this.context = context;
        this.memos = memos ;
    }

    /**
     * context_main.xml의 recyler에 memo_item.xml 을 연결
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.memo_item,parent,false) ;

        MemoHolder holder = new MemoHolder(view);
        return holder;
    }

    /**
     * Data 와 View 연동
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // RecyclerView.ViewHolder 를 MemoHoder로 변환
        MemoHolder memoHolder = (MemoHolder)holder ;

        memoHolder.txt_date.setText(memos.get(position).getStrDate());
        memoHolder.txt_memo.setText(memos.get(position).getStrMemo());

    }

    @Override
    public int getItemCount() {
        return memos.size();
    }


    class MemoHolder extends RecyclerView.ViewHolder {

        public TextView txt_date ;
        public TextView txt_memo ;

        public MemoHolder(View itemView) {
            super(itemView);

            txt_date = itemView.findViewById(R.id.txt_item_date);
            txt_memo = itemView.findViewById(R.id.txt_item_memo);

        }
    }



}









