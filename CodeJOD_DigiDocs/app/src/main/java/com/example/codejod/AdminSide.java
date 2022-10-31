package com.example.codejod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


public class AdminSide extends AppCompatActivity {
    ImageView img;
    Button btn;
    FirebaseStorage firebaseStorage;
    StorageReference sr;
    EditText imgId;
    Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_side);
        img= findViewById(R.id.userImg);
        btn=findViewById(R.id.getImg);
        imgId=findViewById(R.id.id);
        goBtn=findViewById(R.id.go);

        firebaseStorage=FirebaseStorage.getInstance();
        sr=firebaseStorage.getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
                goBtn.setVisibility(View.VISIBLE);
            }
        });
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminVerify.class));
            }
        });
    }

    private void download() {
        String imageId= imgId.getText().toString();
        sr= FirebaseStorage.getInstance().getReference().child("images/"+imageId+".jpg");

        try {

            final File localfile= File.createTempFile(""+imageId,"jpg");
            final ProgressDialog pd= new ProgressDialog(AdminSide.this);
            pd.setTitle("Getting user documents");
            pd.show();
            sr.getFile(localfile)

                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            img.setImageBitmap(bitmap);
                            pd.dismiss();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    pd.dismiss();
                }
            }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull  FileDownloadTask.TaskSnapshot snapshot) {
                    double progressPercent=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    pd.setMessage(""+ progressPercent+"%");
                }
            });
        } catch (IOException e) {
            Log.d("error","error"+e);
        }

    }
    }
