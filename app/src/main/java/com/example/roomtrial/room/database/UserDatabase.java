package com.example.roomtrial.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomtrial.room.dao.UserDatabaseAccessObject;
import com.example.roomtrial.room.userentity.UserEntity;

@Database(entities = UserEntity.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDatabaseAccessObject dao();
}
