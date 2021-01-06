package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This class is the Dash Board.
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
     * The Card speech to text.
     */
    CardView card_speech_to_text;
    /**
     * The Card quit.
     */
    CardView card_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        card_ocr = findViewById(R.id.cardOCR);
        card_text_to_speech = findViewById(R.id.cardTTS);
        card_speech_to_text = findViewById(R.id.cardSTT);
        card_quit = findViewById(R.id.closeApp);

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

        /*
          Closing the app
        */
        card_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}