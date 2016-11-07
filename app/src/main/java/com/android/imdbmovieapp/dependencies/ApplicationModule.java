package com.android.imdbmovieapp.dependencies;

import android.app.Application;
import android.support.annotation.NonNull;

import com.android.imdbmovieapp.utils.PicassoImageLoader;
import com.android.imdbmovieapp.utils.QualityMattersImageLoader;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApplicationModule {

  @NonNull
  private final Application application;

  public ApplicationModule(@NonNull Application application) {
    this.application = application;
  }

  @Provides
  @NonNull
  @Singleton
  public Application provideIMDBMovieApp() {
    return application;
  }

  @Provides
  @NonNull
  @Singleton
  public Picasso providePicasso(@NonNull Application application, @NonNull OkHttpClient okHttpClient) {
    return new Picasso.Builder(application)
        .downloader(new OkHttp3Downloader(okHttpClient))
        .build();
  }

  @Provides
  @NonNull
  @Singleton
  public QualityMattersImageLoader provideImageLoader(@NonNull Picasso picasso) {
    return new PicassoImageLoader(picasso);
  }
}
