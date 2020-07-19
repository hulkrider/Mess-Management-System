package com.example.messmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {

    //Spinner spinner;

    EditText email,pwd;
    Button Login, CreateAccount;
    TextView forgotPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    //SignInButton btn;
    //GoogleSignInClient mGoogleSignInClient;

    //DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        //db = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.EmailId);
        pwd = (EditText) findViewById(R.id.LoginPassword);

        Login = (Button) findViewById(R.id.Login);
        CreateAccount = (Button) findViewById(R.id.CreateAccount);
        forgotPassword = (TextView)findViewById(R.id.ForgotPassword);

        //spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        //spinner.setOnItemSelectedListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //btn = (SignInButton)findViewById(R.id.btngoogle);

        FirebaseUser user = firebaseAuth.getCurrentUser();




        if(user !=null)
        {
            finish();
            Intent i = new Intent(Login_form.this, Page_3.class);
            startActivity(i);
        }

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Hello");
                Intent i = new Intent(Login_form.this, Signup_Form.class);
                startActivity(i);
            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString().trim();
                String password = pwd.getText().toString().trim();

                if (emailid.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Empty Fields",Toast.LENGTH_LONG).show();
                }
                else {

                    progressDialog.setMessage("In Progress");
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                //Toast.makeText(Login_form.this, " Successfully Logged In ", Toast.LENGTH_SHORT).show();
                                //Intent i = new Intent(Login_form.this, com.example.messmanagementsystem.Page_3.class);
                                //startActivity(i);
                                checkEmailVerification();
                            }

                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Login_form.this, " Email or Password Incorrect ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }



                //boolean checkemailpassword = db.emailpassword(emailid, password);

                /*if (checkemailpassword == true)
                {
                    Toast.makeText(getApplicationContext(), " Successfully Logged In ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login_form.this, com.example.messmanagementsystem.Page_3.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), " Wrong Email or Password ", Toast.LENGTH_LONG).show();
                }*/
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_form.this, Password_Activity.class);
                startActivity(i);
            }
        });


        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/




    }


    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag)
        {
            finish();
            Intent i = new Intent(this, User_Type.class);
            startActivity(i);
        }

        else
        {
            Toast.makeText(this, " Verify Your Email ", Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}
