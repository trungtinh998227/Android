package chriatian.tuanhuydev.todo;

        import android.annotation.SuppressLint;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Layout;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;

@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {
    public static final String URL_POST="http://192.168.1.31:3010/api/dev/insert";
    private EditText username, password, birthday, name;
    private Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.usernameID);
        password=findViewById(R.id.passID);
        birthday=findViewById(R.id.birthID);
        name=findViewById(R.id.fullnameID);
        register=findViewById(R.id.registerID);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senDataServer(username.getText().toString(),password.getText().toString(),birthday.getText().toString(),name.getText().toString());
            }
        });
    }
    private void senDataServer(String username, String pass, String birth, String name){
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username",username);
            jsonObject.put("password",pass);
            jsonObject.put("fullname",name);
            jsonObject.put("birth",birth);
        }catch(JSONException e){
            Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST,jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(RegisterActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(jsonObjectRequest);
        }

    }
}
