package com.example.protask;

public class Task {
    private String name;
    private String Time;
    private String Date;
    private boolean isCompleted;
    //-------------------------------------------------
    public String getName() {

        return name;
    }
    public String getTime() {

        return Time;
    }
    public String getDate() {

        return Date;
    }
    public boolean isCompleted() {

        return isCompleted;
    }
    //-------------------------------------------------
    public void setName(String name) {

        this.name = name;
    }
    public void setDate(String Date) {

        this.Date = Date;
    }
    public void setTime(String time) {

        Time = time;
    }
    public void setCompleted(boolean completed) {

        isCompleted = completed;
    }

    @Override
    public String toString() {

        return name;
    }

}
