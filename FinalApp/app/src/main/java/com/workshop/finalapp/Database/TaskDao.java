package com.workshop.finalapp.Database;


import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.workshop.finalapp.data.Task;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("Select * from Task ORDER BY Priority asc")
    DataSource.Factory<Integer,Task> getAllTasks();


}
