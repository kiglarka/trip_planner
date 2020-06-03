package com.codecool.tripplanner.main;

import android.view.View;

/**
 * Responsible for handling actions from the view and updating the UI as required
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView mView;

    public MainPresenter(MainContract.MvpView mView) {
        this.mView = mView;
    }
}
