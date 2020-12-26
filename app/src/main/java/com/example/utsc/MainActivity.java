package com.example.utsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;
import java.util.function.LongConsumer;

/**
 * This class contains methods for text extraction and text display
 * @author Safayat
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Defining variables
     */
    private Button capture_image_btn, detect_text_btn;
    private ImageView image_view;
    private TextView text_view;
    static final int request_image_capture = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Assigning variables
         */
        capture_image_btn = findViewById(R.id.capture_image_btn);
        detect_text_btn = findViewById(R.id.detect_text_image_btn);
        image_view = findViewById(R.id.image_view);
        text_view = findViewById(R.id.text_display);

        capture_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                text_view.setText("");
            }
        });

        detect_text_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectTextFromImage();
            }
        });
    }

    /**
     * This method allows user to open the phone camera to take pictures.
     */
    private void dispatchTakePictureIntent() {
        Intent take_picture_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(take_picture_intent, request_image_capture);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    /**
     * This method is displaying the captured image in a imageView.
     * @param requestCode an integer
     * @param resultCode an integer
     * @param data an Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_image_capture && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            image_view.setImageBitmap(imageBitmap);
        }
    }

    /**
     * This method detects text from image.
     */
    private void detectTextFromImage() {
        FirebaseVisionImage firebase_vision_image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector firebase_vision_text_detector = FirebaseVision.getInstance().getVisionTextDetector() ;
        firebase_vision_text_detector.detectInImage(firebase_vision_image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                displayTextFromImage(firebaseVisionText);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error " , e.getMessage());
            }
        });
    }

    /**
     * This method displays text from image.
     * @param firebaseVisionText A FirebaseVisionText object
     */
    private void displayTextFromImage(FirebaseVisionText firebaseVisionText) {
        List<FirebaseVisionText.Block> block_list = firebaseVisionText.getBlocks();
        if(block_list.size() == 0){
            Toast.makeText(this, "No Text Found in image!", Toast.LENGTH_SHORT).show();
        }
        else{
            for(FirebaseVisionText.Block block : firebaseVisionText.getBlocks()){
                String text = block.getText();
                text_view.setText(text);
            }
        }
    }

}