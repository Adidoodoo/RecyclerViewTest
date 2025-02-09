package com.example.recyclerviewtest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PageHolder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page_holder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int image = getIntent().getIntExtra("image", 0);
        String item = getIntent().getStringExtra("item");
        String price = getIntent().getStringExtra("price");

        ImageView imageID = findViewById(R.id.image_id);
        imageID.setImageResource(image);

        TextView itemID = findViewById(R.id.item_id);
        itemID.setText(item);

        TextView priceID = findViewById(R.id.price_id);
        priceID.setText(price);


        Button addToCartButton = findViewById(R.id.addToCartButton);

    }
}