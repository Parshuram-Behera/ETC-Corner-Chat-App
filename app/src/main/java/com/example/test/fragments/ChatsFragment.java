package com.example.test.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.test.UsersViewAdapter;
import com.example.test.databinding.FragmentChatsBinding;
//import com.example.test.modals.Usersdata;
import com.example.test.modals.Usersdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {



    public ChatsFragment() {
        // Required empty public constructor
    }

    FragmentChatsBinding binding ;
    ArrayList<Usersdata> list = new ArrayList<>();
    FirebaseDatabase database ;
//    private DatabaseReference mref ;
//    FirebaseDatabase database ;
//    ArrayList<Usersdata> users ;
//    UsersViewAdapter adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);
//
        database = FirebaseDatabase.getInstance();
//        users = new ArrayList<>();
//        adapter = new UsersViewAdapter( this , users);


//        binding.chatrecyclerview.setAdapter(adapter);




//        database.getReference().child("Userdata").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                users.clear();
//                for ( DataSnapshot snapshot1 : snapshot.getChildren()){
//
//                    Usersdata user = snapshot1.getValue(Usersdata.class);
//                    users.add(user);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        UserAdapter adapter = new UserAdapter(list , getContext());
//        binding.chatrecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext());
        binding.chatrecyclerview.setLayoutManager(layoutManager);

        database.getReference().child("Usersdata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for ( DataSnapshot snapshot1 : snapshot.getChildren()){
                    Usersdata users = snapshot1.getValue( Usersdata.class);
//                    users.getUserId(dataSnapshot.getKey());
                    list.add(users);
                }
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        return binding.getRoot();
    }
}