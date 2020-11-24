package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class TimerActivity extends AppCompatActivity {
    Chronometer chronometer;

    long stopTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
    }

    public void onclickstart(View view) {
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
        chronometer.start();

    }

    public void onclickstop(View view) {
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.stop();
    }


    public void onclickpause(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        stopTime = 0;
        chronometer.stop();
    }
}