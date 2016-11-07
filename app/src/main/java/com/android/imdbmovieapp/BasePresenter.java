package com.android.imdbmovieapp;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by akshit on 07/08/16.
 */
public abstract class BasePresenter {

  private CompositeSubscription mCompositeSubscription;

  public void onCreate() {
  }

  public void onPause() {

  }

  public void onResume() {
    configureSubscription();
  }

  private CompositeSubscription configureSubscription() {
    if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
      mCompositeSubscription = new CompositeSubscription();
    }
    return mCompositeSubscription;
  }

  public void onDestroy() {
    unSubscribeAll();
  }

  protected void unSubscribeAll() {
    if (mCompositeSubscription != null) {
      mCompositeSubscription.unsubscribe();
      mCompositeSubscription.clear();
    }
  }

  protected <F> void subscribe(Observable<F> observable, Observer<F> observer) {
    Subscription subscription = observable
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.computation())
        .subscribe(observer);
    configureSubscription().add(subscription);
  }
}
