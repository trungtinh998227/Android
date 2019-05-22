package chriatian.tuanhuydev.todo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Controllers.checkUser;
import Models.User;

import static android.view.animation.AnimationUtils.*;

public class SplashActivity extends AppCompatActivity {
    public static final String URL="http://192.168.1.31:3010/api/dev";
    private ImageView icon;
    private Button register;
    private TextView logan;
    private List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icon = findViewById(R.id.icon);
        register = findViewById(R.id.registerID);
        logan = findViewById(R.id.loganId);
        @SuppressLint("ResourceType")
        Animation fadein = loadAnimation(this,R.animator.fade_in);
        logan.setAnimation(fadein);
        @SuppressLint("ResourceType")
        Animation frombottom = loadAnimation(this,R.animator.frombottom);
        register.setAnimation(frombottom);
        @SuppressLint("ResourceType")
        Animation fromtop = loadAnimation(this,R.animator.fromtop);
        icon.setAnimation(fromtop);
        getUser();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.putExtra("UserData", (Serializable) users);
                startActivity(intent);
            }
        });
    }
    private void getUser(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        User newUser = new User();
                        JSONObject object = response.getJSONObject(i);
                        newUser.setUserName(object.getString("username"));
                        newUser.setPassword(object.getString("password"));
                        newUser.setBirthday(object.getString("birth"));
                        newUser.setName(object.getString("fullname"));
                        users.add(newUser);
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("------errrrorrr-----",error.toString());
            }
        });
        queue.add(arrayRequest);
    }
}