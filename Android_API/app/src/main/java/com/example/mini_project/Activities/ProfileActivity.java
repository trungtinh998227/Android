package com.example.mini_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mini_project.Activities.Adapter.MyAdapter;
import com.example.mini_project.Activities.Model.User;
import com.example.mini_project.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listUser = new ArrayList<>();
        Intent intent = getIntent();
        listUser = (List<User>) intent.getSerializableExtra("UserData");
        adapter = new MyAdapter(this,listUser);
        recyclerView.setAdapter(adapter);
    }
}
