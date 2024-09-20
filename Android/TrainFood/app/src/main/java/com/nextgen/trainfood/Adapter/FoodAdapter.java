package com.nextgen.trainfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.nextgen.trainfood.FoodActivity;
import com.nextgen.trainfood.GlobalPreference;
import com.nextgen.trainfood.ModelClass.FoodModelClass;
import com.nextgen.trainfood.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{

    private static String TAG ="FoodAdapter";

    ArrayList<FoodModelClass> list;
    Context context;
    String ip,uid;

    public FoodAdapter(ArrayList<FoodModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        GlobalPreference globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_food,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FoodModelClass foodList = list.get(position);
        holder.foodNameTV.setText(foodList.getItemName());
        holder.foodDescTV.setText(foodList.getItemDesc());
        holder.foodPriceTV.setText("₹ "+foodList.getPrice());

        Glide.with(context).load("http://" + ip +"/train_food/food_tbl/uploads/" + foodList.getImage()).into(holder.foodIV);

        if (foodList.getFoodType().equals("non-veg")){

            Drawable imgDrawable = holder.foodTypeIV.getContext().getResources().getDrawable(R.drawable.non);
            holder.foodTypeIV.setImageDrawable(imgDrawable);
        }

        holder.addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToCart(foodList.getId());
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTV;
        TextView foodDescTV;
        TextView foodPriceTV;
        ImageView foodIV;
        ImageView foodTypeIV;
        Button addBT;



        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

            foodNameTV = itemview.findViewById(R.id.foodNameTextView);
            foodDescTV = itemview.findViewById(R.id.foodDescTextView);
            foodPriceTV = itemview.findViewById(R.id.foodPriceTextView);
            foodIV = itemview.findViewById(R.id.foodImageView);
            foodTypeIV = itemview.findViewById(R.id.foodTypeIV);
            addBT = itemview.findViewById(R.id.addButton);

        }
    }

    private void addToCart(String foodId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip +"/train_food/api/addCart.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("success")){
                    Toast.makeText(context, "Successfully Added to Cart", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, FoodActivity.class);
                    context.startActivity(intent);

                }else{

                    Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
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
                params.put("uid",uid);
                params.put("foodId", foodId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
