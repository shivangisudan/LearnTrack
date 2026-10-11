package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    // constructor
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
        }
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "", batch);
    }

    //getters
    public String getBatch() {
        return batch;
    }
    public boolean isActive() {
        return active;
    }

    // setters
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    //display info
    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Batch: " + batch + ")";
    }
}
