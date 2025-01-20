package com.shsaad.sayhi;

import static com.shsaad.sayhi.Utils.showAlert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shsaad.sayhi.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    FirebaseAuth firebaseAuth;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(RegisterActivity.this);
        dialog.setMessage("Please Wait");

        binding.singUpBtn.setOnClickListener(view -> {
            String name = binding.Name.getText().toString();
            String phone = binding.Phone.getText().toString();
            String email = binding.Email.getText().toString();
            String password = binding.password.getText().toString();

            if (name.equals("")){
                showAlert(RegisterActivity.this,"Name is Empty");
            } else if (phone.equals("")){
                showAlert(RegisterActivity.this,"Phone is Empty");
            } else if (email.equals("")){
                showAlert(RegisterActivity.this,"Email is Empty");
            }else if (password.equals("")){
                showAlert(RegisterActivity.this,"Password is Empty");
            } else if (password.length()<6){
                showAlert(RegisterActivity.this,"Password Must be More then 6");
            } else {


                dialog.show();



                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.dismiss();
                        if (task.isSuccessful()){
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showAlert(RegisterActivity.this,e.getMessage().toString());
                        dialog.dismiss();
                    }
                });


            }

        });







    }
}