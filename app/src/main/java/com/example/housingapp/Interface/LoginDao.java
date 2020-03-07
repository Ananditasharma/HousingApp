package com.example.housingapp.Interface;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.housingapp.Database.LoginTable;

import java.util.List;

@Dao
public interface LoginDao {

    @Insert
    void insertDetails(LoginTable data);

    @Query("select * from LoginDetails")
    LiveData <List<LoginTable>> getDetails();

    @Query("delete from LoginDetails")
    void deleteAllData();

}