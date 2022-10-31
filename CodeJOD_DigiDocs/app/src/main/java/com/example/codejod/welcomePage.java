package com.example.codejod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class welcomePage extends AppCompatActivity {
    Button contBtn;
    FirebaseAuth fbaseAuth;
    String userId;
    FirebaseFirestore fstore;
    ProgressBar pbar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        contBtn=findViewById(R.id.contBtn);
        pbar3 =findViewById(R.id.progressBar3);


        fstore=FirebaseFirestore.getInstance();
        fbaseAuth=FirebaseAuth.getInstance();

        userId= Objects.requireNonNull(fbaseAuth.getCurrentUser()).getUid();
        DocumentReference dr=fstore.collection("users").document(userId);

        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                String type=documentSnapshot.getString("type");
                String admin = "Admin";
                String applicant ="Applicant";

                contBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assert type != null;
                        if(type.equals(applicant)){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            pbar3.setVisibility(View.VISIBLE);
                        }
                        else if (type.equals(admin)){
                            startActivity(new Intent(getApplicationContext(), AdminSide.class));
                            pbar3.setVisibility(View.VISIBLE);
                        }

                    }
                });

            }
        });



    }
}