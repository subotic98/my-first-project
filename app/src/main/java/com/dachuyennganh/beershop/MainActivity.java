package com.dachuyennganh.beershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dachuyennganh.beershop.app.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import static com.dachuyennganh.beershop.ultil.servser.url_Login;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "message";
    private static final String KEY_STATUS = "status";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    EditText edtloginusername,etpassword;
    Button btnlogin;
    Button btnregister;
    private String username,password;
    //private String login_url = "http://192.168.1.2/Beershop/loginnew.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addcontrol();
        addEvent();
    }

    private void addEvent() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtloginusername.getText().toString().toLowerCase().trim();
                password= etpassword.getText().toString().trim();
                if(validateInputs()){
                    login();
                }
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void addcontrol() {
        edtloginusername = findViewById(R.id.etLoginUsername);
        etpassword=findViewById(R.id.etLoginPassword);
        btnlogin=findViewById(R.id.btnLogin);
        btnregister=findViewById(R.id.btnLoginRegister);
    }

    private  void login(){
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest(Request.Method.POST, url_Login, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt(KEY_STATUS) == 0){
                        Intent intent = new Intent(MainActivity.this,Products.class);
                        startActivity(intent);

                    }
                    else{
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
                error.printStackTrace();
            }
        });
        VolleySingleton.getInstance().addToRequestQueue(jsArrayRequest);
    }

    private boolean validateInputs() {
        if(KEY_EMPTY.equals(username)){
            edtloginusername.setError("Username cannot be empty");
            edtloginusername.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            etpassword.setError("Password cannot be empty");
            etpassword.requestFocus();
            return false;
        }
        return true;
    }
}
