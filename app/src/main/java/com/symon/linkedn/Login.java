package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.sign_in_button);

        loginButton.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, SignUp.class);
                    startActivity(intent);
                }
        );
    }
}