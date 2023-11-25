package com.symon.linkedn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class popupWindow extends AppCompatActivity {
    Button emailButton;
    Button mobileCallButton;
    TextView emailTextView;
    String emailInput;

    static final int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = (int) (displayMetrics.widthPixels * 0.99);
        int height = (int) (displayMetrics.heightPixels *.25);

        getWindow().setLayout(width, height);

        emailButton = findViewById(R.id.send_email_button);
        mobileCallButton = findViewById(R.id.call_button);
        emailTextView = findViewById(R.id.popup_window_email_input);


        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("email");
        String mobileNo = intent.getStringExtra("phone");
        Log.d("MOBILE", mobileNo);
        if (ContextCompat.checkSelfPermission(popupWindow.this, getPackageName() + ".permission.CALL_PHONE") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(popupWindow.this, new String[]{android.Manifest.permission.CALL_PHONE}, PERMISSION_CODE);
        }

        mobileCallButton.setOnClickListener(v -> {
            if (mobileNo != null && !mobileNo.isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileNo));
                if (callIntent.getData() != null) {
                    Log.d("MOBILE_URI", callIntent.getData().toString());
                } else {
                    Log.e("MOBILE_URI", "Call URI is null");
                }
                startActivity(callIntent);
            } else {
                Log.e("MOBILE_URI", "Mobile number is null or empty");
            }
        });


        emailButton.setOnClickListener(v -> {
            emailInput = String.valueOf(emailTextView.getText()).trim();
            Toast.makeText(this, "sending email to " + userEmail, Toast.LENGTH_SHORT).show();
        });


    }
}