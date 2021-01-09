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
 * The class contains method of language identification 
 * @author Safayat
 */
public class IdentifyTranslateTextActivity extends AppCompatActivity {

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
     * This method identifies the language in a text
     * by getting the language code of that language by calling getLanguageCode().
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
     * This method gets the language code of a language.
     * @param language A string containing language code of the Identified language.
     */
    private void getLanguageCode(String language) {
        int langCode;
        switch(language){
            case "bn":
                langCode = FirebaseTranslateLanguage.BN;
                mSourceLang.setText("Bangla");
                break;

            case "fr":
                langCode = FirebaseTranslateLanguage.FR;
                mSourceLang.setText("French");
                break;

            case "ja":
                langCode = FirebaseTranslateLanguage.JA;
                mSourceLang.setText("Japanese");
                break;

            case "it":
                langCode = FirebaseTranslateLanguage.IT;
                mSourceLang.setText("Italian");
                break;

            case "hi":
                langCode = FirebaseTranslateLanguage.HI;
                mSourceLang.setText("Hindi");
                break;

            case "ur":
                langCode = FirebaseTranslateLanguage.UR;
                mSourceLang.setText("Urdu");
                break;

            case "ar":
                langCode = FirebaseTranslateLanguage.AR;
                mSourceLang.setText("Arabic");
                break;

            case "ru":
                langCode = FirebaseTranslateLanguage.RU;
                mSourceLang.setText("Russian");
                break;

            default:
                langCode = 0;
        }

        translateText(langCode);
    }


    /**
     * This method passes the language codes of source and target message and performs transforming.
     * @param langCode An integer containing language code of the Identified language string code.
     */
    private void translateText(int langCode){
        mTranslatedText.setText("Translating...");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                //from language
                .setSourceLanguage(langCode)
                //to language
                .setTargetLanguage(FirebaseTranslateLanguage.EN)
                .build();

        final FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance()
                .getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                .build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid){
                translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>(){
                    @Override
                    public void onSuccess(String s){
                        mTranslatedText.setText(s);
                    }
                });
            }
        });
    }

}
