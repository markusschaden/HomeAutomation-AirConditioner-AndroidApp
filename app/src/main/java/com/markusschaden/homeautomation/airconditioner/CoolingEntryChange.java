package com.markusschaden.homeautomation.airconditioner;

import com.markusschaden.homeautomation.airconditioner.domain.CoolingEntry;

/**
 * Created by Markus on 01.02.2016.
 */
public interface CoolingEntryChange {

    void change(CoolingEntry coolingEntry);
}
