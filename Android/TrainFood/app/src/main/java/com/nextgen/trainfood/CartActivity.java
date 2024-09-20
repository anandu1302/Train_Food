package com.nextgen.trainfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nextgen.trainfood.Adapter.CartAdapter;
import com.nextgen.trainfood.Adapter.FoodAdapter;
import com.nextgen.trainfood.ModelClass.CartModelClass;
import com.nextgen.trainfood.ModelClass.FoodModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private static String TAG ="CartActivity";
    RecyclerView cartRV;
    ArrayList<CartModelClass> list;

    TextView cartItemTotalTV;
    TextView cartTotalTV;
    Button payBT;

    private GlobalPreference globalPreference;
    private String ip,uid;
    private TextView titleTV;

    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();

        cartRV = findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartRV.setLayoutManager(layoutManager);

        getCartDetails();

        titleTV = findViewById(R.id.appBarTitleTextView);
        titleTV.setText("Cart Details");

        cartItemTotalTV = findViewById(R.id.cartItemTotalTextView);
        cartTotalTV = findViewById(R.id.cartTotalTextView);
        payBT = findViewById(R.id.payButton);

        payBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CartActivity.this,OrderActivity.class);
                intent.putExtra("amount",String.valueOf(total));
                startActivity(intent);

            }
        });





    }

    private void getCartDetails() {

        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://"+ ip +"/train_food/api/getCartDetails.php?uid="+uid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){
                    Toast.makeText(CartActivity.this, "No Items Available", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i=0; i< jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String itemName = object.getString("itemName");
                            String price = object.getString("price");
                            String totalPrice = object.getString("totalPrice");
                            String image = object.getString("image");
                            String quantity = object.getString("quantity");

                            total += Integer.parseInt(totalPrice);

                            list.add(new CartModelClass(id,itemName,price,image,quantity));

                        }

                        CartAdapter adapter = new CartAdapter(list,CartActivity.this);
                        cartRV.setAdapter(adapter);
                        cartItemTotalTV.setText("₹ "+total);
                        cartTotalTV.setText("₹ "+total);

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
}