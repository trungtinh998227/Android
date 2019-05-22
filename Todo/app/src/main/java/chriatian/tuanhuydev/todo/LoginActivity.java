package chriatian.tuanhuydev.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import Controllers.checkUser;
import Controllers.showDialert;
import Models.User;

public class LoginActivity extends AppCompatActivity{
    private TextView register;
    private EditText userName, password;
    private Button login;
    private CheckBox checkRemember;
    private checkUser ck;
    private Controllers.showDialert showDialert;
    private List<User> users;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getData from Slash
        Intent intent = getIntent();
        users = (List<User>) intent.getSerializableExtra("UserData");
        for (int i=0; i< users.size();i++){
            Log.d("-----Final List------",users.get(i).toString());
        }
        ck = new checkUser(users);
        register = findViewById(R.id.etRegisterID);
        userName = findViewById(R.id.usernameId_login);
        password = findViewById(R.id.passId_login);
        login = findViewById(R.id.loginID);
        checkRemember = findViewById(R.id.cbRememberPass);

        String regisetr_link = "Don't have an account. Register here!";
        SpannableString ss = new SpannableString(regisetr_link);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(true);
            }
        };
        register.setClickable(true);
        ss.setSpan(clickableSpan,23,37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(ss);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ck.checkUserName(userName.getText().toString())){
                    showDialert = new showDialert(LoginActivity.this,"Login Fail","Invalid username");
                }
                if(ck.checkUserName(userName.getText().toString()) && !ck.checkPass(password.getText().toString())){
                    showDialert = new showDialert(LoginActivity.this,"Login Fail","Invalid password");
                }
                if(ck.checkUserName(userName.getText().toString()) && ck.checkPass(password.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("UserData", (Serializable) users);
                    startActivity(intent);
                }
            }
        });
    }
}
