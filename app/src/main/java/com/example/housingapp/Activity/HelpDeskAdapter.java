package com.example.housingapp.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.housingapp.R;


public class HelpDeskAdapter extends RecyclerView.Adapter<HelpDeskAdapter.ViewHolder>{
    private MyListData[] listdata;

    // RecyclerView recyclerView;  
    public HelpDeskAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_helpdesk, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listdata[position];
        holder.tv_content.setText(listdata[position].getDescription());
        holder.tv_name.setText(listdata[position].getName());
        holder.tv_email.setText(listdata[position].getEmail());
        holder.tv_number.setText(listdata[position].getNumber());
        holder.imageView.setImageResource(listdata[position].getImgId());
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv_name;
        public TextView tv_content;
        public TextView tv_email;
        public TextView tv_number;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imgBackground);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_email = (TextView) itemView.findViewById(R.id.tv_email);
            this.tv_number = (TextView) itemView.findViewById(R.id.tv_number);

        }
    }
}  