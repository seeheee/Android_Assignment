package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    String result1, result2, result3;
    int cat_count,brid_count,dog_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

    }


    public void radiobtnclick(View view) {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        //체크여부 확인할 거 필요
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioButton:
                if(checked)
                    cat_count++;
                    result1 = String.valueOf(cat_count);
                    Toast.makeText(getApplicationContext(), ((RadioButton) view).getText() + result1 + "표", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButton2:
                if(checked)
                    brid_count++;
                    result2 = String.valueOf(brid_count);
                    Toast.makeText(getApplicationContext(), ((RadioButton) view).getText() + result2+ "표",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButton3:
                if(checked)
                    dog_count++;
                    result3 = String.valueOf(dog_count);
                    Toast.makeText(getApplicationContext(), ((RadioButton) view).getText() + result3+ "표",Toast.LENGTH_SHORT).show();
                    break;
        }
    }

    public void onclicvkres(View view) {
        Intent intent = new Intent(VoteActivity.this,VoteResultActivity.class);
        intent.putExtra("cat",result1 );
        intent.putExtra("brid", result2);
        intent.putExtra("dog",result3);
        startActivity(intent);
    }
}