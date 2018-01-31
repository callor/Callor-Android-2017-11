package com.callor.lession.navermovie;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by callor on 2018-01-31.
 */

public class Movie_Adapter extends RecyclerView.Adapter{

    List<MovieVO> movies ;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item_view,parent,false);


        // xml 파일을 펼치고
        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());

        // 펼친 xml파일을 이용해서 view를 만든다
        View view = layoutInflater
                .inflate(R.layout.movie_item_view,parent,false);

        RecyclerView.ViewHolder movieHolder = new MovieHolder(view);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MovieHolder movieHolder = (MovieHolder)holder ;

        movieHolder.txt_title.setText(getHTML(movies.get(position).getTitle()));
        movieHolder.txt_director.setText(getHTML(movies.get(position).getDirector()));
        movieHolder.txt_actor.setText(getHTML(movies.get(position).getActor()));

        Picasso.with(movieHolder.itemView.getContext())
                .load(movies.get(position).getImage()) // image link
                .into(movieHolder.img_image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 문자열 속에 포함된 HTML 코드를 실제 화면에 보이도록 변경
    private Spanned getHTML(String strText) {
        Spanned spText ;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spText = Html.fromHtml(strText,Html.FROM_HTML_MODE_LEGACY);
        } else {
            spText = Html.fromHtml(strText);
        }
        return spText ;
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        TextView txt_title ;
        ImageView img_image ;
        TextView txt_director;
        TextView txt_actor ;

        public MovieHolder(View itemView) {

            super(itemView);
            txt_title = itemView.findViewById(R.id.movie_item_title);
            txt_director = itemView.findViewById(R.id.movie_item_director);
            txt_actor = itemView.findViewById(R.id.movie_item_actor);

            img_image = itemView.findViewById(R.id.movie_item_image);

        }

    }
}






