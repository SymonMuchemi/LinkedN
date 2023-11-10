package com.symon.linkedn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;


import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class profileDetails extends AppCompatActivity {

    FloatingActionButton button;
    ImageView shapeableImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.light_blue)));

        button = findViewById(R.id.floatingActionButton);
        shapeableImageView = findViewById(R.id.shapeableImageView);

        button.setOnClickListener(
                v -> ImagePicker.with(this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start()
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;
        Uri uri = data.getData();
        shapeableImageView.setImageURI(uri);
    }
}