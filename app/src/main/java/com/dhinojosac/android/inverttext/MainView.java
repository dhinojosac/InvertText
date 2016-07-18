package com.dhinojosac.android.inverttext;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public interface MainView {
    void showProgress();
    void hideProgress();

    void enableInputs();
    void disableInputs();

    void enableCopy();
    void disableCopy();

    void resetInput();

    void showAnswer(String text);
}
