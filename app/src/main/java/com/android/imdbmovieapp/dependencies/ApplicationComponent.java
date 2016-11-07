package com.android.imdbmovieapp.dependencies;

import com.android.imdbmovieapp.service.MovieService;
import com.android.imdbmovieapp.utils.QualityMattersImageLoader;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by akshit on 09/08/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, OkHttpInterceptorsModule.class, NetworkModule.class, ApiModule.class})
public interface ApplicationComponent {

  MovieService movieService();

  QualityMattersImageLoader imageLoader();
}
