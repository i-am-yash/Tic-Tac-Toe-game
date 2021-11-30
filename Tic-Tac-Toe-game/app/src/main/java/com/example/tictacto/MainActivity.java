package com.example.tictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    boolean win=false;
    int count=0;  // it is used to count no of tap so after 9 tap or after win,game gets reset.
    // 0- X
    // 1- O
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    //state means
//    0-X
//    1-O
//    2-Null
    int[][]winPositions={
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        count++;
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive &&(win ||count==10) ){
            reset(view);
        }

        if(gameState[tappedImage]==2 ){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status=(TextView) findViewById(R.id.status);
                status.setText("O's Turn Tap to Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status=(TextView) findViewById(R.id.status);
                status.setText("X's Turn Tap to Play");
            }
            img.animate().translationYBy(1000f);
        }
        // if a player won
        for(int[] winPos:winPositions){
            if(gameState[winPos[0]]==gameState[winPos[1]] && gameState[winPos[1]]==gameState[winPos[2]] && gameState[winPos[0]]!=2){
                if(gameState[winPos[0]]==1){
                    TextView status=(TextView) findViewById(R.id.status);
                    status.setText("O's has won!");

                }
                else{
                    TextView status=(TextView) findViewById(R.id.status);
                    status.setText("X's has won!");
                }
                gameActive=false;
                win=true;
                //count=0;

                break;

            }

            }
        if(count==9 && !win){
            TextView status=(TextView) findViewById(R.id.status);
            status.setText("Draw! Tap to play");
            gameActive=false;
        }


        }



    private void reset(View view) {

        gameActive=true;
        count=1;
        win=false;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        

    }


}