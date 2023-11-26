package com.example.healthlog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class CustomWorkoutDialogFragment extends DialogFragment {

    private EditText edtTitle, edtRating, edtDuration;
    private Button btnSave;
    private OnWorkoutSavedListener savedListener;

    public interface OnWorkoutSavedListener {
        void onWorkoutSaved();
    }

    public void setOnWorkoutSavedListener(OnWorkoutSavedListener listener) {
        this.savedListener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_workout_dialog, container, false);
        edtTitle = view.findViewById(R.id.edtTitle);
        edtRating = view.findViewById(R.id.edtRating);
        edtDuration = view.findViewById(R.id.edtDuration);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveWorkout());

        return view;
    }

    private void saveWorkout() {
        String title = edtTitle.getText().toString().trim();
        String rating = edtRating.getText().toString().trim();
        String duration = edtDuration.getText().toString().trim();

        // Validate the inputs
        if (title.isEmpty() || rating.isEmpty() || duration.isEmpty()) {
            // Show error message
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new WorkoutRoutine object
        WorkoutRoutine newWorkout = new WorkoutRoutine(title, rating, duration, "custom"); // 'custom' type for custom workouts

        // Insert the new workout into the database
        WorkoutDao workoutDao = new WorkoutDao(getContext());
        long id = workoutDao.insertWorkout(newWorkout);

        if (id != -1) {
            Toast.makeText(getContext(), "Workout added successfully", Toast.LENGTH_SHORT).show();

            // Successfully inserted
            if (savedListener != null) {
                savedListener.onWorkoutSaved();
            }
        } else {
            Toast.makeText(getContext(), "Failed to add workout", Toast.LENGTH_SHORT).show();
        }

        dismiss(); // Close the dialog
    }

}
