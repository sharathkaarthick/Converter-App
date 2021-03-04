package com.example.converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FragmentCurrency extends Fragment {
    public FragmentCurrency() {
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
        View v = inflater.inflate(R.layout.fragment_currency, container, false);


        //Initialize Spinners
        Spinner currency1 = (Spinner) v.findViewById(R.id.currency1);
        ArrayAdapter<String> currencyAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currency));
        currencyAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency1.setAdapter(currencyAdapter1);

        Spinner currency2 = (Spinner) v.findViewById(R.id.currency2);
        ArrayAdapter<String> currencyAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currency));
        currencyAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currency2.setAdapter(currencyAdapter2);

        return v;
    }
}