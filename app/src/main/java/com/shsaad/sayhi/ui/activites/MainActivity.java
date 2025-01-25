package com.shsaad.sayhi.ui.activites;

import static com.shsaad.sayhi.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.shsaad.sayhi.R;
import com.shsaad.sayhi.databinding.ActivityMainBinding;
import com.shsaad.sayhi.ui.fragments.HomeFragment;
import com.shsaad.sayhi.ui.fragments.ProfileFragment;
import com.shsaad.sayhi.ui.fragments.UserFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), "").commit();
        firebaseAuth = FirebaseAuth.getInstance();


        binding.logOutBtn.setOnClickListener(view -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();


        });




        binding.btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == id.homeMenu) {
                    fragmentManager.beginTransaction().replace(id.fragment_container, new HomeFragment(), "").commit();
                } if (item.getItemId() == id.userMenu){
                    fragmentManager.beginTransaction().replace(id.fragment_container, new UserFragment(), "").commit();
                } if (item.getItemId() == id.profileMenu){
                    fragmentManager.beginTransaction().replace(id.fragment_container, new ProfileFragment(), "").commit();
                }


                return true;
            }

        });
    }
}