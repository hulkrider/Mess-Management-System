package com.example.messmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_Form extends AppCompatActivity {

    //DatabaseHelper db;
    EditText name,erno,mobno,email,pwd, cnfpwd;
    RadioGroup gender;
    RadioButton genderOption;
    Button Register, RegisterLogin;
    private FirebaseAuth firebaseAuth;
    String s1, s2, s3, s4, s5, s6, s7;
    String n,m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        firebaseAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.FullName);
        erno = (EditText) findViewById(R.id.RegisterErNo);
        mobno = (EditText) findViewById(R.id.MobNo);
        email = (EditText) findViewById(R.id.EmailId);
        pwd = (EditText) findViewById(R.id.LoginPassword);
        cnfpwd = (EditText) findViewById(R.id.CnfPassword);
        gender = (RadioGroup) findViewById(R.id.RadioGroup);
        Register = (Button) findViewById(R.id.Register);
        RegisterLogin = (Button) findViewById(R.id.RegisterLogin);

        RegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Jrllo");
                Intent i = new Intent(Signup_Form.this,Login_form.class);
                startActivity(i);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = name.getText().toString().trim();
                s2 = erno.getText().toString().trim();
                s3 = mobno.getText().toString().trim();
                s4 = email.getText().toString().trim();
                s5 = pwd.getText().toString().trim();
                s6 = cnfpwd.getText().toString().trim();


                int i = gender.getCheckedRadioButtonId();

                genderOption = (RadioButton) findViewById(i);

                s7 = genderOption.getText().toString();


                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals(""))
                {
                    Toast.makeText(getApplicationContext(), " Fields are Empty ", Toast.LENGTH_LONG).show();
                }

                else
                {
                    if (s5.equals(s6))
                    {
                        //Make data base

                        firebaseAuth.createUserWithEmailAndPassword(s4, s5).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    sendEmailVerification();
                                    //Toast.makeText(Signup_Form.this," Registered Successfully, Verification Email sent ", Toast.LENGTH_SHORT).show();
                                    //Intent i = new Intent(Signup_Form.this, Login_form.class);
                                    //startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(Signup_Form.this, " Registration UnSuccessful ",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        /*boolean checkemail = db.checkEmail(s4);
                        if (checkemail == true)
                        {
                            //Toast.makeText(getApplicationContext(), " Hello ", Toast.LENGTH_LONG).show();
                            boolean insert = db.insert(s1,s2,s3,s4,s5);
                            if (insert == true)
                            {
                                Toast.makeText(getApplicationContext(), " Registered Successfully ", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Signup_Form.this, Page_3.class);
                            }                                startActivity(i);

                        }

                        else
                        {
                            Toast.makeText(getApplicationContext(), " Email already exists ", Toast.LENGTH_LONG).show();
                        }*/
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), " Passwords Don't Match ", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void sendEmailVerification()
    {
        final FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!= null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful())
                    {
                        sendUserData();
                        Toast.makeText(Signup_Form.this," Registered Successfully, Verification Email sent ", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        Intent i = new Intent(Signup_Form.this, Login_form.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Signup_Form.this, " Mail Not Verified ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }



    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //n=firebaseAuth.getUid();
        //n=s2;
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        //DatabaseReference myRef = firebaseDatabase.getReference(n);
        UserProfile userProfile = new UserProfile(s1, s2, s3, s4, s5, s7);
        myRef.setValue(userProfile);
    }
}
