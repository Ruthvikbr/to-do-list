package com.workshop.finalapp.addData;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.workshop.finalapp.Database.TaskRepository;
import com.workshop.finalapp.data.Task;

public class addViewModel extends AndroidViewModel {

    private TaskRepository repository;

    private LiveData<PagedList<Task>> pagedListLiveData;

    public addViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
    }

    public void insertTask(Task task){
        repository.insertTask(task);
    }

    public void updateTask(Task task){
        repository.updateTask(task);
    }


}
