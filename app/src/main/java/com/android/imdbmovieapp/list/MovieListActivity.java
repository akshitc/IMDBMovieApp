package com.android.imdbmovieapp.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.imdbmovieapp.R;

public class MovieListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.main_frame_layout, new MovieListFragment())
        .commit();
  }
}
