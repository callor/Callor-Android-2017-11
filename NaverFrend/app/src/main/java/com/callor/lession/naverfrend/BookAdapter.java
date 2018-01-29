package com.callor.lession.naverfrend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.lession.naverfrend.data.BookVO;

import java.util.List;

/**
 * Created by callor on 2018-01-29.
 */

public class BookAdapter extends RecyclerView.Adapter{

    List<BookVO.BookItemVO> books ;



    public BookAdapter(List<BookVO.BookItemVO> books) {
        this.books = books;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder bookHolder =  new BookHolder(view);

        return bookHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        public BookHolder(View itemView) {
            super(itemView);

        }
    }


}
