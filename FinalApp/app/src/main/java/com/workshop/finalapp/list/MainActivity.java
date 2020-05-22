package com.workshop.finalapp.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.workshop.finalapp.R;
import com.workshop.finalapp.addData.addActivity;
import com.workshop.finalapp.data.Task;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_STATE_TITLE = "extra_task_title";
    public static final String EXTRA_DATA_DESCRIPTIONL = "extra_task_description";
    public static final String EXTRA_DATA_PRIORITY = "extra_task_priority";

    private listViewModel viewModel;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivityForResult(intent,NEW_DATA_REQUEST_CODE);
            }
        });

        viewModel = new ViewModelProvider(this).get(listViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.toDoListRecyclerView);
        final TaskListPagingAdapter taskListPagingAdapter = new TaskListPagingAdapter();
        recyclerView.setAdapter(taskListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.pagedListLiveData.observe(this, new Observer<PagedList<Task>>() {
            @Override
            public void onChanged(PagedList<Task> tasks) {
                taskListPagingAdapter.submitList(tasks);
            }
        });
    }
}
