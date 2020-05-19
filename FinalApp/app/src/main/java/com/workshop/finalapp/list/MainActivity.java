package com.workshop.finalapp.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.workshop.finalapp.R;
import com.workshop.finalapp.addData.addActivity;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE =1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

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
    }
}
