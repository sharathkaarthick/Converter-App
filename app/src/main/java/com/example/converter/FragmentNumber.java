package com.example.converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class FragmentNumber extends Fragment {

    public FragmentNumber() {
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
        View v = inflater.inflate(R.layout.fragment_number, container, false);


        //Initialize Spinners
        Spinner number1 = (Spinner) v.findViewById(R.id.number1);
        ArrayAdapter<String> numberAdapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.number));
        numberAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        number1.setAdapter(numberAdapter1);
        number1.setSelection(0);

        Spinner number2 = (Spinner) v.findViewById(R.id.number2);
        ArrayAdapter<String> numberAdapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.number));
        numberAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        number2.setAdapter(numberAdapter2);
        number2.setSelection(0);

        Button bt = (Button) v.findViewById(R.id.number_convert);

        EditText ip1 = (EditText) v.findViewById(R.id.ip_number1);
        EditText ip2 = (EditText) v.findViewById(R.id.ip_number2);

        number1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ip1.setText(null);
                ip2.setText(null);

                if(number1.getSelectedItemId() == 0)
                {
                    ip1.setKeyListener(DigitsKeyListener.getInstance("01"));
                }
                if(number1.getSelectedItemId() == 1)
                {
                    ip1.setKeyListener(DigitsKeyListener.getInstance("01234"));
                }
                if(number1.getSelectedItemId() == 2)
                {
                    ip1.setKeyListener(DigitsKeyListener.getInstance("01234567"));
                }
                if(number1.getSelectedItemId() == 3)
                {
                    ip1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                }
                number2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ip2.setText(null);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ip1.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "No input Given", Toast.LENGTH_LONG).show();
                }
                else {
                    switch(Integer.parseInt(String.valueOf(number1.getSelectedItemId())))
                    {
                        case 0:
                        {
                            switch (Integer.parseInt(String.valueOf(number2.getSelectedItemId())))
                            {
                                case 0:
                                    Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    break;

                                case 1:
                                    int dec = Integer.parseInt(ip1.getText().toString(),2);
                                    ip2.setText(Integer.toString(toany(dec,5)));
                                    break;

                                case 2:
                                    dec = Integer.parseInt(ip1.getText().toString(),2);
                                    ip2.setText(Integer.toString(toany(dec,8)));
                                    break;

                                case 3:
                                    dec = Integer.parseInt(ip1.getText().toString(),2);
                                    ip2.setText(hex(dec));
                                    break;
                            }
                            break;
                        }

                        case 1:
                        {
                            switch (Integer.parseInt(String.valueOf(number2.getSelectedItemId())))
                            {
                                case 0:
                                    int dec = Integer.parseInt(ip1.getText().toString(),5);
                                    ip2.setText(Integer.toString(toany(dec,2)));
                                    break;

                                case 1:
                                    Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    break;

                                case 2:
                                    dec = Integer.parseInt(ip1.getText().toString(),5);
                                    ip2.setText(Integer.toString(toany(dec,8)));
                                    break;

                                case 3:
                                    dec = Integer.parseInt(ip1.getText().toString(),5);
                                    ip2.setText(hex(dec));
                                    break;
                            }
                            break;
                        }

                        case 2:
                        {
                            switch (Integer.parseInt(String.valueOf(number2.getSelectedItemId())))
                            {
                                case 0:
                                    int dec = Integer.parseInt(ip1.getText().toString(),8);
                                    ip2.setText(Integer.toString(toany(dec,2)));
                                    break;

                                case 1:
                                    dec = Integer.parseInt(ip1.getText().toString(),8);
                                    ip2.setText(Integer.toString(toany(dec,5)));
                                    break;

                                case 2:
                                    Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                    break;

                                case 3:
                                    dec = Integer.parseInt(ip1.getText().toString(),8);
                                    ip2.setText(hex(dec));
                                    break;
                            }
                            break;
                        }

                        case 3:
                            {
                                switch (Integer.parseInt(String.valueOf(number2.getSelectedItemId())))
                                {
                                    case 0:
                                        try{
                                            int dec = Integer.parseInt(ip1.getText().toString(),16);
                                            ip2.setText(Integer.toString(toany(dec,2)));
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            Toast.makeText(getContext(), "Invalid Hex Value", Toast.LENGTH_LONG).show();
                                        }
                                        break;

                                    case 1:
                                        try{
                                            int dec = Integer.parseInt(ip1.getText().toString(),16);
                                            ip2.setText(Integer.toString(toany(dec,5)));
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            Toast.makeText(getContext(), "Invalid Hex Value", Toast.LENGTH_LONG).show();
                                        }
                                        break;

                                    case 2:
                                        try{
                                            int dec = Integer.parseInt(ip1.getText().toString(),16);
                                            ip2.setText(Integer.toString(toany(dec,8)));
                                        }
                                        catch (NumberFormatException e)
                                        {
                                            Toast.makeText(getContext(), "Invalid Hex Value", Toast.LENGTH_LONG).show();
                                        }
                                        break;

                                    case 3:
                                        Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                        break;

                                }
                                break;

                        }
                    }
                }
            }
        });
        return v;
    }

    public int toany(int decimal, int tobase)
    {
        int result = 0,rem,power=1;
        while(decimal!=0)
        {
            rem = decimal % tobase;
            result = rem * power + result;
            decimal = decimal / tobase;
            power = power * 10;
        }
        return result;
    }

    public String hex(int decimal)
    {
        int rem;
        String hex="";
        char hexchars[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(decimal>0)
        {
            rem=decimal%16;
            hex=hexchars[rem]+hex;
            decimal=decimal/16;
        }
        return hex;
    }
}