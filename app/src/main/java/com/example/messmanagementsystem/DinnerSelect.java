package com.example.messmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DinnerSelect extends AppCompatActivity { RadioGroup Dinner;
    RadioButton SelectDinner;
    Button Submit;
    String s1;
    DatabaseReference DatabaseBreakfast;
    FirebaseAuth firebaseAuth;
    //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_select);

        //DatabaseBreakfast = FirebaseDatabase.getInstance().getReference("Breakfast");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseBreakfast = firebaseDatabase.getReference(firebaseAuth.getUid()+"Dinner");

        Dinner = (RadioGroup) findViewById(R.id.RadioGroupDinner);
        Submit = (Button) findViewById(R.id.DinnerSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = Dinner.getCheckedRadioButtonId();

                SelectDinner = (RadioButton) findViewById(i);

                s1 = SelectDinner.getText().toString();

                if(Dinner.getCheckedRadioButtonId() != -1)
                {
                    String id = DatabaseBreakfast.push().getKey();
                    Dinner breakfast = new Dinner(s1);
                    DatabaseBreakfast.setValue(breakfast);
                    Toast.makeText(getApplicationContext(), " Submitted ", Toast.LENGTH_SHORT).show();
                    finish();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), " Please Select at least One Option ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
