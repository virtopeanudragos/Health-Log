package com.example.healthlog;

public class Exercise {
    private int exerciseId;
    private int routineId;
    private String name;
    private int sets;
    private int reps;
    private int duration; // in seconds
    private String description;
    private String imageVideoUrl;


    public Exercise(int exerciseId, int routineId, String name, int sets, int reps, int duration, String description, String imageVideoUrl) {
        this.exerciseId = exerciseId;
        this.routineId = routineId;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.description = description;
        this.imageVideoUrl = imageVideoUrl;
    }


    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageVideoUrl() {
        return imageVideoUrl;
    }

    public void setImageVideoUrl(String imageVideoUrl) {
        this.imageVideoUrl = imageVideoUrl;
    }
}
