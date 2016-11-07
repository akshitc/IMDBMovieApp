package com.android.imdbmovieapp.list;

import com.android.imdbmovieapp.list.models.MovieListItem;
import com.android.imdbmovieapp.list.models.MovieListResponse;
import com.android.imdbmovieapp.service.MovieService;
import com.android.imdbmovieapp.utils.Constant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by akshit on 24/08/16.
 */
public class MovieListPresenterTest {

  @Rule
  public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

  @Mock
  private MovieListContract.View view;

  @Mock
  private MovieService service;

  private MovieListPresenter movieListPresenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    movieListPresenter = new MovieListPresenter(view, service);
  }

  @Test
  public void testFetchMovies_shouldMoveViewToLoadingState() throws Exception {
    when(service.getPopularMovies(Constant.API_KEY)).thenReturn(Observable.just(new MovieListResponse()));
    movieListPresenter.fetchMovies();

    verify(view).showLoadingUi();
  }

  @Test
  public void testFetchMovies_shouldSendDataToTheView() {
    MovieListResponse movieListResponse = new MovieListResponse();
    List<MovieListItem> items = new ArrayList<>();

    MovieListItem item1 = new MovieListItem("Inception", "inception.jpg", "2013");
    MovieListItem item2 = new MovieListItem("Departed", "departed.jpg", "2007");
    items.add(item1);
    items.add(item2);
    movieListResponse.setResults(items);

    when(service.getPopularMovies(Constant.API_KEY)).thenReturn(Observable.just(movieListResponse));

    movieListPresenter.fetchMovies();

    verify(view).onMovies(movieListResponse);
    verify(view).showContentUi();
    verify(view, never()).showErrorUi(null);
  }

  @Test
  public void testFetchMovies_shouldSendErrorToTheView() {
    Throwable error = new RuntimeException();
    Observable<MovieListResponse> movieListResponseObservable = Observable.error(error);
    when(service.getPopularMovies(Constant.API_KEY)).thenReturn(movieListResponseObservable);

    movieListPresenter.fetchMovies();

    verify(view).showErrorUi(error.getMessage());
    verify(view, never()).showContentUi();
  }

  @Test
  public void testOnMovieClicked() throws Exception {
    movieListPresenter.onMovieClicked();

    verify(view).openMovieDetail();
  }
}