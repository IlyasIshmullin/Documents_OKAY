package com.example.documents_okay.authorization;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.documents_okay.R;


public class ResetPasswordFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewResetPasswordFragment = inflater.inflate(R.layout.fragment_reset_password, container, false);

        return inflater.inflate(R.layout.fragment_reset_password, container, false);
    }
}