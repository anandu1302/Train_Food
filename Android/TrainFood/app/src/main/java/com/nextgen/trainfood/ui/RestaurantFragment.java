package com.nextgen.trainfood.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.trainfood.Adapter.RestaurantAdapter;
import com.nextgen.trainfood.Adapter.SearchRestaurantsAdapter;
import com.nextgen.trainfood.GlobalPreference;
import com.nextgen.trainfood.ModelClass.RestaurantModelClass;
import com.nextgen.trainfood.ModelClass.SearchRestaurantsModelClass;
import com.nextgen.trainfood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
    FoodFragment
 */
public class RestaurantFragment extends Fragment {

    private static String TAG ="RestaurantFragment";

    // TODO: Rename parameter arguments, choose names that match
    View view;
    RecyclerView restaurantsRV;
    ArrayList<SearchRestaurantsModelClass> list;
    EditText locationET;
    ImageView searchIV;
    TextView noRestaurantTV;

    GlobalPreference globalPreference;
    private String ip;


    public RestaurantFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RestaurantFragment newInstance(String param1, String param2) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globalPreference = new GlobalPreference(getContext());
        ip = globalPreference.getIP();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        restaurantsRV = view.findViewById(R.id.restaurantRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        restaurantsRV.setLayoutManager(layoutManager);

        getRestaurants();

        locationET = view.findViewById(R.id.locationEditText);
        searchIV = view.findViewById(R.id.searchImageView);
        noRestaurantTV = view.findViewById(R.id.noRestaurantsTextView);

        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ""+locationET.getText().toString(), Toast.LENGTH_SHORT).show();
                searchRestaurants();
            }
        });

        return view;
    }



    private void getRestaurants() {
        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/train_food/api/getRestaurants.php", new Response.Listener<String>() {
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

                            list.add(new SearchRestaurantsModelClass(id,name,place,image,rating));

                        }

                        SearchRestaurantsAdapter adapter = new SearchRestaurantsAdapter(list,getContext());
                        restaurantsRV.setAdapter(adapter);

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

    private void searchRestaurants() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/train_food/api/searchRestaurants.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){

                    restaurantsRV.setVisibility(View.GONE);
                    noRestaurantTV.setVisibility(View.VISIBLE);
                }
                else{
                    restaurantsRV.setVisibility(View.VISIBLE);
                    noRestaurantTV.setVisibility(View.INVISIBLE);
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

                            list.add(new SearchRestaurantsModelClass(id,name,place,image,rating));

                        }

                        SearchRestaurantsAdapter adapter = new SearchRestaurantsAdapter(list,getContext());
                        restaurantsRV.setAdapter(adapter);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
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
                Map<String,String> params = new HashMap<>();
                params.put("location",locationET.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}