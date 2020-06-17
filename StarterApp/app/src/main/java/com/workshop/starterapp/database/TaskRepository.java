package com.workshop.starterapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.workshop.starterapp.data.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private TaskDao taskDao;

    private TaskRepository INSTANCE =null;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TaskRepository(Application application){
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        taskDao = taskDatabase.taskDao();
    }

    public  TaskRepository getRepository(Application application){
        if(INSTANCE == null){
            synchronized (TaskRepository.class){
                INSTANCE = new TaskRepository(application);
            }
        }
        return INSTANCE;
    }

    public void insertTask(final Task task){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(task);
            }
        });
    }

    public void deleteTask(final Task task){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTask(task);
            }
        });
    }
    public void updateTask(final Task task){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(task);
            }
        });
    }

    public LiveData<PagedList<Task>> getTasks(){
        int PAGE_SIZE = 15;
        return new LivePagedListBuilder<>(
                    taskDao.getTasks(), PAGE_SIZE
                ).build();
        }
    }


