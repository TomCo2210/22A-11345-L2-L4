package com.example.triviaapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.triviaapp.Models.DataManager;
import com.example.triviaapp.Models.Question;
import com.example.triviaapp.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView main_IMG_question;
    private ProgressBar main_PRG_time;
    private Button[] main_BTN_answers;

    private static final int DELAY = 1000;
    private int clock = 10;
    private Timer timer;

    private ArrayList<Question> questions;
    private int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questions = DataManager.generateQuestion();

        findViews();
        initViews();

        main_PRG_time.setMax(10);
        main_PRG_time.setProgress(10);
    }

    private void initViews() {
        ImageView main_IMG_background = findViewById(R.id.main_IMG_background);
        Glide
                .with(this)
                .load(R.drawable.img_background)
                .centerCrop()
                .into(main_IMG_background);
        for (int i = 0; i < main_BTN_answers.length ; i++) {
            int finalI = i;
            main_BTN_answers[i].setOnClickListener(v -> {
                clicked(finalI);
            });
        }
    }

    private void updateUI() {
        startTicker();
        Question q = questions.get(currentQuestion);

        Glide
                .with(this)
                .load(q.getImage())
                .into(main_IMG_question);

        for (int i = 0; i < q.getAnswers().size(); i++) {
            main_BTN_answers[i].setText(q.getAnswers().get(i));
        }
    }

    private void clicked(int buttonIndex) {
        vibrate();
        if (buttonIndex == 0){
            toast("Correct");
        }
        stopTicker();
       // nextQuestion();
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }

    private void startTicker() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("timeTick", "Tick: " + clock + " On Thread: " + Thread.currentThread().getName());
                runOnUiThread(() -> {
                    Log.d("timeTick", "Tick: " + clock + " On Thread: " + Thread.currentThread().getName());
                    updateProgressBar();
                });
            }
        }, 0, DELAY);
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
       timer.cancel();
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