package com.example.messmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update_Activity extends AppCompatActivity {

    private EditText username, usermobno, usererno;
    public EditText useremail, usergender, userpwd;
    private TextView changepwd;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);

        username = (EditText)findViewById(R.id.etUpdateName);
        usererno = (EditText)findViewById(R.id.etUpdateErNo);
        usermobno = (EditText)findViewById(R.id.etUpdateMobNo);
        //useremail = findViewById(R.id.etUpdateEmail);
        //userpwd = findViewById(R.id.LoginPassword);

        changepwd = (TextView)findViewById(R.id.tvchangepwd);

        save = (Button)findViewById(R.id.btnUpdate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                username.setText(userProfile.getUserName());
                usererno.setText(userProfile.getUserErno());
                usermobno.setText(userProfile.getUserMobno());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Update_Activity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String erno = usererno.getText().toString();
                String mobno = usermobno.getText().toString();
                //String email = useremail.getText().toString();
                //String pwd = userpwd.getText().toString();
                //String gender = usergender.getText().toString();

                UserProfile userProfile = new UserProfile(name, erno, mobno);
                databaseReference.setValue(userProfile);

                finish();
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
