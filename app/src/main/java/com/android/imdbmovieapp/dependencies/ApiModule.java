package com.android.imdbmovieapp.dependencies;

import com.android.imdbmovieapp.service.MovieService;
import com.android.imdbmovieapp.utils.Constant;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akshit on 07/08/16.
 */
@Module
public class ApiModule {


  @Provides
  @Singleton
  @Named("movie_url")
  String provideMovieBaseUrl() {
    return Constant.MOVIE_BASE_URL;
  }

  @Provides
  @Singleton
  RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJavaCallAdapterFactory.create();
  }

  @Provides
  @Singleton
  @Named("Gson")
  Converter.Factory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  @Named("Jackson")
  Converter.Factory provideJacksonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  MovieService provideMovieService(@Named("movie_url") String baseUrl, @Named("Gson") Converter.Factory converterFactory, RxJavaCallAdapterFactory callAdapterFactory, OkHttpClient client) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()
        .create(MovieService.class);
  }

}
