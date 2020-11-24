package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VoteResultActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_result);

        textView1 = (TextView) findViewById(R.id.textView10);
        textView2 = (TextView) findViewById(R.id.textView11);
        textView3 = (TextView) findViewById(R.id.textView12);


        Intent intent = getIntent();

        //받을 때 int로 받는거 잊지말기
        String result = intent.getExtras().getString("cat");
        String result_bird = intent.getExtras().getString("brid");
        String result_dog = intent.getExtras().getString("dog");
        textView1.setText("고양이:" + result);
        textView2.setText("강아지:" + result_bird);
        textView3.setText("독수리:" + result_dog);

    }

    public void onclick(View view) {
        finish();
    }
}