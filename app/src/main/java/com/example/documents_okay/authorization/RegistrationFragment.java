package com.example.documents_okay.authorization;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.documents_okay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationFragment extends Fragment {
    private FirebaseAuth firebaseAuth;

    private Button registerButton;

    private EditText emailRegister;
    private EditText passwordRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration,
                container, false);

        registerButton = view.findViewById(R.id.register_button);
        emailRegister = view.findViewById(R.id.edit_text_email_register);
        passwordRegister = view.findViewById(R.id.edit_text_password_register);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(emailRegister.getText().toString(), passwordRegister.getText().toString());
            }
        });
    }

    private void registration(String email, String password) {
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT)
                                    .show();
                    }
                });
    }
}