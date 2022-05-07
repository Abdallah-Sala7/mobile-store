package com.mobileStore.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileStore.Helper.ManagementCart;
import com.mobileStore.R;
import com.mobileStore.domain.PhoneDomain;
import com.mobileStore.inter.ChangeNumberItems;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {


    private ArrayList<PhoneDomain> phoneDomains;
    private ManagementCart managementCart;
    private ChangeNumberItems changeNumberItems;

    public cartAdapter(ArrayList<PhoneDomain> phoneDomains, Context context, ChangeNumberItems changeNumberItems) {
        this.phoneDomains = phoneDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItems = changeNumberItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(phoneDomains.get(position).getTitle());
        holder.totalEachItem.setText(String.valueOf(Math.round((phoneDomains.get(position).getNumberInCart() * phoneDomains.get(position).getPrice())*100)/100));
        holder.priceEachItem.setText(String.valueOf(phoneDomains.get(position).getPrice()));
        holder.num.setText(String.valueOf(phoneDomains.get(position).getNumberInCart()));

        int drawableResourced = holder.itemView.getContext().getResources().getIdentifier(phoneDomains.get(position).getPic(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourced)
                .into(holder.pic);

        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.incrementNumberFood(phoneDomains, position, new ChangeNumberItems(){

                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItems.changed();
                    }
                });
            }
        });

        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.decrementNumberFood(phoneDomains, position, new ChangeNumberItems(){

                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItems.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, priceEachItem, increment, decrement, totalEachItem, num;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.itemInCartTitle);
            priceEachItem = itemView.findViewById(R.id.itemInCartPrice);
            increment = itemView.findViewById(R.id.itemInCartIncrement);
            decrement = itemView.findViewById(R.id.itemInCartDecrement);
            num = itemView.findViewById(R.id.itemInCartCount);
            totalEachItem = itemView.findViewById(R.id.itemInCartEachPrice);
            pic = itemView.findViewById(R.id.itemInCartImg);
        }
    }
}
