package com.example.codejod;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class passport_renewal extends AppCompatActivity {
    private ImageView profileIv;
    private Button intentBTn,conti;
    Button applyBtn;
    Uri imageUri ;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    EditText passportNumber;
    ProgressBar pbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_renewal);

        profileIv = findViewById(R.id.userImg);
        intentBTn = findViewById(R.id.getImg);
        applyBtn=findViewById(R.id.apply);
        conti=findViewById(R.id.cont);
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
        passportNumber=(EditText)findViewById(R.id.pass_num);
        pbar=findViewById(R.id.progressBar4);

        intentBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                galleryActivityResultLauncher.launch(intent);
            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            final String passportNum=passportNumber.getText().toString();
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(passportNum)){
                    passportNumber.setError("Passport Number in required");
                }
                uploadImage();

            }


            private void uploadImage() {
                String uid=passportNumber.getText().toString();
                final ProgressDialog pd= new ProgressDialog(passport_renewal.this);
                pd.setTitle("Submitting your application");
                pd.show();
                StorageReference riversRef = storageReference.child("images/"+uid+".jpg");
                riversRef.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Snackbar.make(findViewById(android.R.id.content),"Application made succesfully",Snackbar.LENGTH_LONG).show();
                                pd.dismiss();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {

                                pd.dismiss();

                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull  UploadTask.TaskSnapshot snapshot) {
                        double progressPercent=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        pd.setMessage(""+ progressPercent+"%");
                    }
                });
            }
        });

    }

    public ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();
                        profileIv.setImageURI(imageUri);
                    }
                    else {
                        Toast.makeText(passport_renewal.this, "Cancelled...", Toast.LENGTH_SHORT).show();
                    }
                }
            }

    );


}