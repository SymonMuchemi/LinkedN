package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    Navigation appNavigation = new Navigation(this, Login.this);
    Button loginButton;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.sign_in_button);
        signUp = findViewById(R.id.sign_up_text);

        loginButton.setOnClickListener( v -> { appNavigation.moveToActivity(LandingPage.class);});
        signUp.setOnClickListener( v -> { appNavigation.moveToActivity(SignUp.class); });
    }
}