package com.dhinojosac.android.inverttext;

import android.os.Handler;
import android.util.Log;

import com.dhinojosac.android.inverttext.events.MyEvent;
import com.dhinojosac.android.inverttext.libs.EventBus;
import com.dhinojosac.android.inverttext.libs.GreenRobotEventBus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public class RepositoryImpl implements Repository{
    private String result ;
    private static final int TIME = 1000;

    @Override
    public void execute(final String text) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Task(text);
            }
        }, 1000);

    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void postEvent(int type, String error) {
        MyEvent event = new MyEvent();
        event.setEventType(type);
        event.setError(error);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }
    public void postEvent(int type) {
        MyEvent event = new MyEvent();
        event.setEventType(type);
        event.setError(null);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(event);
    }

    public void Task(String text){
        result = "";
        Log.d("TASK-PRE", text);
        for (int i=0; i<text.length(); i++){
            Log.d("TASK-INTER", String.valueOf(text.length()));
            result = result + text.charAt(text.length()-(i+1));
        }

        Log.d("TASK-POST", text);
        postEvent(MyEvent.ON_SUCCESS);
    }


}
