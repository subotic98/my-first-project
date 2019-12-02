package com.dachuyennganh.beershop.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dachuyennganh.beershop.DetailBeer;
import com.dachuyennganh.beershop.R;
import com.dachuyennganh.beershop.model.Beer;

import java.io.Serializable;
import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ItemHolder>{

    private Context context;
    private ArrayList<Beer> arraybeer;

    public BeerAdapter(Context context, ArrayList<Beer> arraybeer) {
        this.context = context;
        this.arraybeer = arraybeer;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanpham,parent,false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Beer beer = arraybeer.get(position);
        holder.txtnamebeer.setText(beer.getName());
        holder.txtprice.setText(""+beer.getPrice());
        Glide.with(context).load(beer.getImage()).into(holder.imgbeer);
    }

    @Override
    public int getItemCount() {
        return arraybeer.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgbeer;
        public TextView txtnamebeer,txtprice;

        public ItemHolder (View itemView){
            super(itemView);
            imgbeer = itemView.findViewById(R.id.imagebeer);
            txtnamebeer = itemView.findViewById(R.id.txtnamebeer);
            txtprice = itemView.findViewById(R.id.txtprice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBeer.class);
                    intent.putExtra("thongtinsanpham", arraybeer.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
