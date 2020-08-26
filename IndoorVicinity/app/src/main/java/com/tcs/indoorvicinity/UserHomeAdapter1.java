package com.tcs.indoorvicinity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class UserHomeAdapter1 extends RecyclerView.Adapter<UserHomeAdapter1.ViewHolder>  {
    private ArrayList<Products> product;
    public UserHomeAdapter1(Context context, ArrayList<Products> list) {
        product=list;

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

                    System.out.println("Item clicked " +getItemCount() );
                    System.out.println("2 "+getAbsoluteAdapterPosition());


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
        holder.tv1.setText(product.get(position).getProduct_name());
        holder.itempic.setImageResource(R.drawable.car1);



    }

    @Override
    public int getItemCount() {
        return product.size();

    }

}