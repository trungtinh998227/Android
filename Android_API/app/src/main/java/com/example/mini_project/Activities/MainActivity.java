package com.example.mini_project.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.mini_project.Activities.Model.User;
import com.example.mini_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String URL="http://192.168.1.9:3010/api/dev";
    private EditText etUsername, etPassword;
    private TextView tvRegister;
    private Button btnLogin;
    private RequestQueue queue;
    private AlertDialog.Builder alertDialog;
    private List<User> listUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        arrayRequest();
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0 ; i< listUser.size(); i++){
                    //user name and password empty
                    if(etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, R.string.enter_login_infor,Toast.LENGTH_SHORT).show();
                    }
                    else if(etUsername.getText().toString().equals(listUser.get(i).getUserName())&&
                            !etPassword.getText().toString().equals(listUser.get(i).getPassword())){
                        Log.d("Username",etUsername.getText().toString()+"---"+listUser.get(i).getUserName());
                        alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Login Failed");
                        alertDialog.setMessage("The password entered is incorrect.");
                        alertDialog.setCancelable(false);
                        alertDialog.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                dialog1.cancel();
                            }
                        });
                        AlertDialog dialog1 = alertDialog.create();
                        dialog1.show();
                    }
                    else if(!etUsername.getText().toString().equals(listUser.get(i).getUserName()) &&
                             !etUsername.getText().toString().equals("")){
                        Log.d("Username",etUsername.getText().toString()+"---"+listUser.get(i).getUserName());
                        alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Login Failed");
                        alertDialog.setMessage("The username and password entered is incorrect.\nDo you want to register?");
                        alertDialog.setCancelable(false);
                        alertDialog.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                                startActivity(intent);
                            }
                        });
                        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = alertDialog.create();
                        dialog.show();
                    }
                    else
                    {
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        intent.putExtra("UserData", (Serializable) listUser);
                        startActivity(intent);
                    }
                }
            }
        });
    }
        private void arrayRequest(){
            queue = Volley.newRequestQueue(this);
            JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,URL, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i=0; i< response.length();i++){
                        try {
                            JSONObject object = response.getJSONObject(i);
                            User newUser= new User();
                            newUser.setUserName(object.getString("username"));
                            newUser.setPassword(object.getString("password"));
                            newUser.setBirthday(object.getString("birth"));
                            newUser.setName(object.getString("fullname"));
                            listUser.add(newUser);
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("------Error------", error.getMessage());
                }
            });
            queue.add(arrayRequest);
        }
}
