package com.dhinojosac.android.inverttext.events;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public class MyEvent {
    public static final int ON_SUCCESS = 1;
    public static final int O_ERROR = 0;

    public int eventType;
    private String error;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
