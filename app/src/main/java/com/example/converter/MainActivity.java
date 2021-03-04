package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView MainNav;
    private FrameLayout MainFrame;

    private FragmentTemperature temperature;
    private FragmentDistance distance;
    private FragmentCurrency currency;
    private FragmentTime time;
    private FragmentNumber number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing frame and navigation bar
        MainFrame = (FrameLayout) findViewById(R.id.main_frame);
        MainNav = (BottomNavigationView) findViewById(R.id.main_nav);


        //initializing fragments
        temperature = new FragmentTemperature();
        distance = new FragmentDistance();
        currency = new FragmentCurrency();
        time = new FragmentTime();
        number = new FragmentNumber();

        //set default frame and title
        setFragment(temperature);
        setTitle(getResources().getString(R.string.temperature_title));

        MainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_temperature: {
                        setFragment(temperature);
                        setTitle(getResources().getString(R.string.temperature_title)); //set fragment title
                        return true;
                    }

                    case R.id.nav_distance: {
                        setFragment(distance);
                        setTitle(getResources().getString(R.string.distance_title)); //set fragment title
                        return true;
                    }
                    case R.id.nav_currency: {
                        setFragment(currency);
                        setTitle(getResources().getString(R.string.currency_title)); //set fragment title
                        return true;
                    }
                    case R.id.nav_time: {
                        setFragment(time);
                        setTitle(getResources().getString(R.string.time_title)); //set fragment title
                        return true;
                    }
                    case R.id.nav_number: {
                        setFragment(number);
                        setTitle(getResources().getString(R.string.number_title)); //set fragment title
                        return true;
                    }
                }
                return false;
            }

        });

    }

    //set fragment when the respective tab is clicked
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    // function for temperature conversion
    public void convert(View v) {

        EditText temp = (EditText) findViewById(R.id.temp);
        TextView conv_temp = (TextView) findViewById(R.id.conv_temp);

        if(temp.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "No input Given", Toast.LENGTH_LONG).show();
        }
        else{
        double t1;
        double t = Double.parseDouble(String.valueOf(temp.getText()));

        //initialize radio buttons
        RadioButton ctof = (RadioButton) findViewById(R.id.radio_ctof);
        RadioButton ftoc = (RadioButton) findViewById(R.id.radio_ftoc);
        RadioButton ftok = (RadioButton) findViewById(R.id.radio_ftok);
        RadioButton ktof = (RadioButton) findViewById(R.id.radio_ktof);
        RadioButton ctok = (RadioButton) findViewById(R.id.radio_ctok);
        RadioButton ktoc = (RadioButton) findViewById(R.id.radio_ktoc);

        //check for the selected radio button and set converted temperature
            if(ctof.isChecked() == false && ftoc.isChecked() == false && ftok.isChecked() == false && ktof.isChecked() == false && ctok.isChecked() == false && ktoc.isChecked() == false)
            {
                Toast.makeText(getApplicationContext(),"No Temperature Conversion Choosen", Toast.LENGTH_LONG).show();
            }

            if (ctof.isChecked()) {
                t1 = c2f(t);
                conv_temp.setText((String.format("%.2f",t1))+" deg F");
                ctof.setChecked(true);
            }

            if (ftoc.isChecked()) {
                t1 = f2c(t);
                conv_temp.setText((String.format("%.2f",t1))+" deg C");
                ftoc.setChecked(true);
            }

            if (ftok.isChecked()) {
                t1 = f2k(t);
                conv_temp.setText((String.format("%.2f",t1))+" K");
                ftok.setChecked(true);
            }

            if (ktof.isChecked()) {
                t1 = k2f(t);
                conv_temp.setText((String.format("%.2f",t1))+" deg F");
                ktof.setChecked(true);
            }

            if (ctok.isChecked()) {
                t1 = c2k(t);
                conv_temp.setText((String.format("%.2f",t1))+" K");
                ctok.setChecked(true);
            }

            if (ktoc.isChecked()) {
                t1 = k2c(t);
                conv_temp.setText((String.format("%.2f",t1))+" deg C");
                ktoc.setChecked(true);
            }
        }


    }

    // functions for conversions
    private double c2f(double c) {
        return (((c * 9) / 5) + 32);
    }

    private double f2c(double f) {
        return (((f - 32) * 5) / 9);
    }

    private double f2k(double f) {
        return (((f - 32) * 5) / 9) + 273.15;
    }

    private double k2f(double k) {
        return (((k - 273.15) * 5) / 9) + 32;
    }

    private double c2k(double c) {
        return (c + 273.15);
    }

    private double k2c(double k) {
        return (k - 273.15);
    }

    //Length Conversions function starts
    public void lengthcalc(View v){

        Spinner length1 = (Spinner) findViewById(R.id.length1);
        Spinner length2 = (Spinner) findViewById(R.id.length2);

        EditText ip_length1 = (EditText) findViewById(R.id.ip_length1);
        EditText ip_length2 = (EditText) findViewById(R.id.ip_length2);

        if(ip_length1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "No input Given", Toast.LENGTH_LONG).show();
            ip_length2.setText("");
        }
        else {
            String pos1 = String.valueOf(length1.getSelectedItemId());
            String pos2 = String.valueOf(length2.getSelectedItemId());


            double l = Double.parseDouble(String.valueOf(ip_length1.getText()));

            if (pos1.equals("0") && pos2.equals("0")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("0") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l / 10)));
            }
            if (pos1.equals("0") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l / 25.4)));
            }
            if (pos1.equals("0") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l / 305)));
            }
            if (pos1.equals("0") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l / 914)));
            }
            if (pos1.equals("0") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l / 1000)));
            }
            if (pos1.equals("0") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 1609344)));
            }
            if (pos1.equals("0") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 1000000)));
            }


            if (pos1.equals("1") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 10)));
            }
            if (pos1.equals("1") && pos2.equals("1")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("1") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l / 2.54)));
            }
            if (pos1.equals("1") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l / 30.5)));
            }
            if (pos1.equals("1") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l / 91.4)));
            }
            if (pos1.equals("1") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l / 100)));
            }
            if (pos1.equals("1") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 160934.4)));
            }
            if (pos1.equals("1") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 100000)));
            }


            if (pos1.equals("2") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 25.4)));
            }
            if (pos1.equals("2") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 2.54)));
            }
            if (pos1.equals("2") && pos2.equals("2")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("2") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l / 12)));
            }
            if (pos1.equals("2") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l / 36)));
            }
            if (pos1.equals("2") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l / 39.37)));
            }
            if (pos1.equals("2") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 63360)));
            }
            if (pos1.equals("2") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 39370)));
            }


            if (pos1.equals("3") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 305)));
            }
            if (pos1.equals("3") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 30.5)));
            }
            if (pos1.equals("3") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l * 12)));
            }
            if (pos1.equals("3") && pos2.equals("3")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("3") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l / 3)));
            }
            if (pos1.equals("3") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l / 3.3)));
            }
            if (pos1.equals("3") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 5280)));
            }
            if (pos1.equals("3") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 3281)));
            }


            if (pos1.equals("4") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 914)));
            }
            if (pos1.equals("4") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 91.44)));
            }
            if (pos1.equals("4") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l * 36)));
            }
            if (pos1.equals("4") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l * 3)));
            }
            if (pos1.equals("4") && pos2.equals("4")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("4") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l / 1.094)));
            }
            if (pos1.equals("4") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 1760)));
            }
            if (pos1.equals("4") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 1094)));
            }


            if (pos1.equals("5") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 1000)));
            }
            if (pos1.equals("5") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 100)));
            }
            if (pos1.equals("5") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l * 39.37)));
            }
            if (pos1.equals("5") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l * 3.281)));
            }
            if (pos1.equals("5") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l * 1.094)));
            }
            if (pos1.equals("5") && pos2.equals("5")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("5") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 1609)));
            }
            if (pos1.equals("5") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 1000)));
            }


            if (pos1.equals("6") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 1609344)));
            }
            if (pos1.equals("6") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 160934.4)));
            }
            if (pos1.equals("6") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l * 63360)));
            }
            if (pos1.equals("6") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l * 5280)));
            }
            if (pos1.equals("6") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l * 1760)));
            }
            if (pos1.equals("6") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l * 1609)));
            }
            if (pos1.equals("6") && pos2.equals("6")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("6") && pos2.equals("7")) {
                ip_length2.setText((String.format("%.6f", l / 1.609)));
            }


            if (pos1.equals("7") && pos2.equals("0")) {
                ip_length2.setText((String.format("%.6f", l * 1000000)));
            }
            if (pos1.equals("7") && pos2.equals("1")) {
                ip_length2.setText((String.format("%.6f", l * 100000)));
            }
            if (pos1.equals("7") && pos2.equals("2")) {
                ip_length2.setText((String.format("%.6f", l * 39370)));
            }
            if (pos1.equals("7") && pos2.equals("3")) {
                ip_length2.setText((String.format("%.6f", l * 3281)));
            }
            if (pos1.equals("7") && pos2.equals("4")) {
                ip_length2.setText((String.format("%.6f", l * 1094)));
            }
            if (pos1.equals("7") && pos2.equals("5")) {
                ip_length2.setText((String.format("%.6f", l * 1000)));
            }
            if (pos1.equals("7") && pos2.equals("6")) {
                ip_length2.setText((String.format("%.6f", l / 1.609)));
            }
            if (pos1.equals("7") && pos2.equals("7")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    } // length conversion function ends

    //function for time conversion starts
    public void timecalc(View v){

        Spinner time1 = (Spinner) findViewById(R.id.time1);
        Spinner time2 = (Spinner) findViewById(R.id.time2);

        EditText ip_time1 = (EditText) findViewById(R.id.ip_time1);
        EditText ip_time2 = (EditText) findViewById(R.id.ip_time2);

        Button b = (Button) findViewById(R.id.time_convert);

        if(ip_time1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "No input Given", Toast.LENGTH_LONG).show();
            ip_time2.setText("");
        }
        else {
            String pos1 = String.valueOf(time1.getSelectedItemId());
            String pos2 = String.valueOf(time2.getSelectedItemId());


            double t = Integer.parseInt(String.valueOf(ip_time1.getText()));

            if (pos1.equals("0") && pos2.equals("0")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("0") && pos2.equals("1")) {
            //  b.setText(((ip_time1.getText().toString())));
                ip_time2.setText((String.format("%.6f", t / 60)));
            }
            if (pos1.equals("0") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t / 3600)));
            }
            if (pos1.equals("0") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t / (3600 * 24))));
            }
            if (pos1.equals("0") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t / (3600 * 24 * 7))));
            }
            if (pos1.equals("0") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t / 2629746)));
            }
            if (pos1.equals("0") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 31556952)));
            }


            if (pos1.equals("1") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 60)));
            }
            if (pos1.equals("1") && pos2.equals("1")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("1") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t / 60)));
            }
            if (pos1.equals("1") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t / 1440)));
            }
            if (pos1.equals("1") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t / 10080)));
            }
            if (pos1.equals("1") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t / 43800)));
            }
            if (pos1.equals("1") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 525600)));
            }


            if (pos1.equals("2") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 3600)));
            }
            if (pos1.equals("2") && pos2.equals("1")) {
                ip_time2.setText((String.format("%.6f", t * 60)));
            }
            if (pos1.equals("2") && pos2.equals("2")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("2") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t / 24)));
            }
            if (pos1.equals("2") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t / 168)));
            }
            if (pos1.equals("2") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t / 730)));
            }
            if (pos1.equals("2") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 8760)));
            }


            if (pos1.equals("3") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 86400)));
            }
            if (pos1.equals("3") && pos2.equals("1")) {
                ip_time2.setText((String.format("%.6f", t * 1440)));
            }
            if (pos1.equals("3") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t * 24)));
            }
            if (pos1.equals("3") && pos2.equals("3")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("3") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t / 7)));
            }
            if (pos1.equals("3") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t / 30.417)));
            }
            if (pos1.equals("3") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 365)));
            }


            if (pos1.equals("4") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 604800)));
            }
            if (pos1.equals("4") && pos2.equals("1")) {
                ip_time2.setText((String.format("%.6f", t * 10080)));
            }
            if (pos1.equals("4") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t * 168)));
            }
            if (pos1.equals("4") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t * 7)));
            }
            if (pos1.equals("4") && pos2.equals("4")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("4") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t / 4.345)));
            }
            if (pos1.equals("4") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 52.143)));
            }


            if (pos1.equals("5") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 2629746)));
            }
            if (pos1.equals("5") && pos2.equals("1")) {
                ip_time2.setText((String.format("%.6f", t * 43800)));
            }
            if (pos1.equals("5") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t * 730)));
            }
            if (pos1.equals("5") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t * 30.417)));
            }
            if (pos1.equals("5") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t * 4.345)));
            }
            if (pos1.equals("5") && pos2.equals("5")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("5") && pos2.equals("6")) {
                ip_time2.setText((String.format("%.6f", t / 12)));
            }


            if (pos1.equals("6") && pos2.equals("0")) {
                ip_time2.setText((String.format("%.6f", t * 31556952)));
            }
            if (pos1.equals("6") && pos2.equals("1")) {
                ip_time2.setText((String.format("%.6f", t * 525600)));
            }
            if (pos1.equals("6") && pos2.equals("2")) {
                ip_time2.setText((String.format("%.6f", t * 8760)));
            }
            if (pos1.equals("6") && pos2.equals("3")) {
                ip_time2.setText((String.format("%.6f", t * 365)));
            }
            if (pos1.equals("6") && pos2.equals("4")) {
                ip_time2.setText((String.format("%.6f", t * 52.143)));
            }
            if (pos1.equals("6") && pos2.equals("5")) {
                ip_time2.setText((String.format("%.6f", t * 12)));
            }
            if (pos1.equals("6") && pos2.equals("6")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    } // function for time conversion ends


    //function for currency conversion starts
    public void currencycalc(View v){

        Spinner currency1 = (Spinner) findViewById(R.id.currency1);
        Spinner currency2 = (Spinner) findViewById(R.id.currency2);

        EditText ip_currency1 = (EditText) findViewById(R.id.ip_currency1);
        EditText ip_currency2 = (EditText) findViewById(R.id.ip_currency2);

        if(ip_currency1.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "No input Given", Toast.LENGTH_LONG).show();
            ip_currency2.setText("");
        }

        else {
            String pos1 = String.valueOf(currency1.getSelectedItemId());
            String pos2 = String.valueOf(currency2.getSelectedItemId());


            double c = Integer.parseInt(String.valueOf(ip_currency1.getText()));

            if (pos1.equals("0") && pos2.equals("0")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("0") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 89.45)));
            }
            if (pos1.equals("0") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 73.34)));
            }
            if (pos1.equals("0") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * (1.42))));
            }
            if (pos1.equals("0") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 99.19)));
            }
            if (pos1.equals("0") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 56.61)));
            }
            if (pos1.equals("0") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c / 3.65)));
            }
            if (pos1.equals("0") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 241.71)));
            }
            if (pos1.equals("0") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c / 55.18)));
            }
            if (pos1.equals("0") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c / 18.16)));
            }
            if (pos1.equals("0") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c / 19.99)));
            }


            if (pos1.equals("1") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 89.45)));
            }
            if (pos1.equals("1") && pos2.equals("1")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("1") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c * 1.22)));
            }
            if (pos1.equals("1") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 126.86)));
            }
            if (pos1.equals("1") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 1.11)));
            }
            if (pos1.equals("1") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c * 1.58)));
            }
            if (pos1.equals("1") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 24.48)));
            }
            if (pos1.equals("1") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 2.71)));
            }
            if (pos1.equals("1") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c * 1.62)));
            }
            if (pos1.equals("1") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 4.93)));
            }
            if (pos1.equals("1") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 4.48)));
            }


            if (pos1.equals("2") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 73.34)));
            }
            if (pos1.equals("2") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 1.22)));
            }
            if (pos1.equals("2") && pos2.equals("2")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("2") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 104.09)));
            }
            if (pos1.equals("2") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 1.35)));
            }
            if (pos1.equals("2") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c * 1.29)));
            }
            if (pos1.equals("2") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 20.09)));
            }
            if (pos1.equals("2") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 3.29)));
            }
            if (pos1.equals("2") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c * 1.33)));
            }
            if (pos1.equals("2") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 4.04)));
            }
            if (pos1.equals("2") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 3.67)));
            }


            if (pos1.equals("3") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c / 1.42)));
            }
            if (pos1.equals("3") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 126.86)));
            }
            if (pos1.equals("3") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 104.09)));
            }
            if (pos1.equals("3") && pos2.equals("3")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("3") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 140.77)));
            }
            if (pos1.equals("3") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 80.35)));
            }
            if (pos1.equals("3") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c / 5.17)));
            }
            if (pos1.equals("3") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 343.20)));
            }
            if (pos1.equals("3") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c / 78.34)));
            }
            if (pos1.equals("3") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c / 25.74)));
            }
            if (pos1.equals("3") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c / 28.36)));
            }


            if (pos1.equals("4") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 99.19)));
            }
            if (pos1.equals("4") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c * 1.11)));
            }
            if (pos1.equals("4") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c * 1.35)));
            }
            if (pos1.equals("4") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 140.77)));
            }
            if (pos1.equals("4") && pos2.equals("4")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("4") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c * 1.75)));
            }
            if (pos1.equals("4") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 27.16)));
            }
            if (pos1.equals("4") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 2.44)));
            }
            if (pos1.equals("4") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c * 1.8)));
            }
            if (pos1.equals("4") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 5.46)));
            }
            if (pos1.equals("4") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 4.96)));
            }


            if (pos1.equals("5") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 56.61)));
            }
            if (pos1.equals("5") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 1.58)));
            }
            if (pos1.equals("5") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 1.29)));
            }
            if (pos1.equals("5") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 80.35)));
            }
            if (pos1.equals("5") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 1.75)));
            }
            if (pos1.equals("5") && pos2.equals("5")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("5") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 15.50)));
            }
            if (pos1.equals("5") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 4.27)));
            }
            if (pos1.equals("5") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c * 1.03)));
            }
            if (pos1.equals("5") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 3.12)));
            }
            if (pos1.equals("5") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 2.83)));
            }


            if (pos1.equals("6") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 3.65)));
            }
            if (pos1.equals("6") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 24.48)));
            }
            if (pos1.equals("6") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 20.09)));
            }
            if (pos1.equals("6") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 5.18)));
            }
            if (pos1.equals("6") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 1.75)));
            }
            if (pos1.equals("6") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 15.50)));
            }
            if (pos1.equals("6") && pos2.equals("6")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("6") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 66.31)));
            }
            if (pos1.equals("6") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c / 15.13)));
            }
            if (pos1.equals("6") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c / 4.98)));
            }
            if (pos1.equals("6") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c / 5.49)));
            }


            if (pos1.equals("7") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 241.71)));
            }
            if (pos1.equals("7") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c * 2.71)));
            }
            if (pos1.equals("7") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c * 3.29)));
            }
            if (pos1.equals("7") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 343.20)));
            }
            if (pos1.equals("7") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c * 2.44)));
            }
            if (pos1.equals("7") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c * 4.27)));
            }
            if (pos1.equals("7") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 66.31)));
            }
            if (pos1.equals("7") && pos2.equals("7")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("7") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c * 4.38)));
            }
            if (pos1.equals("7") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c / 4.99)));
            }
            if (pos1.equals("7") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 12.10)));
            }


            if (pos1.equals("8") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 55.18)));
            }
            if (pos1.equals("8") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 1.62)));
            }
            if (pos1.equals("8") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 1.33)));
            }
            if (pos1.equals("8") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 78.34)));
            }
            if (pos1.equals("8") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 1.80)));
            }
            if (pos1.equals("8") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 1.03)));
            }
            if (pos1.equals("8") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 15.13)));
            }
            if (pos1.equals("8") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 4.38)));
            }
            if (pos1.equals("8") && pos2.equals("8")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("8") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 3.04)));
            }
            if (pos1.equals("8") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c * 2.76)));
            }

            if (pos1.equals("9") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 18.16)));
            }
            if (pos1.equals("9") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 4.94)));
            }
            if (pos1.equals("9") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 4.04)));
            }
            if (pos1.equals("9") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 25.7)));
            }
            if (pos1.equals("9") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 5.46)));
            }
            if (pos1.equals("9") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 3.13)));
            }
            if (pos1.equals("9") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 4.99)));
            }
            if (pos1.equals("9") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 13.31)));
            }
            if (pos1.equals("9") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c / 3.04)));
            }
            if (pos1.equals("9") && pos2.equals("9")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            if (pos1.equals("9") && pos2.equals("10")) {
                ip_currency2.setText((String.format("%.6f", c / 1.10)));
            }


            if (pos1.equals("10") && pos2.equals("0")) {
                ip_currency2.setText((String.format("%.6f", c * 19.99)));
            }
            if (pos1.equals("10") && pos2.equals("1")) {
                ip_currency2.setText((String.format("%.6f", c / 4.48)));
            }
            if (pos1.equals("10") && pos2.equals("2")) {
                ip_currency2.setText((String.format("%.6f", c / 3.67)));
            }
            if (pos1.equals("10") && pos2.equals("3")) {
                ip_currency2.setText((String.format("%.6f", c * 28.36)));
            }
            if (pos1.equals("10") && pos2.equals("4")) {
                ip_currency2.setText((String.format("%.6f", c / 4.96)));
            }
            if (pos1.equals("10") && pos2.equals("5")) {
                ip_currency2.setText((String.format("%.6f", c / 2.83)));
            }
            if (pos1.equals("10") && pos2.equals("6")) {
                ip_currency2.setText((String.format("%.6f", c * 5.49)));
            }
            if (pos1.equals("10") && pos2.equals("7")) {
                ip_currency2.setText((String.format("%.6f", c / 12.10)));
            }
            if (pos1.equals("10") && pos2.equals("8")) {
                ip_currency2.setText((String.format("%.6f", c / 2.76)));
            }
            if (pos1.equals("10") && pos2.equals("9")) {
                ip_currency2.setText((String.format("%.6f", c * 1.10)));
            }
            if (pos1.equals("10") && pos2.equals("10")) {
                Snackbar.make(v, "Same units cannot be converted", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        }
    } // function for currency conversion ends

}



