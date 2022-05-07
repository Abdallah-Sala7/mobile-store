package com.mobileStore.Helper;

import android.content.Context;
import android.widget.Toast;

import com.mobileStore.domain.PhoneDomain;
import com.mobileStore.inter.ChangeNumberItems;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;


    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(PhoneDomain item){
        ArrayList<PhoneDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PhoneDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void incrementNumberFood(ArrayList<PhoneDomain> listFood, int position, ChangeNumberItems changeNumberItems){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList",listFood);
        changeNumberItems.changed();
    }

    public void decrementNumberFood(ArrayList<PhoneDomain> listFood, int position, ChangeNumberItems changeNumberItems){
        if (listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }
        else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() -1);
        }
        tinyDB.putListObject("CartList",listFood);
        changeNumberItems.changed();
    }

    public Double getTotalPrice(){
        ArrayList<PhoneDomain> listfood = getListCart();
        double price = 0;
        for (int i = 0; i < listfood.size(); i++) {
            price = price + (listfood.get(i).getPrice() * listfood.get(i).getNumberInCart());
        }
        return price;
    }

}
