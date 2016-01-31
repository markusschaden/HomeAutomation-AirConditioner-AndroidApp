package com.markusschaden.homeautomation.airconditioner.domain;

import lombok.Data;

/**
 * Created by Markus on 31.01.2016.
 */
@Data
public class CoolingEntry {

    int temperature;
    Time startTime;
    Time stopTime;
    boolean enabled;
    Day day;

    public CoolingEntry(Day day, Time start, Time end, int temperature) {
        this.day = day;
        this.startTime = start;
        this.stopTime = end;
        this.temperature = temperature;
    }
}
