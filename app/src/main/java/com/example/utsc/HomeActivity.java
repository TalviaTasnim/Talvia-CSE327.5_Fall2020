package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void goToDash(View view) {
        Toast.makeText(HomeActivity.this, "OCR clicked!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), DashBoard.class));
    }

    public void closeApp(View view) {
        finish();
        System.exit(0);
    }
}