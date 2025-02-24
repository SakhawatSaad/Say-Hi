package com.shsaad.sayhi.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shsaad.sayhi.R;
import com.shsaad.sayhi.databinding.FragmentProfileBinding;
import com.shsaad.sayhi.ui.User;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        binding = FragmentProfileBinding.inflate(inflater,container,false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUser = firebaseUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(currentUser);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);

                binding.userName.setText(user.getUserName());
                binding.userEmail.setText(user.getUserEmail());
                binding.userPhone.setText(user.getUserphone());
                binding.userCounty.setText(user.getUserCountry());






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return binding.getRoot();
    }
}