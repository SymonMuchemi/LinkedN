package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LandingPage extends AppCompatActivity {
    Navigation appNavigation = new Navigation(this, LandingPage.this);
    ShapeableImageView userCard;
    Button exit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mAuth = FirebaseAuth.getInstance();

        userCard = findViewById(R.id.user_card_image);
        exit = findViewById(R.id.logout_button);

        userCard.setOnClickListener( v -> { appNavigation.moveToActivity(popupWindow.class);});
        exit.setOnClickListener(
                v -> {
                    mAuth.signOut();
                    appNavigation.moveToLogin();
                }
        );
    }
}