package com.example.healthlog;

public class UserPerformance {
    private int performanceId;
    private int exerciseId;
    private String date;
    private int setsCompleted;
    private int repsPerSet;
    private float weightUsed;


    public UserPerformance(int performanceId, int exerciseId, String date, int setsCompleted, int repsPerSet, float weightUsed) {
        this.performanceId = performanceId;
        this.exerciseId = exerciseId;
        this.date = date;
        this.setsCompleted = setsCompleted;
        this.repsPerSet = repsPerSet;
        this.weightUsed = weightUsed;
    }


    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSetsCompleted() {
        return setsCompleted;
    }

    public void setSetsCompleted(int setsCompleted) {
        this.setsCompleted = setsCompleted;
    }

    public int getRepsPerSet() {
        return repsPerSet;
    }

    public void setRepsPerSet(int repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    public float getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(float weightUsed) {
        this.weightUsed = weightUsed;
    }
}
