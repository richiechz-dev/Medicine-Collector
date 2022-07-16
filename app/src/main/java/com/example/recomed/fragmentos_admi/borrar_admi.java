package com.example.recomed.fragmentos_admi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recomed.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link borrar_admi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class borrar_admi extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrar_admi, container, false);
    }
}