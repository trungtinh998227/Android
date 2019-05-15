package com.example.mini_project.Activities.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mini_project.Activities.Model.User;
import com.example.mini_project.R;

import java.util.List;

import static com.example.mini_project.R.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<User> userList;
    public MyAdapter(Context context, List userList ){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.username.setText(String.format("UserName: %s", userList.get(i).getUserName()));
            viewHolder.pass.setText(String.format("Password: %s", userList.get(i).getPassword()));
            viewHolder.birth.setText(String.format("Birthday: %s", userList.get(i).getBirthday()));
            viewHolder.name.setText(String.format("FullName: %s", userList.get(i).getName()));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username, pass, name, birth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(id.userId);
            pass = itemView.findViewById(id.passId);
            name= itemView.findViewById(id.nameId);
            birth= itemView.findViewById(id.birthId);
        }
    }
}
