package com.markusschaden.homeautomation.airconditioner.domain;

public enum Day {
    MONDAY("Mo"), TUESDAY("Di"), WEDNESDAY("Mi"), THURSDAY("Do"), FRIDAY("Fr"), SATURDAY("Sa"), SUNDAY("So");

    Day(String shortcut) {
        this.shortcut = shortcut;
    }
    private String shortcut;
}
