package com.shsaad.sayhi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shsaad.sayhi.ui.User;

import java.util.List;

public class User_Adpter extends RecyclerView.Adapter<User_viewHolder> {
    @NonNull
    private Context context;
    private List<User>userList;

    public User_Adpter(@NonNull Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public User_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);

        return new User_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_viewHolder holder, int position) {

        holder.name.setText(userList.get(position).getUserName());
        holder.gmail.setText(userList.get(position).getUserEmail());



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
