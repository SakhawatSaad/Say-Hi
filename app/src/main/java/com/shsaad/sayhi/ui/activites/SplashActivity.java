package com.shsaad.sayhi.ui.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shsaad.sayhi.R;

public class SplashActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                nextPage();
            }
        },2000);


    }

    private void nextPage() {

        if (firebaseUser != null){
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }else {

            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finish();
        }
    }
}