package com.symon.linkedn;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Navigation {
    private  Context currentClass;
    private Activity currentActivity;
    Intent destination;

    public Navigation(Context currentClass, Activity currentActivity) {
        this.currentClass = currentClass;
        this.currentActivity = currentActivity;
    }

    public void moveToActivity(Class<?> destActivity){
        destination = new Intent(currentClass, destActivity);
        currentActivity.startActivity(destination);
    }
    public void moveToLogin(){
        Intent home = new Intent(currentClass, Login.class);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivity(home);
    }
    public void sessionStart(@NonNull Button button, @NonNull ProgressBar progressBar){
        button.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
    public void sessionComplete(Button button, ProgressBar progressBar){
        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public boolean checkInputField(EditText editText){
        String inputString = editText.getText().toString();

        Drawable errorBorderBg = ContextCompat.getDrawable(editText.getContext(),R.drawable.error_input_border);

        if (inputString.isEmpty()){
            editText.requestFocus();
            if (errorBorderBg != null) editText.setBackground(errorBorderBg);
            editText.setError("This field cannot be empty!");
            return false;
        }

        return true;
    }
}
