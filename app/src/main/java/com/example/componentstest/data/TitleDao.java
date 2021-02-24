package com.example.componentstest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TitleDao {
    
    @Insert
    void addTitle(Title title);
    
    @Query("SELECT * FROM titles WHERE id == :id")
    Title getTitleById(int id);

    @Query("SELECT * FROM titles")
    LiveData<List<Title>> getAllTitles();

    @Query("DELETE FROM titles")
    void deleteAllTitles();

    @Delete
    void deleteTitle(Title title);

}
