package com.nextgen.trainfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    TextView nameTV;
    TextView numberTV;
    TextView emailTV;
    ImageView backIV;

    private GlobalPreference globalPreference;
    private  String ip,uid;
    String userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();

        nameTV = findViewById(R.id.uNameTextView);
        numberTV = findViewById(R.id.uNumberTextView);
        emailTV = findViewById(R.id.uEmailTextView);
        backIV = findViewById(R.id.profileBackImageView);

        getUserDetails();


        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bIntent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(bIntent);
            }
        });

    }

    private void getUserDetails() {
        String URL = "http://"+ ip +"/train_food/api/profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                if (!response.equals("")){
                    try{

                        userdata = response;

                        JSONObject obj = new JSONObject(response);
                        JSONArray array = obj.getJSONArray("data");
                        JSONObject data = array.getJSONObject(0);

                        String name = data.getString("name");
                        String number = data.getString("number");
                        String email = data.getString("email");


                        nameTV.setText(name);
                        numberTV.setText(number);
                        emailTV.setText(email);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(ProfileActivity.this,"hyy",Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
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