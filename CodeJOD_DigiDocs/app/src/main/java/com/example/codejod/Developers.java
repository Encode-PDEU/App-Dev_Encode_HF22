package com.example.codejod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Developers extends AppCompatActivity {
    TextView kev,uts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        kev = findViewById(R.id.textView2);
        uts = findViewById(R.id.textView4);

        kev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl( "https://www.linkedin.com/in/kevin-patel45/");
            }
        });

        uts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl( "https://www.linkedin.com/in/utsav-mehta-4253b0200/");
            }
        });



    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}