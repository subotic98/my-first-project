package com.dachuyennganh.beershop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dachuyennganh.beershop.adpater.BeerAdapter;
import com.dachuyennganh.beershop.app.VolleySingleton;
import com.dachuyennganh.beershop.model.Beer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.dachuyennganh.beershop.ultil.servser.url_getsp;
import static com.dachuyennganh.beershop.ultil.servser.url_getspbia;

public class Products extends AppCompatActivity {
    RecyclerView recyclerView;

    private static final String TAG = Products.class.getSimpleName();

    ArrayList<Beer> ListBeer;
    public static ArrayList<com.dachuyennganh.beershop.model.Cart> ListCart;
    BeerAdapter adapter;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar = getSupportActionBar();
        addEvent();
    }


    private void addEvent() {
        recyclerView= findViewById(R.id.recycleview);
        ListBeer = new ArrayList<>();
        if(ListCart!=null){}
        else{
            ListCart = new ArrayList<>();
        }
        adapter = new BeerAdapter(getApplicationContext(),ListBeer);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recyclerView.addItemDecoration(new Products.GridSpacingItemDecoration(2,dpToPx(8),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        Getbeer();
    }

    private void Getbeer() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_getsp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }
                int id = 0;
                String ten="";
                String img="";
                int price=0;
                String des="";
                int type=0;

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id=jsonObject.getInt("id");
                        ten=jsonObject.getString("name");
                        price=jsonObject.getInt("price");
                        img=jsonObject.getString("image");
                        des=jsonObject.getString("description");
                        type=jsonObject.getInt("typebeer");

                        ListBeer.add(new Beer(id,ten,price,img,des,type));
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addToRequestQueue(jsonArrayRequest);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart,menu);
        return true;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucart,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iconcart:
                Intent intent = new Intent(Products.this, Cart.class);
                startActivity(intent);
                break;
            case R.id.mnDM:
                Toast.makeText(Products.this,"about",Toast.LENGTH_LONG).show();
                break;
            case R.id.itemBiaa:
                JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(url_getspbia, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int id = 0;
                        String ten="";
                        String img="";
                        int price=0;
                        String des="";
                        int type=0;

                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                id=jsonObject.getInt("id");
                                ten=jsonObject.getString("name");
                                price=jsonObject.getInt("price");
                                img=jsonObject.getString("image");
                                des=jsonObject.getString("description");
                                type=jsonObject.getInt("typebeer");

                                ListBeer.add(new Beer(id,ten,price,img,des,type));
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                VolleySingleton.getInstance().addToRequestQueue(jsonArrayRequest1);
                break;
            case R.id.mnLogout:
                Intent intent2 = new Intent(Products.this, MainActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
