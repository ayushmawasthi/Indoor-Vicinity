package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Vendor_add_item extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Products> product;
    ImageButton add,myacount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_item);
        recyclerView=findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        product=new ArrayList<Products>();
        product.add(new Products("T Shirt","800","50"));
        product.add(new Products("Shirt","900","70"));
        product.add(new Products("Jeans","700","50"));
        product.add(new Products("Socks","300","50"));
        myAdapter=new Product_adapter(this,product);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        add=findViewById(R.id.additem);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Vendor_add_item.this,com.tcs.indoorvicinity.Vendor_add_product_form.class);
                startActivity(intent);
                
            }
        });
        myacount=findViewById(R.id.gotomyaccount);
        myacount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Vendor_add_item.this,com.tcs.indoorvicinity.VendorAccount.class);
                startActivity(intent);
            }
        });
    }

}
