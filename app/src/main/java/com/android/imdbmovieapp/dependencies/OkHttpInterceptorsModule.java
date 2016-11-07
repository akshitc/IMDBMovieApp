package com.android.imdbmovieapp.dependencies;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Provides OkHttp interceptors for debug build.
 */
@Module
public class OkHttpInterceptorsModule {

  @Provides
  @Singleton
  @NonNull
  public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    return interceptor;
  }
}
