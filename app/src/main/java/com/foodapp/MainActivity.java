package com.foodapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foodapp.Adapter.CategoryAdapter;
import com.foodapp.Adapter.PhoneAdapter;
import com.foodapp.domain.CategoryDomain;
import com.foodapp.domain.PhoneDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewCategory ();
        setRecyclerViewPopular();

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.homBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });

    }

/*    private void navBtn(){
        FloatingActionButton floatingActionButton = findViewById(R.id.floatBtn);
        LinearLayout homBtn = findViewById(R.id.bar_btn1);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });

        homBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }*/


    private void recyclerViewCategory (){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Iphone","iphone"));
        category.add(new CategoryDomain("Laptop","labtop"));
        category.add(new CategoryDomain("Tablet","tablet"));
        category.add(new CategoryDomain("Watch","smartwatch"));

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void setRecyclerViewPopular(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFoodList = findViewById(R.id.recyclerView2);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<PhoneDomain> foodList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/photos";
        // Request a string response from the provided URL.
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject post = response.getJSONObject(i);
                                foodList.add(new PhoneDomain(post.getString("id"), "huy9",post.getString("title"),200.0));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        /*
        foodList.add(new PhoneDomain("HUAWEI Y9a","huy9","camera : 16MP , \nRam : 4 GB, \nbattery : 4000 mAh \nSize : 6.59 Inch",200.0));
        foodList.add(new PhoneDomain("Oppo A31","opp","camera : 48MP , \nRam : 8 GB, \nbattery : 8000 mAh \nSize : 7.59 Inch",220.0));
        foodList.add(new PhoneDomain("iphone 11 pro","iph11","camera : 52MP , \nRam : 4 GB, \nbattery : 2500 mAh \nSize : 5.59 Inch",250.5));
        foodList.add(new PhoneDomain("iphone 12 pro","iph11","camera : 48MP , \nRam : 16 GB, \nbattery : 7000 mAh \nSize : 8.59 Inch",350.5));
        foodList.add(new PhoneDomain("Huawei Y9","huy9","camera : 62MP , \nRam : 32 GB, \nbattery : 3000 mAh \nSize : 9.59 Inch",400.5));
        foodList.add(new PhoneDomain("Samsung Galaxy A22","opp","camera : 48MP , \nRam : 16 GB, \nbattery : 4000 mAh \nSize : 8.59 Inch",120.5));
*/
        adapter2 = new PhoneAdapter(foodList);
        recyclerViewFoodList.setAdapter(adapter2);
    }

}