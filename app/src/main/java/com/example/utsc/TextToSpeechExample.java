package com.example.utsc;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * This class contains methods for text extraction and text display
 * @author Talvia
 * @version 1.0
 */

public class TextToSpeechExample extends AppCompatActivity {
    private TextToSpeech m_tts;
    private EditText m_edit_text;
    private SeekBar m_seekbar_pitch;
    private SeekBar m_seekbar_speed;
    private Button m_button_speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_button_speak = findViewById(R.id.button_speak);
        m_tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = m_tts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        m_button_speak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        /**
         * Assigning variables
         */
        m_edit_text = findViewById(R.id.edit_text);
        m_seekbar_pitch = findViewById(R.id.seek_bar_pitch);
        m_seekbar_speed = findViewById(R.id.seek_bar_speed);
        m_button_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    /**
     * this method envokes speak button
     */
    private void speak() {
        String text = m_edit_text.getText().toString();
        float pitch = (float) m_seekbar_pitch.getProgress() / 50;
        /**
         * 50 represents normal pitch
         * */

        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) m_seekbar_speed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        m_tts.setPitch(pitch);
        m_tts.setSpeechRate(speed);
        m_tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    /**
     * when the written text is done speaking one time
     */

    @Override
    protected void onDestroy() {
        if (m_tts != null) {
            m_tts.stop();
            m_tts.shutdown();
        }
        super.onDestroy();
    }
}