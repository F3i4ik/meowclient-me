package me.meow.event.impl;

import me.meow.event.Event;


public class TimerEvent extends Event {
    private float timer;
    private boolean modified;
    public TimerEvent() {

        timer = 1f;
    }

    public float get() {
        return this.timer;
    }

    public void set(float timer) {
        this.modified = true;
        this.timer = timer;
    }

    public boolean isModified() {
        return this.modified;
    }
}
