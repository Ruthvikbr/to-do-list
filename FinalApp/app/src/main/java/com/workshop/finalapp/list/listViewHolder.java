package com.workshop.finalapp.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.workshop.finalapp.R;
import com.workshop.finalapp.data.Task;

public class listViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView,descriptionTextView,priorityTextView;

    public listViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title);
        descriptionTextView = itemView.findViewById(R.id.Description);
        priorityTextView = itemView.findViewById(R.id.priority);
    }
    public void bind(Task task){
        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getDescription());
        priorityTextView.setText(" "+ task.getPriority());

    }
}
