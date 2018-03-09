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

public class TennisActivity extends AppCompatActivity {

    int scoreP1Tie=0;
    int scoreP2Tie=0;
    int points_p1 =0;
    int game_p1=0;
    int set_p1=0;
    int points_p2=0;
    int game_p2=0;
    int set_p2=0;
    String advantage="Adv.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideActionBar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tennis);
        blurBackground();
    }

    public void blurBackground() {    // This is to blur the background of the info TextView
        RelativeLayout blurredView = (RelativeLayout) findViewById(R.id.backgd);
        blurredView.getBackground().setAlpha(170);
    }
    public void hideActionBar(){  // Hides the action bar
        getSupportActionBar().hide();
    }


    /** Methods for new game and home buttons **/
    public void LaunchHomeActivity(View view){
        Intent home=new Intent(this, MainActivity.class);
        startActivity(home);


    }
    public void new_game(View view){
        points_p1 =0;
        game_p1=0;
        set_p1=0;
        points_p2=0;
        game_p2=0;
        set_p2=0;
        displayForP1(points_p1,game_p1,set_p1);
        displayForP2(points_p2,game_p2,set_p2);
    }



    /** Methods to display Adv. **/
    public void Adv_P1(String adv) {
        TextView scoreView = (TextView) findViewById(R.id.points_p1);
        scoreView.setText(String.valueOf(adv));
    }
    public void Adv_P2(String adv) {
        TextView scoreView = (TextView) findViewById(R.id.points_p2);
        scoreView.setText(String.valueOf(adv));
    }

    /** Methods to display scores  **/
    public void displayForP1(int score, int game, int set) {
        TextView scoreView = (TextView) findViewById(R.id.points_p1);
        scoreView.setText(String.valueOf(score));

        TextView gameView = (TextView) findViewById(R.id.game_p1);
        gameView.setText(String.valueOf(game));

        TextView setView = (TextView) findViewById(R.id.set_p1);
        setView.setText(String.valueOf(set));
    }
    public void displayForP2(int score, int game, int set){
        TextView scoreView = (TextView) findViewById(R.id.points_p2);
        scoreView.setText(String.valueOf(score));

        TextView gameView = (TextView) findViewById(R.id.game_p2);
        gameView.setText(String.valueOf(game));

        TextView setView = (TextView) findViewById(R.id.set_p2);
        setView.setText(String.valueOf(set));
    }


    /** Method to be called in player 1 scores  **/
    public void point_p1(View view){
        if (points_p1<30){points_p1=points_p1+15;}
        else if (points_p1>=30){points_p1=points_p1+10;}

        if (points_p1 > 40 && points_p1-points_p2>=20){game_p1=game_p1+1; points_p1=0; points_p2=0;}
        else if (points_p1>40 && points_p2>40){points_p1=40;points_p2=40;}

        if (game_p1 > 6 && game_p1-game_p2>=2){set_p1=set_p1+1; game_p1=0;}
        else if (game_p1 > 6 && game_p1-game_p2 <2 && game_p2 >=6){}
        displayForP1(points_p1, game_p1, set_p1);
        displayForP2(points_p2, game_p2, set_p2);

        if (points_p1 > 40 && points_p2 == 40){Adv_P1(advantage);}

        if (game_p1==6 && game_p2==6){TieBreak();}
    }
    /** Method to be called in player 2 scores  **/
    public void point_p2(View view){
        if (points_p2<30){points_p2=points_p2+15;}
        else if (points_p2>=30){points_p2=points_p2+10;}

        if (points_p2 > 40 && points_p2-points_p1>=20){game_p2=game_p2+1; points_p2=0; points_p1=0;}
        else if (points_p2>40 && points_p1>40){points_p2=40;points_p1=40;}

        if (game_p2 > 6 && game_p2-game_p1>=2){set_p2=set_p2+1; game_p2=0;}
        displayForP1(points_p1, game_p1, set_p1);
        displayForP2(points_p2, game_p2, set_p2);

        if (points_p2 > 40 && points_p1 == 40){Adv_P2(advantage);}

        if (game_p1==6 && game_p2==6){TieBreak();}

    }


    /** Method called in case of TieBreak**/
    public void TieBreak(){
        RelativeLayout mainLayout = (RelativeLayout)
                findViewById(R.id.main_layout);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.tiebreak, null);


        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        popupWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 0, 0);

        LinearLayout blurredView = (LinearLayout) popupView.findViewById(R.id.backgdpopup);
        blurredView.getBackground().setAlpha(170);

        // If player 1 scores
        Button btnP1 = (Button)popupView.findViewById(R.id.button_tie_p1);
        final TextView dp1=(TextView) popupView.findViewById(R.id.points_tiebreak_p1);
        btnP1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                scoreP1Tie=scoreP1Tie+1;
                dp1.setText(String.valueOf(scoreP1Tie));
                if (scoreP1Tie >= 7 && (scoreP1Tie - scoreP2Tie) > 2) {
                    popupWindow.dismiss();
                    set_p1 = set_p1 + 1;
                    scoreP1Tie=0;
                    scoreP2Tie=0;
                    points_p1=0;
                    points_p2=0;
                    game_p1=0;
                    game_p2=0;
                    displayForP1(points_p1,game_p1,set_p1);
                    displayForP2(points_p2,game_p2,set_p2);
                }
            }});



        // If player 2 scores
        Button btnP2 = (Button)popupView.findViewById(R.id.button_tie_p2);
        final TextView dp2=(TextView) popupView.findViewById(R.id.points_tiebreak_p2);
        btnP2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                scoreP2Tie=scoreP2Tie+1;
                dp2.setText(String.valueOf(scoreP2Tie));
                if (scoreP2Tie >= 7 && (scoreP2Tie - scoreP1Tie) > 2) {
                    popupWindow.dismiss();
                    set_p2 = set_p2 + 1;
                    scoreP1Tie=0;
                    scoreP2Tie=0;
                    points_p1=0;
                    points_p2=0;
                    game_p1=0;
                    game_p2=0;
                    displayForP2(points_p2,game_p2,set_p2);
                    displayForP1(points_p1,game_p1,set_p1);
                }
            }});
    }

}

