package com.example.messmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Page_4 extends AppCompatActivity {

    Button Breakfast,Lunch,Dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_4);

        Breakfast = (Button) findViewById(R.id.Breakfast);
        Lunch = (Button) findViewById(R.id.Lunch);
        Dinner = (Button)findViewById(R.id.Dinner);

        Breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Page_4.this, BreakfastSelect.class);
                startActivity(i);
                Breakfast.setEnabled(false);
            }
        });

        Lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Page_4.this, LunchSelect.class);
                startActivity(i);
                Lunch.setEnabled(false);
            }
        });

        Dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Page_4.this, DinnerSelect.class);
                startActivity(i);
                Dinner.setEnabled(false);
            }
        });
    }
}
