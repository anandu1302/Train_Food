package com.nextgen.trainfood;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.trainfood.Adapter.FoodAdapter;
import com.nextgen.trainfood.Adapter.RestaurantAdapter;
import com.nextgen.trainfood.ModelClass.FoodModelClass;
import com.nextgen.trainfood.ModelClass.RestaurantModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodActivity extends AppCompatActivity {

    private static String TAG ="FoodActivity";

    RecyclerView foodRV;
    ArrayList<FoodModelClass> list;

    LinearLayout cartLL;
    TextView itemCountTV;

    private GlobalPreference globalPreference;
    private String ip,uid;

    String restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();


        restaurantId = getIntent().getStringExtra("rId");

       // Toast.makeText(this, ""+restaurantId, Toast.LENGTH_SHORT).show();

        foodRV = findViewById(R.id.foodRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        foodRV.setLayoutManager(layoutManager);

        cartLL = findViewById(R.id.cartLL);
        itemCountTV = findViewById(R.id.itemCountTextView);


        getFood();

        getCartDetails();

        cartLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FoodActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });



    }

    private void getFood() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/train_food/api/getFoods.php?rid="+restaurantId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){
                    Toast.makeText(FoodActivity.this, "No Food Items Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String itemName = object.getString("itemName");
                            String itemDesc = object.getString("itemDesc");
                            String price = object.getString("price");
                            String foodType = object.getString("food_type");
                            String image = object.getString("image");


                            list.add(new FoodModelClass(id,itemName,itemDesc,price,foodType,image));

                        }

                        FoodAdapter adapter = new FoodAdapter(list,FoodActivity.this);
                        foodRV.setAdapter(adapter);

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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getCartDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/train_food/api/checkCart.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);

                if(response.equals("failed")){

                    cartLL.setVisibility(View.GONE);
                }
                else{
                    cartLL.setVisibility(View.VISIBLE);

                    if (response.equals("1")){

                        itemCountTV.setText(response+" Item added");
                    } else {

                        itemCountTV.setText(response+" Items added");
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("uid",uid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}