package com.example.utsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.languageid.LanguageIdentification;
import com.google.mlkit.nl.languageid.LanguageIdentifier;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

/**
 * The class contains method of language identification and translation
 * @author Safayat
 * @author Rubaida
 */
public class IdentifyTranslateTextActivity extends AppCompatActivity {

    /**
     * Variable declaration
     */
    public TextView source_lang,source_text;
    private Button translate_btn;
    private TextView translated_text;
    private  String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_text);

        /*
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
     * This method identifies the language in a text
     * by getting the language code of that text
     */
    public void identifyLanguage() {
        text = source_text.getText().toString();
        LanguageIdentifier language_identifier = LanguageIdentification.getClient();
        source_lang.setText("Detecting...");

                language_identifier.identifyLanguage(text).addOnSuccessListener(new OnSuccessListener<String>() {

            @Override
            public void onSuccess(String s) {
                if(s.equals("und")){
                    Toast.makeText(getApplicationContext(), "Can't Identify Language", Toast.LENGTH_LONG).show();
                }
                else{

                       source_lang.setText(s);
                       translateText(text);


                }
            }
        }).addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(IdentifyTranslateTextActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Error " , e.getMessage());
                    }
                });

    }

    /**
     * This method translates the given text
     * @param s A string containing a string of text
     */
    public void translateText(String s) {
        TranslatorOptions options = new TranslatorOptions.Builder()

                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.BENGALI)
                        .build();


        final Translator english_bengali_translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder().requireWifi().build();
        english_bengali_translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Model download successful", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(IdentifyTranslateTextActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Error " , e.getMessage());
                    }
                });

        english_bengali_translator.translate(s).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                translated_text.setText(s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(IdentifyTranslateTextActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error " , e.getMessage());
            }
        });

    }
}
