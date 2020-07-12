package com.example.registration.traintracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    TextView ltxt, lforgot;
    EditText lemail, lpassword;
    Button lbtn;
    ProgressBar lprogressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_log_in);

        ltxt = findViewById(R.id.logintxt);
        lforgot = findViewById(R.id.forgot);
        lemail = findViewById(R.id.email);
        lpassword = findViewById(R.id.password);
        lbtn = findViewById(R.id.loginbtn);
        lprogressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = lemail.getText().toString().trim();
                String password = lpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    lemail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    lpassword.setError("Password is required");
                    return;
                }

                if (password.length()<6){
                    lpassword.setError("Password must be 6 character long.");
                    return;
                }

                lprogressBar.setVisibility(View.VISIBLE);

                //Identify the user.

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LogInActivity.this,"Logged In Successfuly",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(LogInActivity.this,SelectionActivity.class));
                        }

                        else {
                            Toast.makeText(LogInActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                            lprogressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        ltxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        lforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordresetDialog = new AlertDialog.Builder(view.getContext());
                passwordresetDialog.setTitle("Reset Password?");
                passwordresetDialog.setMessage("Enter your email to reset your password.");
                passwordresetDialog.setView(resetMail);

                passwordresetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LogInActivity.this, "Reset Link sent to your email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LogInActivity.this, "Error! Reset Link was not sent." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordresetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordresetDialog.create().show();
            }
        });
    }
}
