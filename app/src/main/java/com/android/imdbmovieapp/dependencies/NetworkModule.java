package com.android.imdbmovieapp.dependencies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by akshit on 07/08/16.
 */
@Module
public class NetworkModule {

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
    return new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build();
  }
}
