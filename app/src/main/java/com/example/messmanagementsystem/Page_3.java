package com.example.messmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

public class Page_3 extends AppCompatActivity {

    Button MenuForToday;
    Button MenuForTomorrow;
    Button SelectForDay3;
    Button Ratefood;
    Button Logout;
    TextView profileName, profileErno;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_3);

        Logout = (Button)findViewById(R.id.Logout);
        profileName = (TextView)findViewById(R.id.tvName);
        profileErno = (TextView)findViewById(R.id.tvErno);
        Ratefood = (Button) findViewById(R.id.RateFood);
        SelectForDay3 = (Button) findViewById(R.id.SelectForDay3);

        SelectForDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Page_3.this, Page_4.class);
                startActivity(i);
                //SelectForDay3.setEnabled(false);
            }
        });

        Ratefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Page_3.this, Rating.class);
                startActivity(i);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //String n = "171B033";

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText(userProfile.getUserName());
                profileErno.setText(userProfile.getUserErno());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Page_3.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent i =new Intent(Page_3.this, Login_form.class);
                startActivity(i);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.LogoutMenu:
            {
                firebaseAuth.signOut();
                finish();
                Intent i =new Intent(Page_3.this, Login_form.class);
                startActivity(i);
            }

            case R.id.profileMenu:
            {
                Intent i = new Intent(Page_3.this, Profile_Activity.class);
                startActivity(i);
            }
        }
        return true;
    }

}
