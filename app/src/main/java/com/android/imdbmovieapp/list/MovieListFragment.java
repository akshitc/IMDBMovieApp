package com.android.imdbmovieapp.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.imdbmovieapp.IMDBMovieApplication;
import com.android.imdbmovieapp.R;
import com.android.imdbmovieapp.list.models.MovieListResponse;
import com.android.imdbmovieapp.utils.QualityMattersImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by akshit on 07/08/16.
 */
public class MovieListFragment extends Fragment implements MovieListContract.View {
  @BindView(R.id.items_loading_ui)
  View loadingUiView;

  @BindView(R.id.items_loading_error_ui)
  View errorUiView;

  @BindView(R.id.items_content_ui)
  RecyclerView contentUiRecyclerView;

  MovieItemsAdapter movieItemsAdapter;

  @Inject
  MovieListPresenter movieListPresenter;

  @Inject
  QualityMattersImageLoader imageLoader;

  @SuppressWarnings("NullableProblems")
  @NonNull
  private Unbinder unbinder;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DaggerMovieListComponent.builder()
        .applicationComponent(((IMDBMovieApplication) getActivity().getApplication()).getApplicationComponent())
        .movieListModule(new MovieListModule(this))
        .build()
        .inject(this);

    movieListPresenter.onCreate();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

    unbinder = ButterKnife.bind(this, rootView);
    contentUiRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    movieItemsAdapter = new MovieItemsAdapter(getActivity().getLayoutInflater(), imageLoader);
    contentUiRecyclerView.setAdapter(movieItemsAdapter);

    setRetainInstance(true);

    return rootView;
  }

  @Override
  public void onResume() {
    super.onResume();
    movieListPresenter.onResume();
    movieListPresenter.fetchMovies();
  }

  @Override
  public void onMovies(MovieListResponse movieListResponse) {
    movieItemsAdapter.setData(movieListResponse.getResults());
  }


  @Override
  public void openMovieDetail() {

  }

  @Override
  public void showLoadingUi() {
    loadingUiView.setVisibility(VISIBLE);
    errorUiView.setVisibility(GONE);
    contentUiRecyclerView.setVisibility(GONE);
  }

  @Override
  public void showErrorUi(String message) {
    loadingUiView.setVisibility(GONE);
    errorUiView.setVisibility(VISIBLE);
    contentUiRecyclerView.setVisibility(GONE);
    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showContentUi() {
    loadingUiView.setVisibility(GONE);
    errorUiView.setVisibility(GONE);
    contentUiRecyclerView.setVisibility(VISIBLE);
  }

  @OnClick(R.id.items_loading_error_try_again_button)
  void onTryAgainButtonClick() {
    movieListPresenter.fetchMovies();
  }

  @Override
  public void onDestroyView() {
    if (unbinder != null) {
      unbinder.unbind();
    }

    super.onDestroyView();
  }
}
