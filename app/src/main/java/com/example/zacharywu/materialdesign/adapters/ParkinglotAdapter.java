package com.example.zacharywu.materialdesign.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zacharywu.materialdesign.R;
import com.example.zacharywu.materialdesign.activitys.ParkinglotActivity;
import com.example.zacharywu.materialdesign.classes.Parkinglot;

import java.util.List;

public class ParkinglotAdapter extends RecyclerView.Adapter <ParkinglotAdapter.ViewHolder>{

    private Context mContext;
    private List<Parkinglot> mParkinglotList;

     static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView parkingLotImage;
        TextView parkingLotName;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            parkingLotImage = (ImageView)view.findViewById(R.id.parkinglot_image);
            parkingLotName = (TextView)view.findViewById(R.id.parkinglot_name);
        }
    }

    public ParkinglotAdapter(List<Parkinglot> mParkinglotList){
        this.mParkinglotList = mParkinglotList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.parkinglot_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //给cardView注册一个监听事件，并且传入图片资源id和名称
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Parkinglot parkinglot = mParkinglotList.get(position);
                Intent intent = new Intent(mContext, ParkinglotActivity.class);
                intent.putExtra(ParkinglotActivity.PARRINGLOT_NAME, parkinglot.getName());
                intent.putExtra(ParkinglotActivity.PARRINGLOT_IMAGE_ID, parkinglot.getImageID());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Parkinglot parkinglot = mParkinglotList.get(position);
        holder.parkingLotName.setText(parkinglot.getName());
        Glide.with(mContext).load(parkinglot.getImageID()).into(holder.parkingLotImage);
    }

    @Override
    public int getItemCount() {
        return mParkinglotList.size();
    }
}
