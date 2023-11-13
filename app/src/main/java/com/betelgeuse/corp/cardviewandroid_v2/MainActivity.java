package com.betelgeuse.corp.cardviewandroid_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listItem);
        List<Car> AllCars = new ArrayList<>();
        AllCars.add(new Car("Diablo", "Lamborghini", 2020, 280, R.drawable.lamborghini));
        AllCars.add(new Car("812", "Ferrari", 2020, 260, R.drawable.ferrari));
        AllCars.add(new Car("Chiron", "Bugatti", 2020, 380, R.drawable.bugatti));
        AllCars.add(new Car("Gemera", "Koenigsegg", 2020, 440, R.drawable.koenigsegg));

        CarAdapter carAdapter = new CarAdapter(AllCars, this, R.layout.car_item);
        listView.setAdapter(carAdapter);

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
        logo.setImageResource(car.getImageId());
        return view;
    }

}

class Car{
    String Model;
    String Mark;
    int Year;
    int Speed;
    int ImageId;

    public Car(String model, String mark, int year, int speed, int imageId) {
        Model = model;
        Mark = mark;
        Year = year;
        Speed = speed;
        ImageId = imageId;
    }
    public Car(){}

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

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}