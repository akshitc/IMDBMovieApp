package com.android.imdbmovieapp.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.imdbmovieapp.R;
import com.android.imdbmovieapp.list.models.MovieListItem;
import com.android.imdbmovieapp.utils.QualityMattersImageLoader;

import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Created by akshit on 08/08/16.
 */
public class MovieItemsAdapter extends RecyclerView.Adapter<MovieItemViewHolder> {
  private final LayoutInflater layoutInflater;
  private final QualityMattersImageLoader imageLoader;
  private List<MovieListItem> items;

  public MovieItemsAdapter(LayoutInflater layoutInflater, QualityMattersImageLoader imageLoader) {
    this.layoutInflater = layoutInflater;
    this.imageLoader = imageLoader;
  }

  @Override
  public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MovieItemViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false));
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void setData(@NonNull List<MovieListItem> items) {
    this.items = unmodifiableList(items); // Prevent possible side-effects.
    notifyDataSetChanged();
  }

  @Override
  public void onBindViewHolder(MovieItemViewHolder viewHolder, int position) {
    viewHolder.bind(items.get(position), imageLoader);
  }
}
