package com.example.healthlog;

public class WorkoutRoutine {
    private String title;
    private String rating;
    private String duration;

    // Constructor
    public WorkoutRoutine(String title, String rating, String duration) {
        this.title = title;
        this.rating = rating;
        this.duration = duration;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
