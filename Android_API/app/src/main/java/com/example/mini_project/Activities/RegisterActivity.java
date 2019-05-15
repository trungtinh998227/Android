package com.example.mini_project.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mini_project.R;

import com.example.mini_project.Activities.Data.DBManagement;
import com.example.mini_project.Activities.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    public static final String URL_POST="http://192.168.1.9:3000/api/dev/insert";
    private EditText username, password, birthday, name;
    private Button register;
    //private  DBManagement db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

        username = findViewById(R.id.et_reg_username);
        password = findViewById(R.id.et_reg_password);
        birthday = findViewById(R.id.et_reg_birth);
        name = findViewById(R.id.et_name);
        register= findViewById(R.id.btn_register);
        //setDb();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senDataServer(username.getText().toString(),password.getText().toString(),birthday.getText().toString(),name.getText().toString());
            }
        });
    }
    private void senDataServer(String username, String pass, String birth, String name){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username",username);
            jsonObject.put("password",pass);
            jsonObject.put("fullname",name);
            jsonObject.put("birth",birth);
        }catch(JSONException e){
            Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Toast.makeText(RegisterActivity.this,response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("------errror-------",error.toString());

            }
        });
        queue.add(jsonObjectRequest);
    }
}
