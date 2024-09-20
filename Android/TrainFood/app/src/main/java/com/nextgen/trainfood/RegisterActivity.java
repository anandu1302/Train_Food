package com.nextgen.trainfood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText nameET;
    EditText numberET;
    EditText emailET;
    EditText passwordET;
    Button registerBT;
    TextView signInTV;
    CircleImageView userImageIV;

    private Bitmap bitmap;

    private GlobalPreference globalPreference;
    private String ip;

    private String image = "";
    private boolean iset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.getIP();

        nameET = findViewById(R.id.nameEditText);
        numberET = findViewById(R.id.numberEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        registerBT = findViewById(R.id.registerButton);
        signInTV = findViewById(R.id.signInTextView);
        userImageIV = findViewById(R.id.circleImageView);

        userImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });


    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                userImageIV.setImageBitmap(bitmap);
                iset = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void check() {

        if (nameET.getText().toString().equals("") || numberET.getText().toString().equals("") ||
                emailET.getText().toString().equals("") || passwordET.getText().toString().equals("")){

            Toast.makeText(this, "Please Fill Fields", Toast.LENGTH_SHORT).show();

        } else if (iset == true) {

            image = getStringImage(bitmap);
            register();

        } else{
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void register() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ ip +"/train_food/api/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("success")){

                    Intent intent = new Intent(RegisterActivity.this,OtpActivity.class);
                    intent.putExtra("number",numberET.getText().toString());
                    startActivity(intent);

                }else {
                    Toast.makeText(RegisterActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,""+error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            @Nullable
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nameET.getText().toString());
                params.put("number", numberET.getText().toString());
                params.put("email", emailET.getText().toString());
                params.put("password", passwordET.getText().toString());
                params.put("image", image);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
    }
}