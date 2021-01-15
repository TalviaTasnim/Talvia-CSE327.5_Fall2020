package com.example.utsc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class contains method of converting speech to text.
 * @author Azizul
 */
public class SpeechToText extends AppCompatActivity {
    /**
     * Declaring variables.
     */
    protected static final int RESULT_SPEECH = 1;
    private ImageButton btn_speak;
    private TextView tv_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        tv_text = findViewById(R.id.tvTest);
        btn_speak = findViewById(R.id.btnSpeak);
        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
                try{
                    startActivityForResult(intent,RESULT_SPEECH);
                    tv_text.setText("");
                } catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(), "Your device doesn't support speech to text", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }

            }
        });


    }

    /**
     * This method
     * @param requestCode is an Integer
     * @param resultCode is an Integer
     * @param data is an Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if(resultCode== RESULT_OK && data != null){
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv_text.setText(text.get(0));
                }
                break;
        }
    }
}