package com.android.imdbmovieapp.list;

import com.android.imdbmovieapp.service.MovieService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by akshit on 07/08/16.
 */
@Module
public class MovieListModule {
  private MovieListContract.View view;

  public MovieListModule(MovieListContract.View view) {
    this.view = view;
  }

  @Provides
  MovieListPresenter provideMovieListPresenter(MovieService service) {
    return new MovieListPresenter(view, service);
  }
}
