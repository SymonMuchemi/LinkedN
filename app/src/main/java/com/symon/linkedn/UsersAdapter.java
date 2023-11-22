package com.symon.linkedn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {



    private List<User> users;

    public UsersAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.userName.setText(user.getName());
        holder.mobileNo.setText(String.valueOf(user.getMobileNo()));
        holder.shortBio.setText(user.getShortBio());
        holder.gender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userName,mobileNo,gender,shortBio;
        ShapeableImageView shapeableImageView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            mobileNo = itemView.findViewById(R.id.phoneNumber);
            gender = itemView.findViewById(R.id.gender);
            shortBio = itemView.findViewById(R.id.short_bio);
            shapeableImageView = itemView.findViewById(R.id.shapeableImageView);
        }
    }
}
