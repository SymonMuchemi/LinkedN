package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Navigation appNavigation = new Navigation(this, SignUp.this);

    Button signUp;

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
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        signUp = findViewById(R.id.sign_up_button);

        signUp.setOnClickListener( v -> { appNavigation.moveToActivity(profileDetails.class); });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(
                this,
                text,
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}