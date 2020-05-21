package com.workshop.finalapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.workshop.finalapp.data.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public static TaskDatabase INSTANCE = null;

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static  TaskDatabase getInstance(final Context context){
        if(INSTANCE==null){
            synchronized (TaskDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class,
                            "TaskDatabase"
                            ).addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            executor.execute(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                    }).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
