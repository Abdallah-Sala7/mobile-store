package com.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.foodapp.Adapter.cartAdapter;
import com.foodapp.Helper.ManagementCart;
import com.foodapp.inter.ChangeNumberItems;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    private double tax;
    TextView total,taxTxt,totalItems,deliveryService,cartIsEmpty;
    ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateTax();

        LinearLayout bar_btn1 = (LinearLayout) findViewById(R.id.bar_btn1);
        bar_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }


    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        taxTxt = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        totalItems = findViewById(R.id.totalItems);
        deliveryService = findViewById(R.id.deliveryService);
        cartIsEmpty = findViewById(R.id.cartIsEmpyt);
        scrollView = findViewById(R.id.cartScroll);
        recyclerView = findViewById(R.id.cartView);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new cartAdapter(managementCart.getListCart(), this, new ChangeNumberItems() {
            @Override
            public void changed() {
                CalculateTax();
            }
        });

        recyclerView.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            cartIsEmpty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
        else {
            cartIsEmpty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateTax(){
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalPrice() * percentTax) *100)/100;

        double totalPr = Math.round((managementCart.getTotalPrice() + tax + delivery) *100)/100;
        double itemTotal = Math.round(managementCart.getTotalPrice() * 100) / 100;

        taxTxt.setText("$" + tax);
        total.setText("$"+totalPr);
        deliveryService.setText("$"+delivery);
        totalItems.setText("$"+itemTotal);

    }


}