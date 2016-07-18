package com.dhinojosac.android.inverttext;


import com.dhinojosac.android.inverttext.events.MyEvent;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public interface Presenter {
    void onCreate();
    void onDestroy();

    void sendText(String text);


    void onEventMainThread(MyEvent event);
}
