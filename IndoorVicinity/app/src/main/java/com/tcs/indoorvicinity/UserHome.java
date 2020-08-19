package com.tcs.indoorvicinity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {
    LinearLayout l1,l2;
    RecyclerView recyclerView,recyclerView2;
    RecyclerView.Adapter myAdapter,myAdapter2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    ArrayList<Products> product;
    ImageView productview,searchbutton;
    AutoCompleteTextView etsearch;
    String []names={"Raymond","Shirts","TShirts","Glasses","Woodland","Reebok","Adidas","Peter England","Intex"};
    TextView t1temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        l1=findViewById(R.id.llproduct);
        l2=findViewById(R.id.llshop);
        etsearch =(AutoCompleteTextView) findViewById(R.id.userhomesearchbar);
        recyclerView=findViewById(R.id.userhomeproduct1);
        recyclerView2=findViewById(R.id.userhomeshop1);
        searchbutton=findViewById(R.id.userhomesearchbutton);
        t1temp=findViewById(R.id.temp1);
        t1temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserHome.this,com.tcs.indoorvicinity.Customer_View_Item.class);
                startActivity(i);
            }
        });
//        recyclerView.setHasFixedSize(true);

        layoutManager =new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);
        layoutManager2 =new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);

//        layoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        product=new ArrayList<Products>();
        product.add(new Products("T Shirt","Raymond","50"));
        product.add(new Products("Shirt","LeeCooper","70"));
        product.add(new Products("Jeans","Lewis","50"));
        product.add(new Products("Socks","Puma","50"));
        product.add(new Products("Shoes","Reebok","20"));
        product.add(new Products("Tie","Raymond","60"));
        myAdapter=new UserHomeAdapter1(this,product);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        myAdapter2=new UserHomeAdapter2(this,product);
        recyclerView2.setAdapter(myAdapter2);
        myAdapter2.notifyDataSetChanged();






        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserHome.this,com.tcs.indoorvicinity.Customer_View_Item.class);
                startActivity(i);

            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(UserHome.this,com.tcs.indoorvicinity.Customer_View_Item.class);
                startActivity(i);

            }
        });
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etsearch.getVisibility()!=View.VISIBLE) {
                    etsearch.setVisibility(View.VISIBLE);
                }
                else
                {
                    etsearch.setVisibility(View.GONE);
                }
            }
        });
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,names);
        etsearch.setThreshold(1);
        etsearch.setAdapter(arrayAdapter);

    }


}
