package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.languageid.LanguageIdentifier;

/**
 * The type Identify text activity.
 */
public class IdentifyTanslateTextActivity extends AppCompatActivity {

    /**
     * Variable declaration
     */
    private TextView source_lang,source_text;
    private Button translate_btn;
    private TextView translated_text;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_text);

        /**
         * Assigning variables
         */
        source_lang = findViewById(R.id.sourceLang);
        source_text = findViewById(R.id.sourceText);
        translate_btn = findViewById(R.id.translate);
        translated_text = findViewById(R.id.translatedText);

        source_text.setText(getIntent().getStringExtra("TEXT"));

        translate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identifyLanguage();
            }
        });

    }

    /**
     *
     */
    private void identifyLanguage() {
        text = source_text.getText().toString();
        LanguageIdentifier language_identifier = LanguageIdentification.getClient();
                language_identifier.identifyLanguage(text).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                if(s.equals("und")){
                    Toast.makeText(getApplicationContext(), "Can't Identify Language", Toast.LENGTH_LONG).show();
                }
                else{
                    getLanguageCode(s);
                }
            }
        });
    }

    /**
     *
     * @param s A string containing language code of the Identified language.
     */
    private void getLanguageCode(String s) {
    }
}