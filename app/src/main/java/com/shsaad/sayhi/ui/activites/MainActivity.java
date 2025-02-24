package com.shsaad.sayhi.ui.activites;

import static com.shsaad.sayhi.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(id.mytoolbar);
        setSupportActionBar(toolbar);



        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), "").commit();
        firebaseAuth = FirebaseAuth.getInstance();


//        binding.logOutBtn.setOnClickListener(view -> {
//            firebaseAuth.signOut();
//            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            finish();
//
//
//        });




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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.top_menu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == id.homeMenuTop) {
            Toast.makeText(this, "TopClicked", Toast.LENGTH_SHORT).show();
        } else if (itemId == id.logoutMenuTop) {
            logout();
        }


        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        firebaseAuth.signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class
        ));
        finish();

    }
}



  
