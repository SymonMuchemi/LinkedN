package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;

public class LandingPage extends AppCompatActivity {
    Navigation appNavigation = new Navigation(this, LandingPage.this);
    ShapeableImageView userCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        userCard = findViewById(R.id.user_card_image);

        userCard.setOnClickListener( v -> { appNavigation.moveToActivity(popupWindow.class);});
    }
}