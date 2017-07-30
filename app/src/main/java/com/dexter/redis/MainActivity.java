package com.dexter.redis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    EditText key_et, value_et, time_et;
    Button btn_save, btn_clearall, btn_clear, btn_savetime;
    Redis redis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key_et = (EditText)findViewById(R.id.key);
        value_et = (EditText)findViewById(R.id.value);
        time_et = (EditText)findViewById(R.id.time);

        btn_clear = (Button)findViewById(R.id.clear);
        btn_clearall = (Button)findViewById(R.id.clearall);
        btn_save = (Button)findViewById(R.id.save);
        btn_savetime = (Button)findViewById(R.id.savetime);
        redis = Redis.getInstance(this);
        btn_save.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_clearall.setOnClickListener(this);
        btn_savetime.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String key;
        String value;
        switch (view.getId()){
            case R.id.save:
                key = key_et.getText().toString();
                value = value_et.getText().toString();
                redis.set(key, value);
                key_et.setText("");
                value_et.setText("");
                break;
            case R.id.savetime:
                key = key_et.getText().toString();
                value = value_et.getText().toString();
                int time = Integer.parseInt(time_et.getText().toString());
                redis.set(key, value, time);
                key_et.setText("");
                value_et.setText("");
                time_et.setText("");
                break;
            case R.id.clear:
                key = key_et.getText().toString();
                redis.clear(key);
                break;
            case R.id.clearall:
                redis.clear();
                break;
        }
    }
}
