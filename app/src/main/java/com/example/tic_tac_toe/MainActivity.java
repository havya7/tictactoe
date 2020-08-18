package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA = "WINS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    // x - 0
    // o - 1
    //empty - 2

    int player = 0;
    boolean game = true;
    int[] state = {2,2,2,2,2,2,2,2,2};
    int[][] win = {{0,1,2}, {3,4,5}, {6,7,8},
                    {0,3,6}, {1,4,7}, {2,5,8},
                    {0,4,8}, {2,4,6} };

    public void tap(View view) {
        // Do something in response to button
        ImageView img = (ImageView) view;
        int pos = Integer.parseInt(img.getTag().toString());

        if(game != true)
            reset(view);

        if(state[pos] == 2) {
            state[pos] = player;
            img.setTranslationY(-1000f);

            if (player == 1) {
                player = 0;
                img.setImageResource(R.drawable.o1);
                TextView txt = findViewById(R.id.turn);
                txt.setText("X's turn - Tap to play" );
            }
            else {
                player = 1;
                img.setImageResource(R.drawable.x2);
                TextView txt = findViewById(R.id.turn);
                txt.setText("O's turn - Tap to play" );
            }
            img.animate().translationYBy(1000f).setDuration(300);

            for(int[] wp:win)
            {
                if(state[wp[0]] == state[wp[1]] && state[wp[2]] == state[wp[1]] && state[wp[0]] != 2) {
                    if(state[wp[0]] == 0) {
                        TextView txt = findViewById(R.id.turn);
                        txt.setText("X wins" );
                    }
                    else{
                        TextView txt = findViewById(R.id.turn);
                        txt.setText("O wins" );
                    }

                    Intent intent = new Intent(MainActivity.this,pop.class);

                    TextView Text = findViewById(R.id.turn);
                    String msg = Text.getText().toString();
                    intent.putExtra(EXTRA,msg);

                    startActivity(intent);

                    game = false;
                }
            }

            if(game)
            {
                boolean flag = true;
                for(int i=0;i<9;i++) {
                    if(state[i] == 2)
                        flag = false;
                }
                if(flag)
                {
                    TextView txt = findViewById(R.id.turn);
                    txt.setText("Neither wins" );

                    Intent intent = new Intent(MainActivity.this,pop.class);

                    TextView Text = findViewById(R.id.turn);
                    String msg = Text.getText().toString();
                    intent.putExtra(EXTRA,msg);

                    startActivity(intent);

                    game = false;
                }
            }
        }
    }

    public void reset(View view){
        player = 0;
        game = true;
        for(int i=0;i<9;i++) {
            state[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }
}