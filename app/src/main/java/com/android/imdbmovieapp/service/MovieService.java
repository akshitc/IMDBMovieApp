package com.android.imdbmovieapp.service;

import com.android.imdbmovieapp.list.models.MovieListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by akshit on 07/08/16.
 */
public interface MovieService {
    @GET("/3/movie/popular")
    Observable<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey);
}
