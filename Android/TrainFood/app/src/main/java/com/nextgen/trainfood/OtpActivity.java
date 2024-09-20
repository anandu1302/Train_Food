package com.nextgen.trainfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class OtpActivity extends AppCompatActivity {

    EditText mPinFirstDigitEditText;
    EditText mPinSecondDigitEditText;
    EditText mPinThirdDigitEditText;
    EditText mPinForthDigitEditText;
    Button verifyBT;

    private GlobalPreference globalPreference;

    String token="";
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        globalPreference = new GlobalPreference(this);

        setDefaults();

        number = getIntent().getStringExtra("number");

        if (number.equals("")){
            Toast.makeText(this, "Unable to fetch number", Toast.LENGTH_SHORT).show();
        }else{

            sendSMS(number,token);
        }

        mPinFirstDigitEditText = findViewById(R.id.pin_first_edittext);
        mPinSecondDigitEditText = findViewById(R.id.pin_second_edittext);
        mPinThirdDigitEditText = findViewById(R.id.pin_third_edittext);
        mPinForthDigitEditText = findViewById(R.id.pin_four_edittext);
        verifyBT = findViewById(R.id.verifyButton);

        verifyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });

    }

    private void verify() {

        String num1 = mPinFirstDigitEditText.getText().toString();
        String num2 = mPinSecondDigitEditText.getText().toString();
        String num3 = mPinThirdDigitEditText.getText().toString();
        String num4 = mPinForthDigitEditText.getText().toString();

        String enteredPin = num1+num2+num3+num4;

        //verifying otp by comparing the entered otp and generated Token

        String verify_token = enteredPin;
        if(verify_token.equals(token)){
            Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(OtpActivity.this,LoginActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Invalid OTP Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(String phoneNo, String msg) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, "Use OTP "+msg+" to verify your account in Train Food ", null, null);

            Toast.makeText(getApplicationContext(), "Otp Sent",Toast.LENGTH_LONG).show();

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void setDefaults() {
        Random rand = new Random();
        token=String.format("%04d", rand.nextInt(10000));
    }
}