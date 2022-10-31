package com.example.codejod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminVerify extends AppCompatActivity {
    Button verify;
    Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verify);

        ActivityCompat.requestPermissions(AdminVerify.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        verify=findViewById(R.id.verify);
        report=findViewById(R.id.report);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] numbers={"6355364197","9426508970","8690115500"}; // applicant mobile number
                String noticeN = "Passport Renewal request accepted";
                String notice2 = "Show this message and collect your passport from 11,abc complex after 01/01/2021";
                int length=numbers.length;
                for(int i =0;i<length;i++){
                    SmsManager mySmsSender =SmsManager.getDefault();
                    mySmsSender.sendTextMessage(numbers[i],null,noticeN,null,null);
                    mySmsSender.sendTextMessage(numbers[i],null,notice2,null,null);
                    Toast.makeText(AdminVerify.this, "Verification message sent successfully ", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AdminReport.class));

            }
        });

    }
}