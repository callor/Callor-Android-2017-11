package com.callor.lession.naverfrend;

import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.callor.lession.naverfrend.data.BookVO;
import com.squareup.picasso.Picasso;

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

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_view,parent,false);

        RecyclerView.ViewHolder bookHolder =  new BookHolder(view);
        return bookHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BookHolder bookHolder = (BookHolder)holder ; // parent holder를 BookHoder로 형변환


        String bookTitle = books.get(position).getTitle();
        bookHolder.txt_title.setText(getHTML(bookTitle));

        String bookDesc = books.get(position).getDesciption();
        bookHolder.txt_desc.setText(getHTML(bookDesc));

        String imageLink = books.get(position).getImage();
        Picasso.with(bookHolder.itemView.getContext()) // (부모)누구한테
            .load(imageLink) // 무엇을
            .into(bookHolder.img_book); // 실제 이미지를 붙일 위젯


        //
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private Spanned getHTML(String strText) {
        Spanned spText ;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spText = Html.fromHtml(strText,Html.FROM_HTML_MODE_LEGACY);
        } else {
            spText = Html.fromHtml(strText);
        }
        return spText ;
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        public TextView txt_title ;
        public ImageView img_book ;
        public TextView txt_desc ;

        public BookHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.book_item_title);
            img_book = itemView.findViewById(R.id.book_item_image);
            txt_desc = itemView.findViewById(R.id.book_item_desc);
        }
    }
}
