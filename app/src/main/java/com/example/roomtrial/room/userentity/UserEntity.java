package com.example.roomtrial.room.userentity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//TABLE INFO
@Entity(tableName = "userinfo")
public class UserEntity {

    //COLUMN INFO
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "Image")
    String img;
    @ColumnInfo(name = "username")
    String name;
    @ColumnInfo(name = "userphonenumber")
    String phone;
    @ColumnInfo(name = "useremail")
    String email;
    @ColumnInfo(name = "userpassword")
    String password;

    //CONSTRUCTORS
    public UserEntity() {  //Default
    }

    public UserEntity( String name, String phone, String email, String password, String image) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.img = image;
    }


    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
