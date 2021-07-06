package com.tcs.indoorvicinity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class UserHomeAdapter1 extends RecyclerView.Adapter<UserHomeAdapter1.ViewHolder>  {
    private ArrayList<String> product;
    ItemClicked activity;
    ImageView imageView;

    public interface ItemClicked
    {
        public void onItemClicked(int index,String s1);
    }
    public UserHomeAdapter1(Context context, ArrayList<String> list) {
        product=list;
        activity = (ItemClicked) context;

    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1;
        ImageView itempic;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.userhometext1);

            itempic=itemView.findViewById(R.id.userhomeimg1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

               //     System.out.println("Item clicked " +getItemCount() );
                //    System.out.println("2 "+getAbsoluteAdapterPosition());
                    activity.onItemClicked(product.indexOf((String)v.getTag()),"product");




                }
            });
        }

    }


    @NonNull
    @Override
    public UserHomeAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.userhomeadapterlist1,parent,false);
        return new UserHomeAdapter1.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHomeAdapter1.ViewHolder holder, int position) {
        holder.itemView.setTag(product.get(position));
        holder.tv1.setText(product.get(position));
        holder.itempic.setImageResource(R.drawable.car1);

//        holder.itempic.setImageDrawable(null);

        String text=holder.tv1.getText().toString().trim();
        text=text.replaceAll(" ","");
        System.out.println("text"+text);
        String url="https://ayushmawasthi.000webhostapp.com/"+text+".png";
        Glide.with(holder.itempic.getContext())
                .load(url)
                .placeholder(R.drawable.car1)
                .into(holder.itempic);
        holder.itempic.getLayoutParams().height = 150;

    }

    @Override
    public int getItemCount() {
        return product.size();

    }

}
