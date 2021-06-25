package com.example.roadcasttask.roomr;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<UserEn> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<UserEn> userEn);

}
