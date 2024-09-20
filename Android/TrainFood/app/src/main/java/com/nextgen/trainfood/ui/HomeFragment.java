package com.nextgen.trainfood.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.trainfood.Adapter.NearbyRestaurantsAdapter;
import com.nextgen.trainfood.Adapter.RestaurantAdapter;
import com.nextgen.trainfood.GlobalPreference;
import com.nextgen.trainfood.ModelClass.NearbyRestaurantModelClass;
import com.nextgen.trainfood.ModelClass.RestaurantModelClass;
import com.nextgen.trainfood.ProfileActivity;
import com.nextgen.trainfood.R;
import com.nextgen.trainfood.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    View view;
    ImageView accountIV;
    RecyclerView restaurantRV;
    TextView nameTV;

    RecyclerView nearbyRestaurantRV;
    ArrayList<RestaurantModelClass> list;
    ArrayList<NearbyRestaurantModelClass> restList;

    private GlobalPreference globalPreference;
    private String ip,name;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globalPreference = new GlobalPreference(getContext());
        ip = globalPreference.getIP();
        name = globalPreference.getName();


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        accountIV = view.findViewById(R.id.accountImageView);
        nameTV = view.findViewById(R.id.nameTextView);
        nameTV.setText(name);

        /* restaurants Recycler View */
        restaurantRV = view.findViewById(R.id.restaurantsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        restaurantRV.setLayoutManager(layoutManager);


        /* restaurants Recycler View */
        nearbyRestaurantRV = view.findViewById(R.id.nearbyRestaurantsRecyclerView);
        LinearLayoutManager layoutManagerr = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        nearbyRestaurantRV.setLayoutManager(layoutManagerr);

        getRestaurants();

        getNearbyRestaurants();

        accountIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



    private void getRestaurants() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/train_food/api/getTopRestaurants.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")){
                    Toast.makeText(getContext(), "No restaurants Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String place = object.getString("place");
                            String image = object.getString("image");
                            String rating = object.getString("rating");

                            list.add(new RestaurantModelClass(id,name,place,image,rating));

                        }

                        RestaurantAdapter adapter = new RestaurantAdapter(list,getContext());
                        restaurantRV.setAdapter(adapter);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void getNearbyRestaurants() {
        restList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/train_food/api/nearbyRestaurants.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("failed")){
                    Toast.makeText(getContext(), "No restaurants Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String place = object.getString("place");
                            String image = object.getString("image");
                            String rating = object.getString("rating");

                            restList.add(new NearbyRestaurantModelClass(id,name,place,image,rating));

                        }

                        NearbyRestaurantsAdapter adapter = new NearbyRestaurantsAdapter(restList,getContext());
                        nearbyRestaurantRV.setAdapter(adapter);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}