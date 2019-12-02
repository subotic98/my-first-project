package com.dachuyennganh.beershop.adpater;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.dachuyennganh.beershop.Products;
import com.dachuyennganh.beershop.R;
import com.dachuyennganh.beershop.model.Cart;


import java.util.List;

public class CartAdapter extends ArrayAdapter<com.dachuyennganh.beershop.model.Cart> {
    Activity context;
    int resource;
    List<Cart> objects;

    public CartAdapter(@NonNull Activity context, int resource, List<com.dachuyennganh.beershop.model.Cart> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        Button delete = item.findViewById(R.id.btndelete);
        ImageView image = item.findViewById(R.id.imgcart);
        TextView name = item.findViewById(R.id.txtnamecartbeer);
        TextView price = item.findViewById(R.id.txtpricecartbeer);
        final Button subtract = item.findViewById(R.id.btnsubtract);
        Button add = item.findViewById(R.id.btnadd);
        final Button values = item.findViewById(R.id.btnvalue);
        final Cart c = this.objects.get(position);
        name.setText(c.getName());
        price.setText("$"+c.getPrice());
        Glide.with(context).load(c.getImg()).into(image);
        values.setText(""+c.getAmount());

        if(c.getAmount()<=1){
            subtract.setVisibility(View.INVISIBLE);
        }
        else
            subtract.setVisibility(View.VISIBLE);

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newamt = Integer.parseInt(values.getText().toString())-1;
                int oldamt = Products.ListCart.get(position).getAmount();
                int oldprice=Products.ListCart.get(position).getPrice();
                Products.ListCart.get(position).setAmount(newamt);
                int newprice = (oldprice * newamt)/oldamt;
                Products.ListCart.get(position).setPrice(newprice);
                com.dachuyennganh.beershop.Cart.SumTotalCart();
                values.setText(""+newamt);
                if(c.getAmount()==1){
                    subtract.setVisibility(View.INVISIBLE);
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.getAmount()==1){
                    subtract.setVisibility(View.VISIBLE);
                }
                int newamt = Integer.parseInt(values.getText().toString())+1;
                int oldamt = Products.ListCart.get(position).getAmount();
                int oldprice=Products.ListCart.get(position).getPrice();
                Products.ListCart.get(position).setAmount(newamt);
                int newprice = (oldprice * newamt)/oldamt;
                Products.ListCart.get(position).setPrice(newprice);
                com.dachuyennganh.beershop.Cart.SumTotalCart();
                values.setText(""+newamt);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products.ListCart.remove(position);
                notifyDataSetChanged();
                com.dachuyennganh.beershop.Cart.SumTotalCart();
                if(Products.ListCart.size()<=0){
                    com.dachuyennganh.beershop.Cart.txtnotify.setVisibility(View.VISIBLE);
                    com.dachuyennganh.beershop.Cart.lvcart.setVisibility(View.INVISIBLE);
                }
                else{
                    com.dachuyennganh.beershop.Cart.txtnotify.setVisibility(View.INVISIBLE);
                    com.dachuyennganh.beershop.Cart.lvcart.setVisibility(View.VISIBLE);
                }
            }
        });
        return item;
    }
}
