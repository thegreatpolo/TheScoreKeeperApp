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

public class BasketballActivity extends AppCompatActivity {

    int points_p1 =0;  // variables keeping scores
    int points_p2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketball);
        blurBackground();
    }

    public void blurBackground() {    // This is to blur the background of the info TextView
        RelativeLayout blurredView = (RelativeLayout) findViewById(R.id.backgd);
        blurredView.getBackground().setAlpha(170);
    }

    public void hideActionBar(){  // Hides the action bar
        getSupportActionBar().hide();
    }

    /** Methods to display scores**/
    public void displayForP1(int score) {
        TextView scoreView = (TextView) findViewById(R.id.points_p1);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForP2(int score){
        TextView scoreView = (TextView) findViewById(R.id.points_p2);
        scoreView.setText(String.valueOf(score));
    }

    /** Methods to be called on clicks **/

    /** For single point shot**/
    public void point_p1(View view){
        points_p1=points_p1+1;
        displayForP1(points_p1);
    }
    public void point_p2(View view){
        points_p2=points_p2+1;
        displayForP2(points_p2);
    }

    /** For two points shot**/
    public void two_points_p1(View view){
        points_p1=points_p1+2;
        displayForP1(points_p1);
    }
    public void two_points_p2(View view){
        points_p2=points_p2+2;
        displayForP2(points_p2);
    }

    /** For three points shot**/
    public void three_points_p1(View view){
        points_p1=points_p1+3;
        displayForP1(points_p1);
    }
    public void three_points_p2(View view){
        points_p2=points_p2+3;
        displayForP2(points_p2);
    }


    /** Methods for home button **/
    public void LaunchHomeActivity(View view){
        Intent home=new Intent(this, MainActivity.class);
        startActivity(home);
    }

    /** Methods for new game button **/
    public void new_game(View view){
        points_p1 =0;
        points_p2=0;
        displayForP1(points_p1);
        displayForP2(points_p2);
    }

}
