package com.dhinojosac.android.inverttext.libs;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
