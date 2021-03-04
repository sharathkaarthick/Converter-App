package com.example.converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class FragmentTime extends Fragment {
    public FragmentTime() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_time, container, false);


        //Initialize Spinners
        Spinner time1 = (Spinner) v.findViewById(R.id.time1);
        ArrayAdapter<String> timeAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.time));
        timeAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time1.setAdapter(timeAdapter1);

        Spinner time2 = (Spinner) v.findViewById(R.id.time2);
        ArrayAdapter<String> timeAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.time));
        timeAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time2.setAdapter(timeAdapter2);

        return v;
    }
}