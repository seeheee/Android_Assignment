package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Chat1Activity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    static final int GET_STRING = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat1);
    }

    public void onclicksend(View view) {
        editText = (EditText) findViewById(R.id.editTextTextPersonName);
        Intent intent = new Intent(Chat1Activity.this, Chat2Activity.class);
        intent.putExtra("data",editText.getText().toString());
        startActivityForResult(intent,GET_STRING);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_STRING) {
            if (resultCode == RESULT_OK) {
                textView = (TextView) findViewById(R.id.textView3);
                textView.setText("Received:"+ data.getStringExtra("send"));
            }
        }
    }
}