package com.example.recyclerviewtest;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        RecyclerView recyclerView = findViewById(R.id.rectest);

        List<content> contents = new ArrayList<content>();
        contents.add(new content("Gun", "$10", R.drawable.pistol));
        contents.add(new content("Knife", "$5", R.drawable.knife));
        contents.add(new content("Grenade", "$20", R.drawable.grenade));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), contents));

        recyclerView.setAdapter(new Adapter(this, contents));




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
    }

     */
        RecyclerView recyclerView = findViewById(R.id.rectest);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://my-own-e-commerce-test-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference productsRef = database.getReference("Products");

        List<content> contents = new ArrayList<>();
        Map<String, Integer> imageMap = new HashMap<>();
        imageMap.put("gun", R.drawable.pistol);
        imageMap.put("knife", R.drawable.knife);
        imageMap.put("grenade", R.drawable.grenade);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(getApplicationContext(), contents));

        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contents.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    content product = snapshot.getValue(content.class);
                    if (product != null) {
                        String itemName = product.getItem();
                        if (itemName != null) {
                            Integer imageRes = imageMap.get(itemName.toLowerCase());
                            if (imageRes != null) {
                                product.setImage(imageRes);
                            } else {
                                product.setImage(R.drawable.default_image);
                            }
                        } else {
                            Log.w("Firebase", "Product name is null, setting default image");
                            product.setImage(R.drawable.default_image);
                        }
                        contents.add(product);
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching data", databaseError.toException());
            }
        });
    }
}