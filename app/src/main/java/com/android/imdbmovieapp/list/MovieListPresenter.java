package com.android.imdbmovieapp.list;

import com.android.imdbmovieapp.BasePresenter;
import com.android.imdbmovieapp.list.models.MovieListResponse;
import com.android.imdbmovieapp.service.MovieService;

import rx.Observer;


/**
 * Created by akshit on 07/08/16.
 */
public class MovieListPresenter extends BasePresenter implements MovieListContract.Presenter, Observer<MovieListResponse> {

    private static final String apiKey = "07f045b8b06f4e63d1442f147ec919d0";
    private MovieListContract.View view;
    private MovieService service;

    public MovieListPresenter(MovieListContract.View view, MovieService service) {
        this.view = view;
        this.service = service;
    }

    @Override
    public void fetchMovies() {
        unSubscribeAll();
        subscribe(service.getPopularMovies(apiKey), MovieListPresenter.this);
        view.showLoadingUi();
    }

    @Override
    public void onMovieClicked() {
        view.openMovieDetail();
    }

    @Override
    public void onCompleted() {
        view.showContentUi();
    }

    @Override
    public void onError(Throwable e) {
        view.showErrorUi(e.getMessage());
    }

    @Override
    public void onNext(MovieListResponse movieListResponse) {
        view.onMovies(movieListResponse);
    }
}
