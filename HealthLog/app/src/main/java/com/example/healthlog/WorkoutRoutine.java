package com.example.healthlog;

public class WorkoutRoutine {
    private String title;
    private String rating;
    private String duration;
    private String type;

    public WorkoutRoutine(String title, String rating, String duration, String type) {
        this.title = title;
        this.rating = rating;
        this.duration = duration;
        this.type = type;
    }


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
