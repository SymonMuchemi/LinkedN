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

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Navigation appNavigation = new Navigation(this, SignUp.this);

    Button signUp;
    EditText emailInput, passwordInput, nameInput;
    String name, gender, email, password, userId;
    private FirebaseAuth mAuth;
    User newUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

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

            databaseReference = database.getReference().child("Users");
            databaseReference.setValue(name);

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("FB_AUTH", "createUserWithEmail:success");
                        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        firebaseUser = mAuth.getCurrentUser();
                        insertUserData();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("FB_AUTH", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            );
        });
    }
    public void insertUserData(){
        if (firebaseUser != null) {
            User newUser = new User(name, email, userId);
//            databaseReference.child(userId).setValue(newUser);
            Map <String, Object> userDetails = new HashMap<>();
            userDetails.put("Name", newUser.getName());
            userDetails.put("Email", newUser.getEmail());

            databaseReference.child("User").child("UserID").setValue(userDetails);

            Toast.makeText(this, "Details enter successfully!", Toast.LENGTH_SHORT).show();
            appNavigation.moveToActivity(LandingPage.class);
        }
        else {
            Toast.makeText(this, "Unable to create user profile", Toast.LENGTH_SHORT).show();
            appNavigation.moveToActivity(Login.class);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}