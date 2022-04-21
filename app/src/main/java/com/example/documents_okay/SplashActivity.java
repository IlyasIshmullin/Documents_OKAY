package com.example.documents_okay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.documents_okay.MainMenu.MainMenuActivity;
import com.example.documents_okay.authorization.AuthorizationActivity;


public class SplashActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "CurrentUser";
    public static final String APP_PREFERENCES_CHECK = "isSignIn";
    public static SharedPreferences dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dataUser = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(dataUser.contains(APP_PREFERENCES_CHECK)) {
            if(dataUser.getBoolean(APP_PREFERENCES_CHECK, Boolean.parseBoolean(""))) {
                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, AuthorizationActivity.class);
                startActivity(intent);
            }
        }
        else {
            Intent intent = new Intent(this, AuthorizationActivity.class);
            startActivity(intent);
        }

    }

}