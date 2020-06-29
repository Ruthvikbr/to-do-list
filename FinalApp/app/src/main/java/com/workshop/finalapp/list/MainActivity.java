package com.workshop.finalapp.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import com.google.android.material.snackbar.Snackbar;
import com.workshop.finalapp.R;
import com.workshop.finalapp.addData.addActivity;
import com.workshop.finalapp.data.Task;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_ID = "extra_task_id";
    public static final String EXTRA_DATA_TITLE = "extra_task_title";
    public static final String EXTRA_DATA_DESCRIPTION = "extra_task_description";
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

        ConstraintLayout constraint = findViewById(R.id.ConstraintLayout);
        final Snackbar snackbar = Snackbar.make(constraint,"Task Deleted", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewModel.insertTask(task);
                    }
                });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                task = taskListPagingAdapter.getTaskAtPosition(pos);
                viewModel.deleteTask(task);
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        taskListPagingAdapter.setOnItemClickListener(new TaskListPagingAdapter.ClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Task currentTask = taskListPagingAdapter.getTaskAtPosition(position);
                launchUpdateTaskActivity(currentTask);
            }
        });
    }

    private void launchUpdateTaskActivity(Task currentTask) {
        Intent intent = new Intent(this,addActivity.class);
        intent.putExtra(EXTRA_DATA_ID,currentTask.getTask_id());
        intent.putExtra(EXTRA_DATA_TITLE,currentTask.getTitle());
        intent.putExtra(EXTRA_DATA_DESCRIPTION,currentTask.getDescription());
        intent.putExtra(EXTRA_DATA_PRIORITY,currentTask.getPriority());
        startActivityForResult(intent,UPDATE_DATA_REQUEST_CODE);
    }
}
