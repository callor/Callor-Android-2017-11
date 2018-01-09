package com.callor.lession.myapp018;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by callor on 2018-01-09.
 */

public class PhoneAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<PhoneVO> phoneVOs;


    public PhoneAdapter(Context context, ArrayList phoneVOs) {
        this.context = context;
        this.phoneVOs = phoneVOs;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phone_item,parent,false);
        PhoneHolder holder = new PhoneHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PhoneHolder viewholder = (PhoneHolder)holder;

        viewholder.txt_name.setText(phoneVOs.get(position).getStrName());
        viewholder.txt_phone.setText(phoneVOs.get(position).getStrPhone());

    }

    @Override
    public int getItemCount() {
        return phoneVOs.size();
    }

    class PhoneHolder extends RecyclerView.ViewHolder {

        TextView txt_name ;
        TextView txt_phone ;
        public PhoneHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_phone = itemView.findViewById(R.id.txt_phone);
        }
    }
}
