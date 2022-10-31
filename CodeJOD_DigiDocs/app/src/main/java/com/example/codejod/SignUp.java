package com.example.codejod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class SignUp extends AppCompatActivity {
    EditText username,email,password,phone;
    Button signupbtn;
    TextView logintext;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressBar pbar;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signupbtn=findViewById(R.id.signUpButton);
        logintext=findViewById(R.id.logintxt);
        phone=findViewById(R.id.phone);



        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        pbar=findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        fAuth= FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        pbar=findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name= username.getText().toString();
                String Password=password.getText().toString().trim();
                final String mailId= email.getText().toString().trim();
                String phoneNum = phone.getText().toString().trim();

                if(TextUtils.isEmpty(mailId)){
                    email.setError("Email ID is required");
                    return;
                }

                if(TextUtils.isEmpty(Password)){
                    password.setError("Password is required");
                    return;
                }

                if(Password.length()<6){
                    password.setError("Password should contain minimum 6 Characters");
                    return;
                }

                pbar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(mailId,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("email",mailId);
                            user.put("phone",phoneNum);
                            user.put("type","Applicant");


                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });


                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUp.this, "A verification Email has been sent to your Email ID", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Log.d(TAG, "onFailure" + e.getMessage());
                                }
                            });


                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                        else{
                            Toast.makeText(SignUp.this, ""+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }
}