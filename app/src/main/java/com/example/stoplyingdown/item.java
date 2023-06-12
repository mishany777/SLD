package com.example.stoplyingdown;

import android.widget.Button;

public class item {
    int background;
    String activityTitle;
    String description;
    Boolean isFinished;

    public item(int background, String activityTitle, String description, Boolean isFinished) {
        this.background = background;
        this.activityTitle = activityTitle;
        this.description = description;
        this.isFinished = isFinished;
    }

    public int getBackground() {
        return background;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public void buttonClick(){
        return;
    }
}
