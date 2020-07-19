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

public class BreakfastSelect extends AppCompatActivity {

    RadioGroup Breakfast;
    RadioButton SelectBreakfast;
    Button Submit;
    String s1;
    DatabaseReference DatabaseBreakfast;
    FirebaseAuth firebaseAuth;
    //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_select);

        //DatabaseBreakfast = FirebaseDatabase.getInstance().getReference("Breakfast");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseBreakfast = firebaseDatabase.getReference(firebaseAuth.getUid()+"Breakfast");

        Breakfast = (RadioGroup) findViewById(R.id.RadioGroupBreakfast);
        Submit = (Button) findViewById(R.id.BreakfastSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = Breakfast.getCheckedRadioButtonId();

                SelectBreakfast = (RadioButton) findViewById(i);

                s1 = SelectBreakfast.getText().toString();

                if(Breakfast.getCheckedRadioButtonId() != -1)
                {
                    String id = DatabaseBreakfast.push().getKey();
                    Breakfast breakfast = new Breakfast(s1);
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
