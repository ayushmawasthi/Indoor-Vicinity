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

public class Customer_Product_View extends AppCompatActivity implements PersonAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__view__item);

        recyclerView=findViewById(R.id.rec_view);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        people = new ArrayList<Person>();

        people.add(new Person("Peter England", 10, "shirt"));
        people.add(new Person("Woodland", 15, "shirt"));
        people.add(new Person("Monte Carlo", 20, "shirt"));
        people.add(new Person("Raymond", 5, "tshirt"));
        people.add(new Person("Levi's", 8, "tshirt"));
        people.add(new Person("Lee Cooper", 7, "shirt"));
        people.add(new Person("Peter England", 10, "shirt"));
        people.add(new Person("Woodland", 15, "shirt"));
        people.add(new Person("Monte Carlo", 20, "shirt"));
        people.add(new Person("Raymond", 5, "tshirt"));
        people.add(new Person("Levi's", 8, "tshirt"));
        people.add(new Person("Lee Cooper", 7, "shirt"));
        people.add(new Person("Peter England", 10, "shirt"));
        people.add(new Person("Woodland", 15, "shirt"));
        people.add(new Person("Monte Carlo", 20, "shirt"));
        people.add(new Person("Raymond", 5, "tshirt"));
        people.add(new Person("Levi's", 8, "tshirt"));
        people.add(new Person("Lee Cooper", 7, "shirt"));


        myAdapter = new PersonAdapter(this, people);

        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClicked(int index) {


    }
}