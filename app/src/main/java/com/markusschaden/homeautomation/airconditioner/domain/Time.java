package com.markusschaden.homeautomation.airconditioner.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Markus on 31.01.2016.
 */
@Data
public class Time implements Serializable {
    int hour;
    int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public String formatted() {
        StringBuilder sb = new StringBuilder();
        if(hour < 10) sb.append("0");
        sb.append(hour);
        sb.append(":");
        if(minute < 10) sb.append("0");
        sb.append(minute);
        return sb.toString();
    }
}