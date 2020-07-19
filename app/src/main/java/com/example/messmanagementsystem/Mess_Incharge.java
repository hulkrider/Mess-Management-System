package com.example.messmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mess_Incharge extends AppCompatActivity {

    EditText mivc, pwd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess__incharge);

        mivc = (EditText) findViewById(R.id.MIVC);
        pwd = (EditText) findViewById(R.id.LoginPasswordMI);
        login = (Button) findViewById(R.id.LoginMI);

        String n,m;
        n = mivc.getText().toString();
        m = pwd.getText().toString();

        if(n == "Abc" && m == "123")
        {
            Toast.makeText(Mess_Incharge.this, "Good", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Mess_Incharge.this, "Incorrect Id Password ", Toast.LENGTH_SHORT).show();
        }
    }
}
