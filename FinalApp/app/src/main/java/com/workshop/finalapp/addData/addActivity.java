package com.workshop.finalapp.addData;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //creating references to views
        final EditText title = findViewById(R.id.newTitle);
        final EditText description = findViewById(R.id.newDescription);
        Button submitBtn = findViewById(R.id.submit);

        //adding spinner elements
        Spinner spinner = findViewById(R.id.newPriority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Description = description.getText().toString();
                if(!Title.isEmpty() && !Description.isEmpty() && priority!=0){
                    Task task = new Task(Title,Description,priority);
                    Toast.makeText(getApplicationContext(),""+task.getPriority(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Missed an input",Toast.LENGTH_SHORT).show();
                }
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
