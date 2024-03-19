package com.example.roomtrial.activities;

import static com.example.roomtrial.activities.CheckAndRetrieveInfoActivity.DB_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.roomtrial.R;
import com.example.roomtrial.modelclasses.DataModel;
import com.example.roomtrial.room.database.UserDatabase;
import com.example.roomtrial.room.userentity.UserEntity;
import com.example.roomtrial.util.UtilClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.internal.Util;

import java.util.List;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.ViewHolder> {
    Context context;
    List<UserEntity> userEntities;
    String Url;

    RecyclerViewUserAdapter(Context context, List<UserEntity> userEntityList) {
        this.context = context;
        this.userEntities = userEntityList;
        databaseQuery();
    }

    UserDatabase database;

    private void databaseQuery() {
        database = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView propic;
        TextView userid, name, phone, email, password;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userid = itemView.findViewById(R.id.userIdTextView);
            name = itemView.findViewById(R.id.nameTextView);
            phone = itemView.findViewById(R.id.phoneTextView);
            email = itemView.findViewById(R.id.emailTextView);
            password = itemView.findViewById(R.id.passwordTextView);
            propic = itemView.findViewById(R.id.profilePictureImageView);


        }
        public void setData(DataModel model ){
            Glide.with(context.getApplicationContext())
                    .load(model.getImg())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(propic);
        }
    }


    @Override
    public RecyclerViewUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_layout, parent, false);
        ViewHolder v = new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUserAdapter.ViewHolder holder, int position) {

        holder.name.setText(userEntities.get(position).getName());
        holder.phone.setText(userEntities.get(position).getPhone());
        holder.email.setText(userEntities.get(position).getEmail());
        holder.password.setText(userEntities.get(position).getPassword());
        if (userEntities.size() > 0){

        }
//        String url = userEntities.get(position).getImg();
//        Uri uri = Uri.parse(url);
//        entity.setImg(url);
//        Log.d("UrlTAG", "url" + url);
//        holder.propic.setImageURI(uri);

        holder.userid.setText(""+userEntities.get(position).getId());
//        Log.d("idtag", "onBindViewHolder: " + id);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                database.dao().userDelete(entity);
//                notifyItemRemoved(holder.getAdapterPosition());

                // POP UP MENU
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenu().add("Delete");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();
                        database.dao().userDelete(userEntities.get(holder.getAdapterPosition()));
                        notifyItemRemoved(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        return false;

                    }

                });
                popupMenu.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return userEntities.size();
    }


}
