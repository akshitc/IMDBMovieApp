package com.android.imdbmovieapp.list.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by akshit on 07/08/16.
 */
public class MovieListResponse {

  @Expose
  private List<MovieListItem> results;

  public List<MovieListItem> getResults() {
    return results;
  }

  public void setResults(List<MovieListItem> results) {
    this.results = results;
  }
}
