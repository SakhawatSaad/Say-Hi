package com.shsaad.sayhi.ui.activites;

import static com.shsaad.sayhi.Utils.showAlert;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shsaad.sayhi.R;
import com.shsaad.sayhi.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.createAccountTxt.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        binding.singInBtn.setOnClickListener(view -> {

            String email = binding.Email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            userSingIn(email,password);
        });

    }

    private void userSingIn(String email, String password) {




        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }else {

                    String errorMsg = task.getException().getLocalizedMessage();
                    showAlert(LoginActivity.this,errorMsg);
                }

            }
        });


    }
}