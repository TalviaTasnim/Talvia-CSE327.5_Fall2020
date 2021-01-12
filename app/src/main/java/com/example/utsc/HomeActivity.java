package com.example.utsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * This class contains methods of going to Dashboard and closing the app.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    /**
     * Go to dash.
     * @param view the view
     */
    public void goToDash(View view) {
        Toast.makeText(HomeActivity.this, "Going to DashBoard", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), DashBoard.class));
    }

    /**
     * Close the app.
     * @param view the view
     */
    public void closeApp(View view) {
        finish();
        System.exit(0);
    }
}