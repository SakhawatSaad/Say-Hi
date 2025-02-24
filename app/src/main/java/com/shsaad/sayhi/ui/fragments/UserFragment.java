package com.shsaad.sayhi.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.shsaad.sayhi.User_Adpter;
import com.shsaad.sayhi.databinding.FragmentUserBinding;
import com.shsaad.sayhi.ui.User;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }

    FragmentUserBinding binding;

    DatabaseReference databaseReference;
    List<User> userList;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        userList = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    User user = ds.getValue(User.class);
                    userList.add(user);


                }

                User_Adpter adpter = new User_Adpter(getContext(), userList);
                binding.recyclerView.setAdapter(adpter);


//

                Log.i("TAG", "onDataChange: " + userList.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();

    }
}