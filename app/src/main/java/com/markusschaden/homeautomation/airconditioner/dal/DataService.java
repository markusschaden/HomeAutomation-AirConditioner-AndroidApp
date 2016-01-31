package com.markusschaden.homeautomation.airconditioner.dal;

import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;
import com.markusschaden.homeautomation.airconditioner.domain.Day;
import com.markusschaden.homeautomation.airconditioner.domain.Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Markus on 31.01.2016.
 */
public class DataService {

    public static Map<Day, List<CoolingEntry>> getData() {
        Map<Day, List<CoolingEntry>> map = new HashMap<>();

        Random r = new Random();
        for(Day d : Day.values()) {
            List<CoolingEntry> l = new ArrayList<>();
            for(int i=0;i<10+r.nextInt(10);i++) {
                l.add(new CoolingEntry(d, new Time(1 + r.nextInt(23), r.nextInt(60)), new Time(1 + r.nextInt(23), r.nextInt(60)), 10 + r.nextInt(20)));
            }
            map.put(d, l);
        }

        return map;
    }
}
