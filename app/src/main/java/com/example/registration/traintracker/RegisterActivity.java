package com.example.registration.traintracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText rfullName, remail, rpassword, rphone;
    Button rbtn;
    TextView rtxt;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        rfullName = findViewById(R.id.fullName);
        remail = findViewById(R.id.email);
        rpassword = findViewById(R.id.password);
        rphone = findViewById(R.id.phone);
        rbtn = findViewById(R.id.rgstrbtn);
        rtxt = findViewById(R.id.rgstrtxt);
        progressBar = findViewById(R.id.rgstrprogressBar);

        fAuth = FirebaseAuth.getInstance();

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = remail.getText().toString().trim();
                String password = rpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    remail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    rpassword.setError("Password is required");
                    return;
                }

                if (password.length()<6){
                    rpassword.setError("Password must be 6 character long.");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Now registering the user in fire-base.

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                        }

                        else {
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        rtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LogInActivity.class));
            }
        });

    }

     /*@Override
    protected void onStart() {
        super.onStart();
        if (fAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
    } Jehetu user logged in houar por logged out option deya hoy nai.
     sehetu se porjonto notun register show korbe na ei ongser maddhome.
     etar karjokaritar jonno logged out option set korte hobe.*/

}
