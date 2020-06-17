package com.workshop.starterapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Task")
public class Task {

    @ColumnInfo(name = "priority")
    private int taskPriority;

    @PrimaryKey()
    @ColumnInfo(name = "taskName")
    private String TaskName;

    @ColumnInfo(name = "taskDescription")
    private String TaskDescription;

    public Task( String taskName, String taskDescription,int taskPriority) {
        this.taskPriority = taskPriority;
        TaskName = taskName;
        TaskDescription = taskDescription;
    }

    public Task() {
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }
}
