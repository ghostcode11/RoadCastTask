package com.example.roadcasttask.roomr;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEn.class}, version = 1)
public abstract class UserDb extends RoomDatabase {
    public abstract UserDao userDao();
}