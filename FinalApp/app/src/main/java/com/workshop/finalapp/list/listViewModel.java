package com.workshop.finalapp.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.workshop.finalapp.Database.TaskRepository;
import com.workshop.finalapp.data.Task;

public class listViewModel extends AndroidViewModel {

    private TaskRepository repository;

    private LiveData<PagedList<Task>> pagedListLiveData;

    public listViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        pagedListLiveData = repository.getAllTasks();
    }

    public void deleteTask(Task task){
        repository.deleteTask(task);
    }
}
