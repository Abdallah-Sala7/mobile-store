package com.mobileStore.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileStore.R;
import com.mobileStore.detailActivity;
import com.mobileStore.domain.PhoneDomain;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
    ArrayList<PhoneDomain> popularPhone;

    public PhoneAdapter(ArrayList<PhoneDomain> popularPhone){
        this.popularPhone = popularPhone;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_phone,parent,false);
        return new ViewHolder(inflate);
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(popularPhone.get(position).getTitle());
        holder.price.setText(String.valueOf(popularPhone.get(position).getPrice()));
//        holder.getAdapterPosition();

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularPhone.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), detailActivity.class);
                intent.putExtra("object", popularPhone.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return popularPhone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,price,addBtn;
        ImageView pic;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
