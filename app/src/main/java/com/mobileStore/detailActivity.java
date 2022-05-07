package com.mobileStore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobileStore.Helper.ManagementCart;
import com.mobileStore.domain.PhoneDomain;

public class detailActivity extends AppCompatActivity {

    private TextView addToCartBtn,description, decrimntBtn,incremntBtn, orderCount, orderTitle,orderPrice;
    private ImageView orderPic;
    private PhoneDomain object;
    int orderNum = 1;
    private ManagementCart managementCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (PhoneDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(orderPic);

        orderTitle.setText(object.getTitle());
        orderPrice.setText("$"+object.getPrice());
        description.setText(object.getDescription());
        orderCount.setText(String.valueOf(orderNum));

        incremntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderNum++;
                orderCount.setText(String.valueOf(orderNum));
            }
        });

        decrimntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderNum > 1){
                    orderNum--;
                }
                orderCount.setText(String.valueOf(orderNum));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(orderNum);
                managementCart.insertFood(object);
            }
        });

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        decrimntBtn = findViewById(R.id.decrimntBtn);
        incremntBtn = findViewById(R.id.incremntBtn);
        orderCount = findViewById(R.id.orderCount);
        description = findViewById(R.id.description);
        orderPic = findViewById(R.id.orderPic);
        orderTitle = findViewById(R.id.orderTitle);
        orderPrice = findViewById(R.id.orderPrice);
    }
}