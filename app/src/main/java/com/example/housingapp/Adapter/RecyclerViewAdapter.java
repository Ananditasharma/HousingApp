package com.example.housingapp.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housingapp.Interface.GetIdAndPositionOfClickedAdapter;
import com.example.housingapp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
    public GetIdAndPositionOfClickedAdapter getIdAndPositionOfClickedAdapter;

    List<String> itemList;


    public RecyclerViewAdapter(List<String> itemList,GetIdAndPositionOfClickedAdapter getIdAndPositionOfClickedAdapter) {
        this.itemList = itemList;
        this.getIdAndPositionOfClickedAdapter=getIdAndPositionOfClickedAdapter;
    }




    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder myViewHolder, final int position) {
        myViewHolder.tvItem.setText(itemList.get(position));

    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.tvItem);
            tvItem.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getIdAndPositionOfClickedAdapter.getIdPosition(itemList.get(getAdapterPosition()), getAdapterPosition());
                }
            });


        }

    }
}