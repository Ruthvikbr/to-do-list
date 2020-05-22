package com.workshop.finalapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.PrimaryKey;

import com.workshop.finalapp.data.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private static TaskRepository repository = null;

    private TaskDao taskDao;

    private static int PAGE_SIZE = 15;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getInstance(application);
        taskDao = db.taskDao();
    }

    public static TaskRepository getRepository(Application application) {
        if (repository == null) {
            synchronized (TaskRepository.class) {
                if (repository == null) {
                    repository = new TaskRepository(application);
                }
            }
        }
        return repository;
    }

    public void insertTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.insertTask(task);
            }
        });
    }

    public void updateTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.updateTask(task);
            }
        });
    }

    public void deleteTask(final Task task) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                taskDao.deleteTask(task);
            }
        });
    }

    public LiveData<PagedList<Task>> getAllTasks() {
        return new LivePagedListBuilder<>(
                taskDao.getAllTasks(), PAGE_SIZE
        ).build();
    }
}

