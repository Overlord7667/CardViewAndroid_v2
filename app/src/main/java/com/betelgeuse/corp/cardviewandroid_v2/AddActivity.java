package com.betelgeuse.corp.cardviewandroid_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.security.Key;
import java.time.Year;

public class AddActivity extends AppCompatActivity {

    EditText mark;
    EditText model;
    EditText year;
    EditText speed;
    ImageView imageView;
    String pas;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Intent i = new Intent(this, MainActivity.class);
//        Bitmap b = null;
//        ByteArrayOutputStream bs = new ByteArrayOutputStream();
//        b.compress(Bitmap.CompressFormat.PNG, 50, bs);
//        i.putExtra("byteArray", bs.toByteArray());
//        startActivity(i);


        if (requestCode == 30 && data != null){
            Uri uri = data.getData();
            //Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            pas = uri.toString();
            imageView.setImageURI(uri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mark = findViewById(R.id.markName2);
        model = findViewById(R.id.modelName2);
        year = findViewById(R.id.yearDate2);
        speed = findViewById(R.id.speedValue2);
        imageView = findViewById(R.id.photo2Id);
    }

    public void backBtn(View view){
        finish();
    }

    public void addImage(View view){
        Toast.makeText(this, "IMAGE ADD", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 30);
        imageView.setImageURI(intent.getData());
    }

    public void addBtn(View view){
//        Toast.makeText(this, mark.getText(), Toast.LENGTH_SHORT).show();

        String Model = String.valueOf(model.getText());
        String Mark = String.valueOf(mark.getText());
        int Year = Integer.parseInt(year.getText().toString());
        int Speed = Integer.parseInt (speed.getText().toString());
//        int pas = Integer.parseInt (pas.getText().toString());


        Intent intent = new Intent();
        intent.putExtra("Mark", Mark);
        intent.putExtra("Model", Model);
        intent.putExtra("Year", Year);
        intent.putExtra("Speed", Speed);
//        intent.putExtra("pas", pas);

        setResult(200,intent);

        finish();
    }

}