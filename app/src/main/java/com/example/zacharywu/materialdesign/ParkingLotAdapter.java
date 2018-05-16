package com.example.zacharywu.materialdesign;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ParkingLotAdapter extends RecyclerView.Adapter <ParkingLotAdapter.ViewHolder>{

    private Context mContext;
    private List<ParkingLot> mParkingLotList;

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

    public ParkingLotAdapter(List<ParkingLot> mParkingLotList){
        this.mParkingLotList = mParkingLotList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.parkinglot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParkingLot parkingLot = mParkingLotList.get(position);
        holder.parkingLotName.setText(parkingLot.getName());
        Glide.with(mContext).load(parkingLot.getImageID()).into(holder.parkingLotImage);
    }

    @Override
    public int getItemCount() {
        return mParkingLotList.size();
    }
}
