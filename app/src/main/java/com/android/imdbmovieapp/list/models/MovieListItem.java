package com.android.imdbmovieapp.list.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akshit on 07/08/16.
 */
public class MovieListItem {

  @Expose
  private String title;

  @Expose
  @SerializedName("backdrop_path")
  private String imagePath;

  @Expose
  @SerializedName("release_date")
  private String releaseDate;

  public MovieListItem(String title, String imagePath, String releaseDate) {
    this.title = title;
    this.imagePath = imagePath;
    this.releaseDate = releaseDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
