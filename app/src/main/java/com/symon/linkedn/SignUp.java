package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button signUp;
    EditText emailInput,nameInput;
    TextInputEditText passwordInput;
    String name, gender, email, password;

    Navigation appNavigation = new Navigation(this, SignUp.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        nameInput = findViewById(R.id.sign_up_name);
        signUp = findViewById(R.id.sign_up_button);
        emailInput = findViewById(R.id.sign_up_email_input);
        passwordInput = findViewById(R.id.password_1);

        signUp.setOnClickListener( v -> {
            if (!appNavigation.checkInputField(nameInput)) return;
            if (!appNavigation.checkInputField(emailInput)) return;
            if (!appNavigation.checkInputField(passwordInput)) return;

            name = String.valueOf(nameInput.getText()).trim();
            email = String.valueOf(emailInput.getText()).trim();
            password = String.valueOf(passwordInput.getText()).trim();


            Intent intent = new Intent(this, profileDetails.class);
            intent.putExtra("name", name);
            intent.putExtra("password", password);
            intent.putExtra("email", email);
            intent.putExtra("gender", gender);
            startActivity(intent);

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}