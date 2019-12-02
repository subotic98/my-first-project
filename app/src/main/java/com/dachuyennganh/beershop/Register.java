package com.dachuyennganh.beershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dachuyennganh.beershop.app.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import static com.dachuyennganh.beershop.ultil.servser.url_register;


public class Register extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";

    EditText etUsername,etPassword,etConfirmPassword,etFullName;
    Button register;
    private String username,password,confirmPassword,fullName;
    //private String register_url="http://192.168.1.2/Beershop/registernew.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControl();
        addEvent();
    }

    private void addEvent() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                fullName = etFullName.getText().toString().trim();
                if (validateInputs()) {
                    registerUser();

                }else{
                    Toast.makeText(Register.this, "loi", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void addControl() {
        etUsername=findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFullName = findViewById(R.id.etFullName);

        register = findViewById(R.id.btnRegister);
    }

    private  void registerUser(){

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
            request.put(KEY_FULL_NAME, fullName);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, url_register, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Register.this, "asd", Toast.LENGTH_SHORT).show();
                        try {
                            //Check if user got registered successfully
                            Toast.makeText(Register.this, "test", Toast.LENGTH_SHORT).show();
                            if (response.getInt(KEY_STATUS) == 0) {
                                Intent i = new Intent(Register.this, Products.class);
                                startActivity(i);
                                finish();
                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                etUsername.setError("Username already taken!");
                                etUsername.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        VolleySingleton.getInstance().addToRequestQueue(jsArrayRequest);
    }
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(fullName)) {
            etFullName.setError("Full Name cannot be empty");
            etFullName.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(username)) {
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(confirmPassword)) {
            etConfirmPassword.setError("Confirm Password cannot be empty");
            etConfirmPassword.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password and Confirm Password does not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
}
