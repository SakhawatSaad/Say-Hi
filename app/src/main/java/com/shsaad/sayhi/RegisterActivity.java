package com.shsaad.sayhi;

import static com.shsaad.sayhi.Utils.showAlert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shsaad.sayhi.databinding.ActivityRegisterBinding;
import com.yesterselga.countrypicker.CountryPicker;
import com.yesterselga.countrypicker.Theme;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    FirebaseAuth firebaseAuth;

    ProgressDialog dialog;
    CountryPicker picker;
    DatabaseReference databaseReference;


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
        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        dialog = new ProgressDialog(RegisterActivity.this);
        dialog.setMessage("Please Wait");

        binding.singUpBtn.setOnClickListener(view -> {
            String name = binding.Name.getText().toString();
            String phone = binding.Phone.getText().toString();
            String email = binding.Email.getText().toString();
            String password = binding.password.getText().toString();

            if (name.equals("")) {
                showAlert(RegisterActivity.this, "Name is Empty");
            } else if (phone.equals("")) {
                showAlert(RegisterActivity.this, "Phone is Empty");
            } else if (email.equals("")) {
                showAlert(RegisterActivity.this, "Email is Empty");
            } else if (password.equals("")) {
                showAlert(RegisterActivity.this, "Password is Empty");
            } else if (password.length() < 6) {
                showAlert(RegisterActivity.this, "Password Must be More then 6");
            } else {



                createAccount(email,password,name,phone);




            }

        });

        picker = CountryPicker.newInstance("Select Country", Theme.LIGHT);  // Set Dialog Title and Theme
        picker.setListener((name, code, dialCode, flagDrawableResID) -> {


            binding.countryCode.setText(code);
            binding.countryName.setText(name);
            binding.countryDialCode.setText(dialCode);
            binding.countryIcon.setImageResource(flagDrawableResID);

            picker.dismiss();

        });

        binding.showCountry.setOnClickListener(v -> picker.show(getSupportFragmentManager(), "COUNTRY_PICKER"));


    }

    private void createAccount(String email,String password,String name,String phone) {

        dialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                dialog.dismiss();
                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    String userId = firebaseUser.getUid();


                    HashMap<String,Object> userMap = new HashMap<>();
                    userMap.put("userName",name);
                    userMap.put("userId",userId);
                    userMap.put("userEmail",email);
                    userMap.put("userphone",phone);
                    userMap.put("userCountry",binding.countryName.getText().toString());

                    databaseReference.child(userId).setValue(userMap).addOnSuccessListener(unused -> {
                        dialog.dismiss();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();



                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showAlert(RegisterActivity.this,e.getLocalizedMessage());

                        }
                    });







                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showAlert(RegisterActivity.this, e.getMessage().toString());
                dialog.dismiss();
            }
        });
    }

}