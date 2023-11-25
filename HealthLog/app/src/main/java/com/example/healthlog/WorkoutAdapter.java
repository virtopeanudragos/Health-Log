package com.example.healthlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * Adapter class for the RecyclerView in WorkoutFragment.
 * Handles the display of workout routines.
 */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private final List<WorkoutRoutine> routines;

    /**
     * Constructor for the WorkoutAdapter
     */
    public WorkoutAdapter(List<WorkoutRoutine> routines) {
        this.routines = routines;
    }

    /**
     * Inflates the layout for each item of the RecyclerView.
     */
    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_card, parent, false);
        return new WorkoutViewHolder(view);
    }

    /**
     * Binds data to a ViewHolder.
     */
    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        holder.bindData(routines.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return routines.size();
    }

    /**
     * Provides a reference to the views for each data item.
     */
    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvRating;
        final TextView tvDuration;

        WorkoutViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvDuration = itemView.findViewById(R.id.tvDuration);
        }

        /**
         * Binds the WorkoutRoutine data to the view elements in the ViewHolder.
         */
        void bindData(WorkoutRoutine routine) {
            tvTitle.setText(routine.getTitle());
            tvRating.setText(routine.getRating());
            tvDuration.setText(routine.getDuration());
        }
    }
}

