package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Navigation appNavigation = new Navigation(this, SignUp.this);

    Button signUp;
    EditText emailInput, passwordInput, nameInput;
    String name, gender, email, password;
    private FirebaseAuth mAuth;
    User newUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in already and move to landing page
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(this, "Already signed in", Toast.LENGTH_SHORT).show();
            appNavigation.moveToActivity(LandingPage.class);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

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
            name = String.valueOf(nameInput.getText()).trim();
            email = String.valueOf(emailInput.getText());
            password = String.valueOf(passwordInput.getText()).trim();

            mDatabase = database.getReference("Users");
            mDatabase.setValue(name);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FB_AUTH", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            newUser = new User(name, email, user.getUid());
                            newUser.writeNewUser(user.getUid());
                            appNavigation.moveToActivity(LandingPage.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FB_AUTH", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
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