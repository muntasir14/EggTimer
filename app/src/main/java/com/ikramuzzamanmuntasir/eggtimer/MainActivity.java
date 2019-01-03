package com.ikramuzzamanmuntasir.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    Button startStopButton;
    SeekBar setTimeSeekBar;
    boolean isStartState;
    int time;
    CountDownTimer timer;
    MediaPlayer mediaPlayer;

    public void startStopButtonPressed(View view) {



        if(isStartState){


             timer = new CountDownTimer((time * 1000) + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {


                    updateTimeTextView(time);
                    setTimeSeekBar.setProgress(time);
                    time--;
                    Log.i("Time: ", Integer.toString(time));
                }

                @Override
                public void onFinish() {

                    mediaPlayer.start();
                    setTimeSeekBar.setEnabled(true);
                    isStartState = true;
                    updateTimeTextView(time);
                    startStopButton.setText("Start");
                }

            }.start();
            setTimeSeekBar.setEnabled(false);
            updateTimeTextView(time);
            setTimeSeekBar.setProgress(time);
            isStartState = false;
            startStopButton.setText("Stop");
        }else{

            setTimeSeekBar.setEnabled(true);
            timer.cancel();
            isStartState = true;
            startStopButton.setText("Start");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isStartState = true;

        timeTextView = (TextView) findViewById(R.id.timeTextView);
        startStopButton = (Button) findViewById(R.id.startStopButton);
        setTimeSeekBar = (SeekBar) findViewById(R.id.setTimeSeekBar);

        mediaPlayer = MediaPlayer.create(this, R.raw.airhorn);

        setTimeSeekBar.setMax(600);
        setTimeSeekBar.setProgress(30);
        updateTimeTextView(30);
        time = setTimeSeekBar.getProgress();

        setTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimeTextView(progress);
                time = progress;
                Log.i("Time: ", Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void updateTimeTextView(int progress) {

        int seconds, minutes;
        seconds = progress % 60;
        minutes = progress / 60;
        timeTextView.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
    }
}
