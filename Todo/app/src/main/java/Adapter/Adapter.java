package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Models.User;
import chriatian.tuanhuydev.todo.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<User> listUser;

    public Adapter(Context context, List listUser){
        this.context=context;
        this.listUser=listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_user,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Log.d("---------Infor--------",listUser.get(i).toString());
        viewHolder.username.setText(String.format("UserName: %s", listUser.get(i).getUserName()));
        viewHolder.pass.setText(String.format("Password: %s", listUser.get(i).getPassword()));
        viewHolder.birth.setText(String.format("Birthday: %s", listUser.get(i).getBirthday()));
        viewHolder.name.setText(String.format("FullName: %s", listUser.get(i).getName()));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username, pass, name, birth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            pass = itemView.findViewById(R.id.password);
            name = itemView.findViewById(R.id.fullname);
            birth = itemView.findViewById(R.id.birthday);
        }
    }
}
