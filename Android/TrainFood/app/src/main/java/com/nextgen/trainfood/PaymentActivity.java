package com.nextgen.trainfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    TextView amountTV;
    EditText cardNameET;
    EditText cardNumberET;
    EditText cvvET;
    Button makePaymentBT;
    private RadioButton cardRadioButton;
    String cardType;

    private GlobalPreference globalPreference;
    private String ip,uid;

    List<String> month = new ArrayList<>();

    List<String> year = new ArrayList<>();
    private Spinner MMspin;
    private Spinner YYspin;

    private ImageView backIV;

    String orderId,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();
        uid = globalPreference.getID();

        iniit();

        orderId = getIntent().getStringExtra("orderId");
        amount = getIntent().getStringExtra("amount");

        Toast.makeText(this, ""+amount, Toast.LENGTH_SHORT).show();

        month.add("MM");
        for (int i = 1 ; i <= 31 ; i++)
        {
            month.add(String.valueOf(i));
        }

        year.add("YY");
        for(int i = 2020 ; i<=2025 ; i++ )
            year.add(String.valueOf(i));


        // Getting value from Spinners


        MMspin = (Spinner) findViewById(R.id.mmspinner);
        ArrayAdapter MM = new ArrayAdapter(this,android.R.layout.simple_spinner_item,month);
        MM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MMspin.setAdapter(MM);



        YYspin = (Spinner) findViewById(R.id.yyspinner);
        ArrayAdapter YY = new ArrayAdapter(this,android.R.layout.simple_spinner_item,year);
        YY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YYspin.setAdapter(YY);

        // Getting value from Radio Button

        cardRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardRadioButton.isChecked()) {
                    cardType = "Credit";

                }
            }
        });

        amountTV.setText("₹ "+amount);
        

        makePaymentBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!cardRadioButton.isChecked())
                {
                    Toast.makeText(PaymentActivity.this, "Please select a payment method ", Toast.LENGTH_SHORT).show();
                }
                else if(cardRadioButton.isChecked() && cardNumberET.getText().toString().equals("") ||
                        cardRadioButton.isChecked() && cvvET.getText().toString().equals("") ||
                        cardRadioButton.isChecked() && MMspin.getSelectedItem().toString().equals("MM") ||
                        cardRadioButton.isChecked() && YYspin.getSelectedItem().toString().equals("YY"))
                {
                    Toast.makeText(PaymentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (cardNumberET.getText().toString().length() < 16){
                    Toast.makeText(PaymentActivity.this, "Please Enter a Valid Card Number", Toast.LENGTH_SHORT).show();

                }
                else {
                    payNow();

                }
            }
        });


    }

    private void payNow() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/train_food/api/payment.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")){

                    showAlert();

                }
                else{
                    Toast.makeText(PaymentActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            @Nullable
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userId",uid);
                params.put("orderId",orderId);
                params.put("amount",amount);
                params.put("cardname",cardNameET.getText().toString());
                params.put("cardnumber",cardNumberET.getText().toString());
                params.put("cardtype",cardType);
                params.put("cardmonth",MMspin.getSelectedItem().toString());
                params.put("cardyear",YYspin.getSelectedItem().toString());
                params.put("cvv",cvvET.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showAlert() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(" Order Placed");
        alertDialogBuilder.setIcon(R.drawable.check);
        alertDialogBuilder.setMessage("Your order is on the way");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //on Success this action takes place

                Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    private void iniit() {

        amountTV = findViewById(R.id.amountTextView);
        cardNameET = findViewById(R.id.cardNameEditText);
        cardNumberET = findViewById(R.id.cardNumberEditText);
        cvvET = findViewById(R.id.cvvEditText);
        makePaymentBT = findViewById(R.id.makePaymentButton);
        cardRadioButton = findViewById(R.id.radioCard);
    }
}