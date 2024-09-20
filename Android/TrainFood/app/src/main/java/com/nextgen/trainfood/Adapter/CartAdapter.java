package com.nextgen.trainfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nextgen.trainfood.CartActivity;
import com.nextgen.trainfood.FoodActivity;
import com.nextgen.trainfood.GlobalPreference;
import com.nextgen.trainfood.ModelClass.CartModelClass;
import com.nextgen.trainfood.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private static String TAG ="CartAdapter";

    ArrayList<CartModelClass> list;
    Context context;
    String ip,uid;

    public CartAdapter(ArrayList<CartModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        GlobalPreference globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CartModelClass cartList = list.get(position);
        holder.itemNameTV.setText(cartList.getItemName());

        holder.itemQuantityTV.setText(cartList.getQuantity());

        Glide.with(context).load("http://" + ip +"/train_food/food_tbl/uploads/" + cartList.getImage()).into(holder.itemIV);


        //setting up price for each item with quantity
        if (cartList.getQuantity().equals("1")){

            holder.itemPriceTV.setText(cartList.getPrice());

        }else {

            int amount = Integer.valueOf(cartList.getPrice());
            int price;
            int quantity = Integer.parseInt(holder.itemQuantityTV.getText().toString());

            price = quantity * amount;

            holder.itemPriceTV.setText("₹ "+String.valueOf(price));

        }

        //adding quantity
        holder.addQuantityIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(holder.itemQuantityTV.getText().toString());
                int price;

                if (quantity < 10) {
                    quantity = quantity + 1;
                    holder.itemQuantityTV.setText(String.valueOf(quantity));

                    int amount = Integer.valueOf(cartList.getPrice());

                    price = quantity * amount;

                   // Toast.makeText(context, "" + price, Toast.LENGTH_SHORT).show();

                    holder.itemPriceTV.setText("₹ "+String.valueOf(price));

                    updateQuantity(cartList.getId(),quantity);

                } else {
                    Toast.makeText(context, "Maximum quantity reached", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.subQuantityIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(holder.itemQuantityTV.getText().toString());
                int price;

                if (quantity > 1) {
                    quantity = quantity - 1;
                    holder.itemQuantityTV.setText(String.valueOf(quantity));

                    int amount = Integer.valueOf(cartList.getPrice());

                    price = quantity * amount;

                    // Toast.makeText(context, "" + price, Toast.LENGTH_SHORT).show();

                    holder.itemPriceTV.setText("₹ "+String.valueOf(price));

                    updateQuantity(cartList.getId(),quantity);


                } else {
                    Toast.makeText(context, "Minimum quantity required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add long press listener
      /*  holder.cartCV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Remove the item from the list and notify the adapter

                Toast.makeText(context, "Delete Item"+cartList.getId(), Toast.LENGTH_SHORT).show();
                return true;
            }
        }); */




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTV;
        TextView itemPriceTV;
        TextView itemQuantityTV;
        ImageView itemIV;
        ImageView addQuantityIV;
        ImageView subQuantityIV;
        CardView cartCV;


        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

            itemNameTV = itemview.findViewById(R.id.cItemNameTextView);
            itemPriceTV = itemview.findViewById(R.id.cItemPriceTextView);
            itemQuantityTV = itemview.findViewById(R.id.cItemQuantityTextView);
            itemIV = itemview.findViewById(R.id.cItemImageView);
            addQuantityIV = itemview.findViewById(R.id.addItemImageView);
            subQuantityIV = itemview.findViewById(R.id.subItemImageView);
            cartCV = itemview.findViewById(R.id.card_Cart);


        }
    }

    private void updateQuantity(String id, int quantity) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip +"/train_food/api/updateQuantity.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("success")){
                    Toast.makeText(context, "Quantity Updated", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, CartActivity.class);
                    context.startActivity(intent);

                }else{

                    Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: "+error);
            }
        }){
            @Override
            @Nullable
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("quantity", String.valueOf(quantity));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
