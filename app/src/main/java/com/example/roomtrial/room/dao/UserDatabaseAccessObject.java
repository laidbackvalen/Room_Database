package com.example.roomtrial.room.dao;

import static java.nio.charset.CodingErrorAction.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtrial.room.userentity.UserEntity;

import java.util.List;

@Dao
public interface UserDatabaseAccessObject {
    @Query("select * from userinfo")
    List<UserEntity> getUser();

//    @Insert(onConflict = onConflictStrategy.REPLACE)
    @Insert
    void userInsert(UserEntity userEntity);

    @Update
    void userupdate(UserEntity userEntity);

    @Delete
    void userDelete(UserEntity userEntity);


}
