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

import java.nio.charset.StandardCharsets;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class WorkoutFragment extends Fragment {

    // UI elements
    private Button btnCustom;
    private Button btnExplore;
    private FrameLayout contentFrame;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        // Initialize UI components
        initializeViews(view);
        // Set up button click listeners
        setupButtonListeners();

        return view;
    }

    /**
     * Initializes views by binding them to their respective UI elements.
     */
    private void initializeViews(View view) {
        btnCustom = view.findViewById(R.id.btnCustom);
        btnExplore = view.findViewById(R.id.btnExplore);
        contentFrame = view.findViewById(R.id.content_frame);
    }

    /**
     * Sets up click listeners for buttons.
     */
    private void setupButtonListeners() {
        // Display custom content when the 'CUSTOM' button is clicked
        btnCustom.setOnClickListener(v -> displayCustomContent());
        // Display explore content when the 'EXPLORE' button is clicked
        btnExplore.setOnClickListener(v -> displayExploreContent());
    }

    /**
     * Logic to display custom content.
     */
    private void displayCustomContent() {
        // TODO: Implement the logic to display custom content
    }

    /**
     * Displays the 'EXPLORE' section content.
     */
    private void displayExploreContent() {
        // Inflate and display the explore layout
        @SuppressLint("InflateParams") View exploreView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_workout_explore, null);
        // Clear any existing views in the container
        contentFrame.removeAllViews();
        // Add the explore view to the container
        contentFrame.addView(exploreView);
        // Initialize the RecyclerView within the explore view
        setupExploreRecyclerView(exploreView);
    }

    /**
     * Returns a list of WorkoutRoutine objects parsed from the JSON file.
     */
    private List<WorkoutRoutine> getWorkoutRoutines() {
        // Load JSON data from the assets folder
        String json = loadJSONFromAsset("workouts.json");
        // Parse the JSON data into WorkoutRoutine objects
        return parseWorkouts(json);
    }

    /**
     * Loads JSON data from the specified assets file.
     */
    private String loadJSONFromAsset(String filename) {
        try (InputStream is = requireActivity().getAssets().open(filename)) {
            // Read the input stream into a byte array
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            // Convert byte array into a string
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // Log and handle the exception
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Parses the JSON data into a list of WorkoutRoutine objects.
     */
    private List<WorkoutRoutine> parseWorkouts(String json) {
        // Return an empty list if JSON data is null
        if (json == null) {
            return new ArrayList<>();
        }
        List<WorkoutRoutine> workoutList = new ArrayList<>();
        try {
            // Parse the JSON array
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                // Parse each JSON object into a WorkoutRoutine
                JSONObject jsonObject = array.getJSONObject(i);
                workoutList.add(new WorkoutRoutine(
                        jsonObject.getString("title"),
                        jsonObject.getString("rating"),
                        jsonObject.getString("duration")
                ));
            }
        } catch (JSONException e) {
            // Log and handle the exception
            e.printStackTrace();
        }
        return workoutList;
    }

    /**
     * Sets up the RecyclerView in the 'EXPLORE' section.
     */
    private void setupExploreRecyclerView(View exploreView) {
        // Find the RecyclerView and bind it to the adapter
        RecyclerView recyclerView = exploreView.findViewById(R.id.rvWorkoutExplore);
        List<WorkoutRoutine> routines = getWorkoutRoutines();
        recyclerView.setAdapter(new WorkoutAdapter(routines));
        // Set a LinearLayoutManager to the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}