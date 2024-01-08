package com.betelgeuse.corp.cardviewandroid_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private static final int RESULT_CODE = 2;
    Intent intent;
    List<Car> AllCars = new ArrayList<>();

    //Добавление данных из addActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        intent = data;

        if (data != null)
        {
            String addMark = intent.getStringExtra("Mark");
            String addModel = intent.getStringExtra("Model");
            Integer addYear = intent.getIntExtra("Year",0);
            Integer addSpeed = intent.getIntExtra("Speed",0);
            String pas = intent.getStringExtra("pas");

            Toast.makeText(this, pas, Toast.LENGTH_SHORT).show();

            AllCars.add(new Car(addModel, addMark, addYear, addSpeed, Uri.parse(pas)));
        }else {
            Toast.makeText(this, "NOT DATA", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listItem);
//        AllCars.add(new Car("Diablo", "Lamborghini", 2020, 280, R.drawable.lamborghini));
//        AllCars.add(new Car("812", "Ferrari", 2020, 260, R.drawable.ferrari));
//        AllCars.add(new Car("Chiron", "Bugatti", 2020, 380, R.drawable.bugatti));
//        AllCars.add(new Car("Gemera", "Koenigsegg", 2020, 440, R.drawable.koenigsegg));

        CarAdapter carAdapter = new CarAdapter(AllCars, this, R.layout.car_item);
        listView.setAdapter(carAdapter);
    }

    public void addItemBtn(View View){
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 10);
    }
}

class CarAdapter extends BaseAdapter {
    List<Car> Cars;
    Context context;
    int TemplateLayout;
    LayoutInflater inflater;

    public CarAdapter(List<Car> cars, Context context, int templateLayout) {
        Cars = cars;
        this.context = context;
        TemplateLayout = templateLayout;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Cars.size();
    }

    @Override
    public Object getItem(int position) {
        return Cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(TemplateLayout, parent, false);
        TextView mark = view.findViewById(R.id.markName);
        TextView model = view.findViewById(R.id.modelName);
        TextView year = view.findViewById(R.id.yearDate);
        TextView speed = view.findViewById(R.id.speedValue);
        ImageView logo = view.findViewById(R.id.photoId);
        Car car = Cars.get(position);
        mark.setText(car.getMark());
        model.setText(car.getModel());
        year.setText(Integer.toString(car.getYear()));
        speed.setText(Integer.toString(car.getSpeed()));
//        logo.setImageResource(car.getImageId());
        // Установка изображения из URI
        logo.setImageURI(car.getImageUri());
        return view;
    }
}
class Car{
    String Model;
    String Mark;
    int Year;
    int Speed;
//    int ImageId;
    Uri ImageUri;

    public Car(String model, String mark, int year, int speed, Uri imageUri) {
        Model = model;
        Mark = mark;
        Year = year;
        Speed = speed;
//        ImageId = imageId;
        ImageUri = imageUri;
    }
    public Car(){}

    public Uri getImageUri() {
        return ImageUri;
    }

    public void setImageUri(Uri imageUri) {
        ImageUri = imageUri;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String mark) {
        Mark = mark;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
    }

//    public int getImageId() {
//        return ImageId;
//    }
//
//    public void setImageId(int imageId) {
//        ImageId = imageId;
//    }
}