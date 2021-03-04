package com.example.converter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentDistance extends Fragment {
    public FragmentDistance() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_distance, container, false);

        //Initialize Spinners
        Spinner length1 = (Spinner) v.findViewById(R.id.length1);
        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.length));
        lengthAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        length1.setAdapter(lengthAdapter1);

        Spinner length2 = (Spinner) v.findViewById(R.id.length2);
        ArrayAdapter<String> lengthAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.length));
        lengthAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        length2.setAdapter(lengthAdapter2);

        return v;
    }


}