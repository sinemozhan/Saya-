package com.example.uygulama_sayac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.uygulama_sayac.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button arti, eksi;
    Button ayarlar;
    TextView sonuc;


    int ust_limit = 20;
    int altdeger=5;
    int sayac = altdeger;
    Vibrator vibrator;
    MediaPlayer mediaPlayer;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent splash = new Intent(this, SplashScreen.class);
        startActivity(splash);

        arti = (Button) findViewById(R.id.arti);
        eksi = (Button) findViewById(R.id.eksi);
        sonuc = (TextView) findViewById(R.id.sonuc);
        ayarlar = (Button) findViewById(R.id.ayarlar);

        Context context = getApplicationContext();
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(this, R.raw.note);

        arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sayac==ust_limit){
                    alertvib();
                    alertSound();

                }
                if (sayac<ust_limit) {
                    sayac++;
                }
                sonuc.setText(String.valueOf(sayac));
            }
        });

        eksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sayac>altdeger)
                sayac--;
                sonuc.setText(String.valueOf(sayac));
            }
        });

        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ust_limit = sharedPreferences.getInt("ust_Limit", 20);
        altdeger = sharedPreferences.getInt("alt_deger", 5);
    }


    public void alertvib() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {

            vibrator.vibrate(1000);
        }
    }

    public void alertSound() {
        mediaPlayer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            if(sayac>altdeger)
                sayac=sayac-5;
            sonuc.setText(String.valueOf(sayac));
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            if(sayac<ust_limit)
                sayac=sayac+5;
            sonuc.setText(String.valueOf(sayac));
        }
        return true;
    }
}
