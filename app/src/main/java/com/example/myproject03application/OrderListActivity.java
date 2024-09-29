package com.example.myproject03application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Order;

public class OrderListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list); // Set the content view to the layout file

        recyclerView = findViewById(R.id.recycler_view); // Get the RecyclerView from the layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager for the RecyclerView

        orderList = getIntent().getParcelableArrayListExtra("orderList");

        if (orderList != null && !orderList.isEmpty()) {
            orderAdapter = new OrderAdapter(orderList, this); // Pass context to the adapter
            recyclerView.setAdapter(orderAdapter);
        } else {
            Toast.makeText(this, "No orders available", Toast.LENGTH_SHORT).show();
        }

        Button backButton = findViewById(R.id.back_button_listpage);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
