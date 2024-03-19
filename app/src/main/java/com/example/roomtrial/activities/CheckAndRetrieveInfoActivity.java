package com.example.roomtrial.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.roomtrial.R;
import com.example.roomtrial.room.database.UserDatabase;
import com.example.roomtrial.room.userentity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class CheckAndRetrieveInfoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<UserEntity> entitiesList = new ArrayList<>();
    RecyclerViewUserAdapter adapter;
    UserDatabase database;
    ImageView backIcon;

    public static final String DB_NAME = "users";

    private void databaseQuery() {
        database = Room.databaseBuilder(this, UserDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_and_retrieve_info);
        backIcon = findViewById(R.id.backIconRetrieveActivity);

        recyclerView = findViewById(R.id.recyclerViewCheckAndRetrieveActivity);

        databaseQuery();
        entitiesList = database.dao().getUser();

        adapter = new RecyclerViewUserAdapter(this, entitiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddInfoActivity.class));
                finish();
            }
        });

    }
}

//    ALTER TABLE user ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)