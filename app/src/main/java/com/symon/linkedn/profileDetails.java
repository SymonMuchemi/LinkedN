package com.symon.linkedn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class profileDetails extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    Button updateDetailsButton;
    ImageView shapeableImageView;
    EditText phoneNumber, shortBio, skill;
    String name, email, password, gender, userId;
    String skills, bio;
    Integer mobile;
    Navigation appNavigation;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.light_blue)));

        appNavigation = new Navigation(this, profileDetails.this);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        floatingActionButton = findViewById(R.id.floatingActionButton);
        updateDetailsButton = findViewById(R.id.update_details_button);
        shapeableImageView = findViewById(R.id.shapeableImageView);
        phoneNumber = findViewById(R.id.phone_number);
        shortBio = findViewById(R.id.short_bio_field);
        skill = findViewById(R.id.skill_tab);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        gender = intent.getStringExtra("gender");
        password = intent.getStringExtra("password");

        floatingActionButton.setOnClickListener(
                v -> ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start()
        );

        updateDetailsButton.setOnClickListener(v -> {

            bio = shortBio.getText().toString().trim();
            mobile = Integer.parseInt(phoneNumber.getText().toString().trim());
            skills = String.valueOf(skill.getText()).trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                       if (task.isSuccessful()){
                           Log.d("FB_AUTH", "CreateUserWithEmail: success");
                           firebaseUser = mAuth.getCurrentUser();
                           userId = firebaseUser.getUid().toString();
                            insertUserData();
                       } else {
                           Log.w("FB_AUTH", "CreateUserWithEmail: failure", task.getException());
                           Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                       }
                    });
        });
    }

    public void insertUserData(){
        if (firebaseUser != null) {
            User newUser = new User(name, email, userId, mobile, gender,bio);
            firebaseFirestore.collection("users").document(email).set(newUser);
            Toast.makeText(this, "Details entered successfully!", Toast.LENGTH_SHORT).show();
            appNavigation.moveToActivity(LandingPage.class);
        } else {
            Toast.makeText(this, "Unable to create user!", Toast.LENGTH_SHORT).show();
            appNavigation.moveToActivity(LandingPage.class);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;
        Uri uri = data.getData();
        shapeableImageView.setImageURI(uri);
    }
}