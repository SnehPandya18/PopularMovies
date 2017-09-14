package com.snehpandya.popularmovies.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.model.Movies;

import java.util.List;

/**
 * Created by sneh.pandya on 14/09/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movies> mMoviesList;

    public MoviesAdapter(List<Movies> moviesList) {
        mMoviesList = moviesList;
    }

    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MoviesViewHolder holder, int position) {
        holder.mTextView.setText(mMoviesList.get(position).getMovie());
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        MoviesViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_title);
        }
    }
}
