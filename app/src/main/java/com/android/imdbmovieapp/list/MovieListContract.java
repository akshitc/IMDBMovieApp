package com.android.imdbmovieapp.list;

import com.android.imdbmovieapp.list.models.MovieListResponse;

/**
 * Created by akshit on 07/08/16.
 */
public interface MovieListContract {

  interface View {

    void showLoadingUi();

    void showContentUi();

    void showErrorUi(String message);

    void onMovies(MovieListResponse movieListResponse);

    void openMovieDetail();

  }

  interface Presenter {
    void fetchMovies();

    void onMovieClicked();
  }
}
