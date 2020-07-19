package com.example.messmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Activity extends AppCompatActivity {

    private TextView profileName, profileErno, profileMobno, profileGender;
    private Button profileEdit;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        profileName = (TextView)findViewById(R.id.tvProfileName);
        profileErno = (TextView)findViewById(R.id.tvProfileErno);
        profileMobno = (TextView)findViewById(R.id.tvProfileMobno);
        profileGender = (TextView)findViewById(R.id.tvProfileGender);
        profileEdit = (Button)findViewById(R.id.btnProfileUpdate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText("Name : " + userProfile.getUserName());
                profileErno.setText("Erno : " + userProfile.getUserErno());
                profileMobno.setText("Mobno : " + userProfile.getUserMobno());
                profileGender.setText("Gender : "+userProfile.getUserGender());
                //see from here
                //String i = userProfile.getUserGender();
                //if (i.equals("Male"))
                //    profileGender.setText("Gender : Male");
                //else
                //    profileGender.setText("Gender : Female");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Profile_Activity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile_Activity.this, Update_Activity.class);
                startActivity(i);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
