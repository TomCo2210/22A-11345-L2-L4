package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final int DELAY = 1000;
    private ImageView main_IMG_question;
    private ProgressBar main_PRG_time;
    private Button[] main_BTN_answers;
    private int clock = 10;
    private Handler handler = new Handler();
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            Log.d("timeTick", "Tick: " + clock);
            updateProgressBar();
            handler.postDelayed(this, DELAY);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        main_PRG_time.setMax(10);
        main_PRG_time.setProgress(10);

        main_IMG_question.setImageResource(R.drawable.dog);

        main_BTN_answers[0].setText("VISIBLE");
        main_BTN_answers[1].setText("INVISIBLE");
        main_BTN_answers[2].setText("GONE");

        main_BTN_answers[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_IMG_question.setVisibility(View.VISIBLE);
            }
        });
        main_BTN_answers[1].setOnClickListener(v -> {
            main_IMG_question.setVisibility(View.INVISIBLE);
        });
        main_BTN_answers[2].setOnClickListener(v -> {
            main_IMG_question.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    private void startTicker() {
        handler.postDelayed(r, DELAY);
    }

    private void updateProgressBar() {
        main_PRG_time.setProgress(--clock);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
    }

    private void stopTicker() {
        handler.removeCallbacks(r);
    }


    private void findViews() {
        main_IMG_question = findViewById(R.id.main_IMG_question);
        main_PRG_time = findViewById(R.id.main_PRG_time);
        main_BTN_answers = new Button[]{
                findViewById(R.id.main_BTN_answer1),
                findViewById(R.id.main_BTN_answer2),
                findViewById(R.id.main_BTN_answer3),
                findViewById(R.id.main_BTN_answer4)
        };
    }
}