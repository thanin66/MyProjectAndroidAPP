package com.example.myproject03application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import model.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;
    private Context context; // Context ของ Activity ที่เรียกใช้ Adapter

    // Constructor ของ OrderAdapter
    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderId.setText(String.valueOf("Number ID: " + order.getId()));
        holder.orderName.setText("Name: " + order.getName());
        holder.orderProcessor.setText("Processor: " + order.getProcessor());
        holder.orderVga.setText("Graphics Card: " + order.getVga());
        holder.orderRam.setText("Ram: " + order.getRam());
        holder.orderStorage.setText("Storage: " + order.getStorage());

        // Load images with Glide
        Glide.with(context)
                .load(order.getProcessorImage())
                .into(holder.orderProcessorImage);
        Glide.with(context)
                .load(order.getVgaImage())
                .into(holder.ordervgaImage);
    }

    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0; // Safe check for null
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderName, orderProcessor, orderVga, orderRam, orderStorage;
        ImageView orderProcessorImage, ordervgaImage;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
            orderName = itemView.findViewById(R.id.order_name);
            orderProcessor = itemView.findViewById(R.id.order_processor);
            orderProcessorImage = itemView.findViewById(R.id.order_processor_image);
            orderVga = itemView.findViewById(R.id.order_vga);
            ordervgaImage = itemView.findViewById(R.id.order_vga_image);
            orderRam = itemView.findViewById(R.id.order_ram);
            orderStorage = itemView.findViewById(R.id.order_storage);
        }
    }
}
