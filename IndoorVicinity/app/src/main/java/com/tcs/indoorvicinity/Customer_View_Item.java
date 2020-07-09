package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

public class Customer_View_Item extends AppCompatActivity implements PersonAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Products> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__view__item);

        recyclerView=findViewById(R.id.rec_view);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        people = new ArrayList<Products>();

        people.add(new Products("Jeans", "Peter England", "10"));
        people.add(new Products("Shoes", "Woodland", "20"));
        people.add(new Products("Shirt", "Monte Carlo", "10"));
        people.add(new Products("TShirt", "Raymond", "15"));



        myAdapter = new PersonAdapter(this, people);

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClicked(int index) {


    }
}