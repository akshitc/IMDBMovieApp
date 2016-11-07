package com.android.imdbmovieapp.list;

import com.android.imdbmovieapp.dependencies.ApplicationComponent;
import com.android.imdbmovieapp.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by akshit on 07/08/16.
 */
@FragmentScoped
@Component(modules = MovieListModule.class, dependencies = ApplicationComponent.class)
public interface MovieListComponent {
  void inject(MovieListFragment fragment);
}
