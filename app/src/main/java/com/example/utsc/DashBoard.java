package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This class is the DashBoard of our app.
 */
public class DashBoard extends AppCompatActivity {


    /**
     * The Card ocr.
     */
    CardView card_ocr;
    /**
     * The Card text to speech.
     */
    CardView card_text_to_speech;
    /**
     * The Card save text.
     */
    CardView card_save_text;
    /**
     * The Card speech to text.
     */
    CardView card_speech_to_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        card_ocr = findViewById(R.id.cardOCR);
        card_text_to_speech = findViewById(R.id.cardTTS);
        card_speech_to_text = findViewById(R.id.cardSTT);
        card_save_text = findViewById(R.id.cardSaveText);


        card_ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBoard.this, "OCR clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), OCR.class));
            }
        });

        card_text_to_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBoard.this, "Text to Speech clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TextToSpeechExample.class));
            }
        });

        card_speech_to_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBoard.this, "Speech to Text clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SpeechToText.class));
            }
        });

        card_save_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashBoard.this, "Save Text clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SaveText.class));
            }
        });

    }
}