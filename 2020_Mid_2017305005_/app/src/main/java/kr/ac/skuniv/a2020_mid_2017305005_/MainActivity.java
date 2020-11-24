package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclickquit(View view) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //대화 상자의 내용 부분 설정
        alertDialog.setMessage("프로그램을 종료할까요?");

        // 타이틀 부분
        alertDialog.setTitle("종료");

        //대화상자의 ok 버튼 부분 설정
        alertDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setContentView(R.layout.activity_main);
            }
        });
        alertDialog.show();
    }

    public void onclickTimer(View view) {
        Intent intent = new Intent(MainActivity.this, TimerActivity.class);
        startActivity(intent);
    }

    public void onclicktalk(View view) {
        Intent intent = new Intent(MainActivity.this, Chat1Activity.class);
        startActivity(intent);
    }

    public void onclickfavorite(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }
}