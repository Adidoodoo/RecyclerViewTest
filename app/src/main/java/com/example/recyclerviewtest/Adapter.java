package com.example.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<content> contents;

    public Adapter(Context context, List<content> contents) {
        this.context = context;
        this.contents = contents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set data for item
        content product = contents.get(position);
        holder.item_name.setText(product.getItem());
        holder.price_display.setText(product.getPrice());
        holder.imageview.setImageResource(product.getImage());

        // Shared click listener for item name, price, and image
        View.OnClickListener clickListener = e -> {
            Intent intent = new Intent(context, PageHolder.class);
            intent.putExtra("item", product.getItem());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("image", product.getImage());
            context.startActivity(intent);
        };

        // Apply the click listener to multiple views
        holder.item_name.setOnClickListener(clickListener);
        holder.price_display.setOnClickListener(clickListener);
        holder.imageview.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
