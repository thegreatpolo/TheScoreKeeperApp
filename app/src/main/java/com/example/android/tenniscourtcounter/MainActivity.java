package com.example.android.tenniscourtcounter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        blurBackground();
    }

    public void blurBackground() {    // This is to blur the background of the info TextView
        RelativeLayout blurredView = (RelativeLayout) findViewById(R.id.backgd);
        blurredView.getBackground().setAlpha(170);
    }

    public void hideActionBar(){  // Hides the action bar
        getSupportActionBar().hide();
    }

    public void LaunchTennisActivity(View view){
        Intent tennis=new Intent(this, TennisActivity.class);
        startActivity(tennis);
    }

    public void LaunchFootballActivity(View view){
        Intent football=new Intent(this, FootballActivity.class);
        startActivity(football);
    }

    public void LaunchBasketballActivity(View view){
        Intent football=new Intent(this, BasketballActivity.class);
        startActivity(football);
    }

}


