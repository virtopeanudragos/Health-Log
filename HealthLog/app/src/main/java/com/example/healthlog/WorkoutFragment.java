package com.example.healthlog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * Fragment for displaying workout routines.
 * It allows users to switch between custom and explore workout views.
 */
public class WorkoutFragment extends Fragment {

    // UI components
    private Button btnCustom;
    private Button btnExplore;
    private FrameLayout contentFrame;

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        // Initialize and setup UI components
        initializeViews(view);
        setupButtonListeners();

        // Display custom workout content by default
        displayCustomContent();

        return view;
    }

    /**
     * Initializes views by binding them to their respective UI elements in the layout.
     * @param view The inflated view of this fragment.
     */
    private void initializeViews(View view) {
        btnCustom = view.findViewById(R.id.btnCustom);
        btnExplore = view.findViewById(R.id.btnExplore);
        contentFrame = view.findViewById(R.id.content_frame);
    }

    /**
     * Sets up click listeners for the buttons in the fragment.
     */
    private void setupButtonListeners() {
        // Set onClickListener for custom workout button
        btnCustom.setOnClickListener(v -> displayCustomContent());
        // Set onClickListener for explore workout button
        btnExplore.setOnClickListener(v -> displayExploreContent());
    }

    /**
     * Displays the custom workout content.
     * TODO: Implement the logic to display custom content.
     */
    private void displayCustomContent() {
        // Inflate and display the custom layout
        View customView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_workout_custom, null);
        contentFrame.removeAllViews();
        contentFrame.addView(customView);
        setupCustomRecyclerView(customView);

        // Floating action button for adding new custom workouts
        FloatingActionButton fabAddWorkout = customView.findViewById(R.id.fabAddWorkout);
        fabAddWorkout.setOnClickListener(v -> openCustomWorkoutDialog());
    }


    private void openCustomWorkoutDialog() {
        CustomWorkoutDialogFragment dialog = new CustomWorkoutDialogFragment();
        dialog.setOnWorkoutSavedListener(() -> {
            // Refresh the custom workouts list
            displayCustomContent(); // Call this method again to refresh the custom workouts
        });
        dialog.show(getParentFragmentManager(), "CustomWorkoutDialog");
    }


    private void setupCustomRecyclerView(View customView) {
        RecyclerView recyclerView = customView.findViewById(R.id.rvWorkoutCustom);

        // Create an instance of WorkoutDao to access custom workout data from the SQLite database
        WorkoutDao workoutDao = new WorkoutDao(getContext());
        // Fetch the list of custom workouts
        List<WorkoutRoutine> customRoutines = workoutDao.getCustomWorkouts(); // Implement getCustomWorkouts method in WorkoutDao

        WorkoutAdapter adapter = new WorkoutAdapter(customRoutines);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }




    /**
     * Displays the 'EXPLORE' section content.
     * This involves inflating a layout that contains a RecyclerView for displaying workout routines.
     */
    private void displayExploreContent() {
        // Inflate and display the explore layout
        @SuppressLint("InflateParams") View exploreView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_workout_explore, null);
        // Clear any existing views in the content frame
        contentFrame.removeAllViews();
        // Add the explore view to the content frame
        contentFrame.addView(exploreView);
        // Setup the RecyclerView for displaying workouts
        setupExploreRecyclerView(exploreView);
    }

    /**
     * Sets up the RecyclerView used in the 'EXPLORE' section.
     * This includes configuring the adapter and layout manager.
     * @param exploreView The view containing the RecyclerView.
     */
    private void setupExploreRecyclerView(View exploreView) {
        RecyclerView recyclerView = exploreView.findViewById(R.id.rvWorkoutExplore);

        // Create an instance of WorkoutDao to access workout data from the SQLite database
        WorkoutDao workoutDao = new WorkoutDao(getContext());
        // Fetch the list of workouts
        List<WorkoutRoutine> routines = workoutDao.getExploreWorkouts();

        // Create and set an adapter with the fetched workout routines
        WorkoutAdapter adapter = new WorkoutAdapter(routines);
        recyclerView.setAdapter(adapter);
        // Set a LinearLayoutManager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}