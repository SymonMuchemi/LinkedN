package com.symon.linkedn;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<User> users;
    WriteToLoggerFile loggerFile = new WriteToLoggerFile("imageLogs.txt");

    StorageReference firebaseStorageReference;

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
        String imageName = user.getUserId() + ".jpg";

        firebaseStorageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageReference = firebaseStorageReference.child("user_images/" + imageName);

        holder.userName.setText(user.getName());
        holder.mobileNo.setText(String.valueOf(user.getMobileNo()));
        holder.shortBio.setText(user.getShortBio());
        holder.gender.setText(user.getGender());
        holder.skills.setText(user.getSkills());
//        holder.shapeableImageView.setImageURI(imageReference.getDownloadUrl().getResult());

        if (holder.shapeableImageView != null) {
            Glide.with(holder.itemView.getContext())
                    .load(imageReference)
                    .placeholder(R.drawable.linkedin)
                    .error(R.drawable.linkedin)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Image load failed: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Glide", "Image load successful");
                            loggerFile.writeToFile("Image loaded from: " + imageReference);
                            return false;
                        }
                    })
                    .into(holder.shapeableImageView);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userName,mobileNo,gender,shortBio, skills;
        ShapeableImageView shapeableImageView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            mobileNo = itemView.findViewById(R.id.phoneNumber);
            gender = itemView.findViewById(R.id.gender);
            shortBio = itemView.findViewById(R.id.short_bio);
            shapeableImageView = itemView.findViewById(R.id.shapeableImageView);
            skills = itemView.findViewById(R.id.skills_tab);
        }
    }
}
