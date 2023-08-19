package com.example.rifleshootingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    private Button btnCheck;
    private Button btnExercise;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnCheck = rootView.findViewById(R.id.btnCheckPosture);
        btnCheck.setOnClickListener(view -> openPostureCheck());

        btnExercise = rootView.findViewById(R.id.btnExercise);
        btnExercise.setOnClickListener(view -> openExersice());

        return rootView;
    }

    public void openPostureCheck(){
        Context context = requireContext(); // Get the context of the fragment
        Intent intent = new Intent(context, Posturecheck.class);
        startActivity(intent);
    }

    public void openExersice(){
        Context context = requireContext(); // Get the context of the fragment
        Intent intent = new Intent(context, Exercise.class);
        startActivity(intent);
    }
}




