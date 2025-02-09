package com.example.recyclerviewtest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageview;
    TextView item_name, price_display;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.imageview);
        item_name = itemView.findViewById(R.id.item_name);
        price_display = itemView.findViewById(R.id.price_display);
    }


}
