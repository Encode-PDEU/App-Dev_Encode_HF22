package com.example.codejod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText mail,passcode;
    Button loginBtn;
    TextView registerTxt;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fstore;
    ProgressBar pbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.logInMailID);
        passcode=findViewById(R.id.userpassword);
        registerTxt=findViewById(R.id.registertxt);
        loginBtn=findViewById(R.id.logInBtn);
        pbar2=findViewById(R.id.progressBar2);


        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LogMailID=mail.getText().toString();
                String passCode=passcode.getText().toString();

                if(TextUtils.isEmpty(LogMailID)){
                    mail.setError("Email ID is required");
                    return;
                }

                if(TextUtils.isEmpty(passCode)){
                    passcode.setError("Password is required");
                    return;
                }

                if(passCode.length()<6){
                    passcode.setError("Password should contain minimum 6 characters");
                    return;
                }


                pbar2.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(LogMailID,passCode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(Login.this, "Welcome to Digi-Docs", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),welcomePage.class));


                        }
                        else{
                            Toast.makeText(Login.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



    }
}