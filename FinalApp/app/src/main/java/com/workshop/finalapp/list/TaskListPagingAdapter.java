package com.workshop.finalapp.list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.workshop.finalapp.R;
import com.workshop.finalapp.data.Task;

public class TaskListPagingAdapter extends PagedListAdapter<Task,listViewHolder> {

    private ClickListener clickListener;

    protected TaskListPagingAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new listViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, final int position) {
        final Task currentTask = getItem(position);
        if(currentTask!=null){
            holder.bind(currentTask);
            if(clickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.itemClick(position,v);
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface ClickListener{
         void itemClick(int position,View view);
    }
    public  Task getTaskAtPosition(int position){
        return getItem(position);
    }

    private static DiffUtil.ItemCallback<Task> DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return (oldItem.getTitle() == newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.isTaskEqual(newItem);
        }
    };


}
