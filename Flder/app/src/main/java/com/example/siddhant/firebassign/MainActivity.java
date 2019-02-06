package com.example.siddhant.firebassign;

import android.app.DownloadManager;
import android.graphics.ColorSpace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    FirebaseDatabase dbb;
    List<AssnModel> list;
    List<String> nameList;
    List<String> ageList;

    //MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list_recyc);
        dbb = FirebaseDatabase.getInstance();
        mDatabase = dbb.getReference("Users");
        mDatabase.keepSynced(true);

        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                //HashMap<String, Integer> map = new HashMap<>();
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                list = new ArrayList<AssnModel>();
                ageList = new ArrayList<>();
                nameList = new ArrayList<>();
                list = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    AssnModel assnModel = userSnapshot.getValue(AssnModel.class);
                    //List<String> yourArray = userSnapshot.getValue(t);
                    String name= userSnapshot.child("Name").getValue().toString();
                    String age= userSnapshot.child("Age").getValue().toString();

                    AssnModel userdetails = userSnapshot.getValue(AssnModel.class);
                    AssnModel listdata = new AssnModel();

                    listdata.setUserName(name);
                    listdata.setUserAge(age);
                    list.add(listdata);
                    //Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_LONG).show();


                }
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,MainActivity.this);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Hello", "Failed to read", databaseError.toException());
            }
        });


    }


}