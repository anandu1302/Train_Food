package com.nextgen.trainfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextgen.trainfood.FoodActivity;
import com.nextgen.trainfood.GlobalPreference;
import com.nextgen.trainfood.ModelClass.RestaurantModelClass;
import com.nextgen.trainfood.ModelClass.SearchRestaurantsModelClass;
import com.nextgen.trainfood.R;

import java.util.ArrayList;

public class SearchRestaurantsAdapter extends RecyclerView.Adapter<SearchRestaurantsAdapter.MyViewHolder>{

    ArrayList<SearchRestaurantsModelClass> list;
    Context context;
    String ip;

    public SearchRestaurantsAdapter(ArrayList<SearchRestaurantsModelClass> list, Context context) {
        this.list = list;
        this.context = context;

        GlobalPreference globalPreference = new GlobalPreference(context);
        ip = globalPreference.getIP();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_search_restaurants,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SearchRestaurantsModelClass searchRestList = list.get(position);
        holder.restNameTV.setText(searchRestList.getName());
        holder.restLocationTV.setText(searchRestList.getPlace());
        holder.restRatingTV.setText(searchRestList.getRating());

        Glide.with(context).load("http://" + ip +"/train_food/restaurants_tbl/uploads/" + searchRestList.getImage()).into(holder.restaurantIV);

        holder.restaurantCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("rId",searchRestList.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView restNameTV;
        TextView restLocationTV;
        TextView restRatingTV;
        ImageView restaurantIV;
        CardView restaurantCV;


        public MyViewHolder(@NonNull View itemview) {
            super(itemview);

            restNameTV = itemview.findViewById(R.id.sRestNameTextView);
            restLocationTV = itemview.findViewById(R.id.sRestLocationTextView);
            restRatingTV = itemview.findViewById(R.id.sRestRatingTextView);
            restaurantIV = itemview.findViewById(R.id.sRestImageView);
            restaurantCV = itemview.findViewById(R.id.card_restaurants);

        }
    }


}
