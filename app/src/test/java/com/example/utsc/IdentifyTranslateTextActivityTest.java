package com.example.utsc;


import android.widget.TextView;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class IdentifyTranslateTextActivityTest {

    @org.junit.jupiter.api.Test
    public void TextShouldBeIdentified() {

    }

    @Test
    void onCreate() {
    }

    @Test
    void identifyLanguage() {
        String input = "How are you";
        String output ;
        String expected = "en";

        IdentifyTranslateTextActivity test_case = new IdentifyTranslateTextActivity();
        test_case.identifyLanguage();



    }

    @Test
    void translateText() {
    }
}