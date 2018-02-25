package com.example.vadym.testtasknorthstudio.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vadym on 21.02.2018.
 */

@Entity
public class DateModel {

    @PrimaryKey
    private long timeMills;
    private String messageText;

    public DateModel() {
    }

    public long getTimeMills() {
        return timeMills;
    }

    public void setTimeMills(long timeMills) {
        this.timeMills = timeMills;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
