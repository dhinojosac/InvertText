package com.dhinojosac.android.inverttext;

import android.content.Intent;

import com.dhinojosac.android.inverttext.events.MyEvent;
import com.dhinojosac.android.inverttext.libs.EventBus;
import com.dhinojosac.android.inverttext.libs.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by negro-PC on 05-Jul-16.
 */
public class PresenterImpl implements Presenter{
    private MainView view;
    private Interactor interactor;
    private EventBus eventBus;
    String textPresenter;

    public PresenterImpl(MainView view) {
        this.view = view;
        this.interactor = new InteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance(); //singleton
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void sendText(String text) {
        if(view != null){
            view.disableInputs();
            view.showProgress();
        }
        interactor.sendText(text);
    }



    // Se subscribe a EventBus
    @Subscribe
    @Override
    public void onEventMainThread(MyEvent event) {
        switch (event.getEventType()){
            case MyEvent.ON_SUCCESS:
                    onSuccessReturn();
                break;
            case MyEvent.O_ERROR:
                    onErrorReturn(event.getError());
                break;
        }
    }

    // Metodos para el manejo de los eventos en caso de ser onSuccess o onError
    private void onSuccessReturn() {
        if (view != null){
            textPresenter = interactor.getText();
            view.enableInputs();
            view.hideProgress();
            view.resetInput();
            view.enableCopy();
            view.showAnswer(textPresenter);
        }
    }

    private void onErrorReturn(String error) {
        if (view != null){
            view.resetInput();
            textPresenter = error;
            view.enableInputs();
            view.hideProgress();
            view.showAnswer(error);
            view.disableCopy();
        }
    }
}
