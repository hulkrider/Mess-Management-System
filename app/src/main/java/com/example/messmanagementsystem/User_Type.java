package com.example.messmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_Type extends AppCompatActivity {
    Button student,faculty,mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__type);

        student = (Button) findViewById(R.id.btnstudent);
        faculty = (Button) findViewById(R.id.btnfaculty);
        mess = (Button) findViewById(R.id.btnmessincharge);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Type.this, Page_3.class);
                startActivity(i);
            }
        });

        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Type.this, Page_3.class);
                startActivity(i);
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User_Type.this, Mess_Incharge.class);
                startActivity(i);
            }
        });

    }
}
