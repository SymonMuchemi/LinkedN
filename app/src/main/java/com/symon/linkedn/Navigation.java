package com.symon.linkedn;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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

}
