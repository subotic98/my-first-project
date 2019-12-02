package com.dachuyennganh.beershop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dachuyennganh.beershop.adpater.CartAdapter;

public class Cart extends AppCompatActivity {

    ActionBar toolbars;
    public static ListView lvcart;
    CartAdapter adapter;
    public static TextView txttotal;
    public  static TextView txtnotify;
    Button btnbuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        addControls();
        CheckData();
        //addEvents();
        SumTotalCart();
        toolbars = getSupportActionBar();
        toolbars.setDisplayHomeAsUpEnabled(true);
        toolbars.setTitle("GIỎ HÀNG");


    }

    public static void SumTotalCart() {
        int sum =0;
        for(int i =0;i<Products.ListCart.size();++i){
            sum += Products.ListCart.get(i).getPrice();
        }
        txttotal.setText(""+sum);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }




    private void CheckData() {
        if(Products.ListCart.size()<=0){
            adapter.notifyDataSetChanged();
            txtnotify.setVisibility(View.VISIBLE);
            lvcart.setVisibility(View.INVISIBLE);
        }
        else{
            adapter.notifyDataSetChanged();
            txtnotify.setVisibility(View.INVISIBLE);
            lvcart.setVisibility(View.VISIBLE);
        }
    }

    private void addControls() {
        lvcart = findViewById(R.id.lvcart);
        txttotal=findViewById(R.id.txttotal);
        txtnotify=findViewById(R.id.txtnotify);
        btnbuy =findViewById(R.id.btnbuy);
        adapter = new CartAdapter(Cart.this,R.layout.item_cart,Products.ListCart);
        lvcart.setAdapter(adapter);
    }


}
