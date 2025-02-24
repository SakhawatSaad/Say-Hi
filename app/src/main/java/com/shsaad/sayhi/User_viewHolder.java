package com.shsaad.sayhi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class User_viewHolder extends RecyclerView.ViewHolder {

    ImageView image, chat_icon;
    TextView name, gmail;

    public User_viewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.user_name);
        gmail = itemView.findViewById(R.id.user_gmail);
    }
}
