package chriatian.tuanhuydev.todo;

        import android.annotation.SuppressLint;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.UnsupportedEncodingException;

@SuppressLint("Registered")
public class RegisterActivity extends AppCompatActivity {
    public static final String URL_POST="http://192.168.1.11:3010/api/dev/insert";
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
    private void senDataServer(String username, String pass, String birth, String name) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", pass);
            jsonObject.put("fullname", name);
            jsonObject.put("birth", birth);
            final String object= jsonObject.toString();
            StringRequest js = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response: ",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ",error.toString());
            }
        }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @SuppressLint("LongLogTag")
                @Override
                public byte[] getBody(){
                    try {
                        return object == null ? null : object.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        //Log.v("Unsupported Encoding while trying to get the bytes", object);
                        return null;
                    }
                }
            };
        queue.add(js);
    }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
