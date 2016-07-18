package com.dhinojosac.android.inverttext.libs;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public class GreenRobotEventBus implements EventBus {
    private org.greenrobot.eventbus.EventBus eventBust;

    // Singleton
    private static final class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    public GreenRobotEventBus() {
        this.eventBust = new org.greenrobot.eventbus.EventBus();
    }

    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void register(Object subscriber) {
        eventBust.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBust.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBust.post(event);
    }
}
