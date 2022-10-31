package com.example.codejod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminReport extends AppCompatActivity {
    Button repo;
    EditText noticeN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);

        repo=findViewById(R.id.rep);
        noticeN=findViewById(R.id.notice);

        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] numbers={"6355364197","9426508970","8690115500"}; // applicant mobile number
                ActivityCompat.requestPermissions(AdminReport.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
                String notice = noticeN.getText().toString();
                int length=numbers.length;
                for(int i =0;i<length;i++){
                    SmsManager mySmsSender =SmsManager.getDefault();
                    mySmsSender.sendTextMessage(numbers[i],null,notice,null,null);
                    Toast.makeText(AdminReport.this, "Report sent successfully", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}