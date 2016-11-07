package com.android.imdbmovieapp.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.imdbmovieapp.R;
import com.android.imdbmovieapp.list.models.MovieListItem;
import com.android.imdbmovieapp.utils.Constant;
import com.android.imdbmovieapp.utils.QualityMattersImageLoader;

/**
 * Created by akshit on 08/08/16.
 */
public class MovieItemViewHolder extends RecyclerView.ViewHolder {

  private final TextView titleTextView;
  private final ImageView imageView;

  public MovieItemViewHolder(View itemView) {
    super(itemView);
    titleTextView = (TextView) itemView.findViewById(R.id.title);
    imageView = (ImageView) itemView.findViewById(R.id.image);
  }

  public void bind(MovieListItem movieListItem, QualityMattersImageLoader imageLoader) {
    titleTextView.setText(movieListItem.getTitle());
    String url = Constant.IMAGE_HOST + movieListItem.getImagePath();
    imageLoader.downloadInto(url, imageView);
  }
}
