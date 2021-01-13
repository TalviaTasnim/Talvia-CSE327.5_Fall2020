package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class SaveText extends AppCompatActivity {

     TextView tv ;
     String d;

    /**
     * feature 5 - saving data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_text);

        tv = findViewById(R.id.textView7);
        d = getIntent().getExtras().getString("Value");
        tv.setText(d);

    }
}