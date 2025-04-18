package org.keyin.workoutclasses;

public class WorkoutClass {
    private int classId;
    private String className;
    private String trainerName;
    private int durationMinutes;
    private String schedule;

// constructors    
    public WorkoutClass() {}

// Constructor for adding new classes    
    public WorkoutClass (String className, String trainerName, int durationMinutes, String schedule) {
        this.className = className;
        this.trainerName = trainerName;
        this.durationMinutes = durationMinutes;
        this.schedule = schedule;
    }

    public WorkoutClass( int classId, String className, String trainerName, int durationMinutes) {
        this.classId = classId;
        this.className = className;
        this.trainerName = trainerName;
        this.durationMinutes = durationMinutes;        
    }

// Getters/Setters
    public int getClassId() {
        return classId;
    }

    public void setClassId (int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrainerName () {
        return trainerName;
    }

    public void setTrainerName (String trainerName) {
        this.trainerName = trainerName;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
