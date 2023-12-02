package com.example.uygulama_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class SetupActivity extends AppCompatActivity {

    Button alt_limit;
    Button ust_limit;

    Button alt_arti;
    Button alt_eksi;

    int ust_deger=20;
    int altdeger=5;
    TextView deger;
    TextView alt_deger;

    CheckBox ust_ses, alt_ses ,ust_titresim,alt_titresim;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        alt_limit = (Button) findViewById(R.id.alt_limit);
        ust_limit = (Button) findViewById(R.id.ust_limit);
        deger = (TextView) findViewById(R.id.deger);
        alt_arti = (Button) findViewById(R.id.alt_arti);
        alt_eksi = (Button) findViewById(R.id.alt_eksi);
        alt_deger = (TextView) findViewById(R.id.alt_deger);
        ust_ses = (CheckBox) findViewById(R.id.ust_ses);
        alt_ses = (CheckBox) findViewById(R.id.alt_ses);
        ust_titresim = (CheckBox) findViewById(R.id.ust_titresim);
        alt_titresim = (CheckBox) findViewById(R.id.alt_titresim);

        Context context= getApplicationContext();
        sharedPreferences=context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();

        deger.setText(String.valueOf(ust_deger));

        alt_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ust_deger++;
                deger.setText(String.valueOf(ust_deger));
            }
        });

        ust_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ust_deger--;
                deger.setText(String.valueOf(ust_deger));
            }
        });

        alt_eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altdeger--;
                alt_deger.setText(String.valueOf(altdeger));
            }
        });

        alt_arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                altdeger++;
                alt_deger.setText(String.valueOf(altdeger));
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        editor.putInt("ust_Limit",ust_deger);
        editor.putInt("alt_deger",altdeger);
        editor.commit();
    }
}