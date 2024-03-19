package com.example.roomtrial.util;

import com.google.firebase.storage.FirebaseStorage;

public class UtilClass {

    public static FirebaseStorage getStorage(){
        return FirebaseStorage.getInstance();
    }

}
