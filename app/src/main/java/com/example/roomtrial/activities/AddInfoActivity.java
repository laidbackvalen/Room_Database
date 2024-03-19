package com.example.roomtrial.activities;

import static com.example.roomtrial.activities.CheckAndRetrieveInfoActivity.DB_NAME;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roomtrial.R;
import com.example.roomtrial.modelclasses.DataModel;
import com.example.roomtrial.room.database.UserDatabase;
import com.example.roomtrial.room.userentity.UserEntity;
import com.example.roomtrial.util.UtilClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

public class AddInfoActivity extends AppCompatActivity {
    ImageView userProfilePicture;
    EditText userName, userPhoneNumber, userEmail, userPassword;
    Button addInfoButton, updateInfoButton, viewDataBaseButton;

    UserDatabase database;
    String Url;
    DataModel model;
    private void databaseQuery() {
        database = Room.databaseBuilder(this, UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo);


        userProfilePicture = findViewById(R.id.profilePictureImageView);
        userName = findViewById(R.id.nameEdittext);
        userPhoneNumber = findViewById(R.id.phoneEdittext);
        userEmail = findViewById(R.id.emailEdittext);
        userPassword = findViewById(R.id.passwordEdittext);
        viewDataBaseButton = findViewById(R.id.viewDataButton);

        addInfoButton = findViewById(R.id.addButton);
        updateInfoButton = findViewById(R.id.updateButton);

        databaseQuery();

        userProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activityResultLauncher.launch("image/*");

            }
        });

        addInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userName.getText().toString();
                String phone = userPhoneNumber.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                UserEntity userEntity = new UserEntity(name, phone, email, password, Url);
                database.dao().userInsert(userEntity);
                Toast.makeText(AddInfoActivity.this, "Data added Succcessfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CheckAndRetrieveInfoActivity.class));
                finish();
            }
        });

        viewDataBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckAndRetrieveInfoActivity.class));
                finish();
            }
        });
    }

    ActivityResultLauncher<String> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
                UtilClass.getStorage().getReference().child("images").child("IMG_" + System.currentTimeMillis())
                        .putFile(result).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Url = uri.toString();
                                            userProfilePicture.setImageURI(result);

                                            Glide.with(AddInfoActivity.this)
                                                    .load(model.getImg())
                                                    .placeholder(R.drawable.ic_launcher_background)
                                                    .into(userProfilePicture);

                                            String url = model.getImg();
                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            });
}