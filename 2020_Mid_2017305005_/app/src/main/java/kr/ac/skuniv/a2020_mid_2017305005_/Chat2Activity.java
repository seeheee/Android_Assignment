package kr.ac.skuniv.a2020_mid_2017305005_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Chat2Activity extends AppCompatActivity {
    static final int GET_STRING = 1;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat2);

        textView = (TextView) findViewById(R.id.textView5);
        Intent intent = getIntent();
        String result = intent.getExtras().getString("data");
        textView.setText("Received:" + result);
    }

    public void onclickchat2send(View view) {
        editText =(EditText) findViewById(R.id.editTextTextPersonName2);
        Intent intent = new Intent();
        intent.putExtra("send",editText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
        //startActivityForResult(intent, GET_STRING);
    }
}