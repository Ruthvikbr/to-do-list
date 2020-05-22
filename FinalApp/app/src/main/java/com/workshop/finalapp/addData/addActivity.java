package com.workshop.finalapp.addData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.workshop.finalapp.R;
import com.workshop.finalapp.data.Task;

public class addActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int[] priorities = {1,2,3};
    private int priority = 0;
    public static final String EXTRA_DATA_TITLE = "extra_task_title";
    public static final String EXTRA_DATA_DESCRIPTION = "extra_task_description";
    public static final String EXTRA_DATA_PRIORITY = "extra_task_priority";

    private addViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Bundle extras = getIntent().getExtras();

        viewModel = new ViewModelProvider(this).get(addViewModel.class);

        //creating references to views
        final EditText title = findViewById(R.id.newTitle);
        final EditText description = findViewById(R.id.newDescription);
        Button submitBtn = findViewById(R.id.submit);
        int taskPriority = 0;

        //adding spinner elements
        Spinner spinner = findViewById(R.id.newPriority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        if(extras != null){
            String taskTitle = extras.getString(EXTRA_DATA_TITLE,"");
            taskPriority = extras.getInt(EXTRA_DATA_PRIORITY) - 1;
            String taskDescription = extras.getString(EXTRA_DATA_DESCRIPTION,"");

            if(!taskTitle.isEmpty()){
                title.setText(taskTitle);
            }

            if(!taskDescription.isEmpty()){
                description.setText(taskDescription);
                description.setSelection(taskDescription.length());
                description.requestFocus();
            }

            if(taskPriority!=0){
                spinner.setSelection(taskPriority);
            }

            submitBtn.setText("UPDATE");
        }


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Description = description.getText().toString();
                if(!Title.isEmpty() && !Description.isEmpty() && priority!=0){
                 if(extras!=null){
                     Task task = new Task(Title,Description,priority);
                     viewModel.updateTask(task);
                 }
                 else{
                     Task task = new Task(Title,Description,priority);
                     viewModel.insertTask(task);
                 }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Missed an input",Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK);
                finish();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       priority = priorities[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
