package com.lld.vendingmachine;


public enum Note {
    ONE_DOLLAR(100), FIVE_DOLLARS(500);

    private final int value;

    Note ( int value ) {
        this.value = value;
    }

    public int getValue () {
        return value;
    }
}
