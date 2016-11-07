package com.android.imdbmovieapp;

import android.app.Application;

import com.android.imdbmovieapp.dependencies.ApplicationComponent;
import com.android.imdbmovieapp.dependencies.ApplicationModule;
import com.android.imdbmovieapp.dependencies.DaggerApplicationComponent;

/**
 * Created by akshit on 07/08/16.
 */
public class IMDBMovieApplication extends Application {
  private ApplicationComponent mApiComponent;

  @Override
  public void onCreate() {
    resolveDependency();
    super.onCreate();
  }

  private void resolveDependency() {
    mApiComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return mApiComponent;
  }
}
