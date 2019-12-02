package com.dachuyennganh.beershop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dachuyennganh.beershop.model.Beer;

public class DetailBeer extends AppCompatActivity {

    ActionBar toolbars;
    ImageView image,image1;
    TextView txtname,txtprice,txtdes;
    Spinner spinner;
    Button btnaddcart;
    int id = 0;
    String name="";
    String img="";
    int price=0;
    String des="";
    int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_beer);
        addControls();
        addEvents();
        GetInformation();
        toolbars = getSupportActionBar();
        toolbars.setDisplayHomeAsUpEnabled(true);
        toolbars.setTitle("CHI TIẾT SẢN PHẨM");
        GetInformation();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iconcart:
                Intent intent = new Intent(DetailBeer.this, com.dachuyennganh.beershop.Cart.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        image = findViewById(R.id.imagedetailbeer);
        txtname =findViewById(R.id.txtdetailnamebeer);
        txtprice =findViewById(R.id.txtdetailpricebeer);
        txtdes =findViewById(R.id.txtdescription);
        spinner =findViewById(R.id.spinner);
        btnaddcart =findViewById(R.id.btnaddcard);
        image1 = findViewById(R.id.imgcart);
    }

    private void addEvents() {
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Products.ListCart.size()>0){
                    int s = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i=0;i<Products.ListCart.size();i++){
                        if(Products.ListCart.get(i).getId()==id){
                            Products.ListCart.get(i).setAmount(Products.ListCart.get(i).getAmount()+s);

                            Products.ListCart.get(i).setPrice(Products.ListCart.get(i).getAmount()*price);
                            exists=true;
                        }
                    }
                    if(exists==false){
                        int amt =Integer.parseInt(spinner.getSelectedItem().toString());
                        int sumamt = amt * price;
                        Products.ListCart.add(new com.dachuyennganh.beershop.model.Cart(id,name,sumamt,amt,img));
                    }

                }
                else
                {
                    int amt =Integer.parseInt(spinner.getSelectedItem().toString());
                    int sumamt = amt * price;
                    Products.ListCart.add(new com.dachuyennganh.beershop.model.Cart(id,name,sumamt,amt,img));
                    Toast.makeText(DetailBeer.this, "Đã thêm vào giỏ hàngthang", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Integer[] soluong = new Integer[]{1,2};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);

    }
    private void GetInformation() {

        Beer s = (Beer) getIntent().getSerializableExtra("thongtinsanpham");
        id = s.getID();
        name=s.getName();
        price=s.getPrice();
        img=s.getImage();
        des=s.getDescription();
        type=s.getTypebeer_id();
        txtname.setText(name);
        txtprice.setText(price+" VND");
        txtdes.setText(des);
        Glide.with(getApplicationContext()).load(s.getImage()).into(image);
    }
}
