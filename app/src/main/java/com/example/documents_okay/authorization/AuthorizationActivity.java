package com.example.documents_okay.authorization;

import static com.example.documents_okay.SplashActivity.APP_PREFERENCES_CHECK;
import static com.example.documents_okay.SplashActivity.dataUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.documents_okay.MainMenu.MainMenuActivity;
import com.example.documents_okay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthorizationActivity extends AppCompatActivity {
    private EditText editTextEmailAddress;
    private EditText editTextPassword;

    private Button logInButton;
    private Button createNewAccountButton;

    public static FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        editTextEmailAddress = findViewById(R.id.edit_text_email_register);
        editTextPassword = findViewById(R.id.edit_text_password_register);

        logInButton = findViewById(R.id.log_in_button);
        createNewAccountButton = findViewById(R.id.register_button);

        firebaseAuth = FirebaseAuth.getInstance();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn(editTextEmailAddress.getText().toString(), editTextPassword.getText().toString());
            }

        });

        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.registration_fragment_container_view, RegistrationFragment.class, null)
                        .commit();
            }
        });
    }

    private void logIn(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(AuthorizationActivity.this, "Enter email and password", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(AuthorizationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AuthorizationActivity.this, "OK", Toast.LENGTH_SHORT)
                                    .show();
                            SharedPreferences.Editor editor = dataUser.edit();
                            editor.putBoolean(APP_PREFERENCES_CHECK, true).apply();
                            Intent intent = new Intent(AuthorizationActivity.this, MainMenuActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}