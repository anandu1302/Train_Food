package com.nextgen.trainfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    private static String TAG ="OrderActivity";
    EditText trainDetailsET;
    EditText nameET;
    EditText contactET;
    EditText trainNoET;
    EditText coachET;
    EditText seatnoET;
    Button makePaymentBT;
    String amount;
    private TextView titleTV;

    private GlobalPreference globalPreference;
    private String ip,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();

        amount = getIntent().getStringExtra("amount");

        Toast.makeText(this, ""+amount, Toast.LENGTH_SHORT).show();

        titleTV = findViewById(R.id.appBarTitleTextView);
        titleTV.setText("Order Details");

        trainDetailsET = findViewById(R.id.trainDetailsEditText);
        nameET = findViewById(R.id.oNameEditText);
        contactET = findViewById(R.id.oContactEditText);
        trainNoET = findViewById(R.id.oTrainNumberEditText);
        coachET = findViewById(R.id.oCoachPositionEditText);
        seatnoET = findViewById(R.id.oSeatNumberEditText);
        makePaymentBT = findViewById(R.id.makePaymentBT);

        makePaymentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmOrder();
            }
        });
    }

    private void confirmOrder() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/train_food/api/orderFood.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if (response.equals("failed")){
                    Toast.makeText(OrderActivity.this, "Failed to Order", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(OrderActivity.this,PaymentActivity.class);
                    intent.putExtra("orderId",response);
                    intent.putExtra("amount",amount);
                    startActivity(intent);
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
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("uid",uid);
                params.put("trainDetails",trainDetailsET.getText().toString());
                params.put("name",nameET.getText().toString());
                params.put("contact",contactET.getText().toString());
                params.put("trainNo",trainNoET.getText().toString());
                params.put("coach",coachET.getText().toString());
                params.put("seatNo",contactET.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(OrderActivity.this);
        requestQueue.add(stringRequest);
    }
}