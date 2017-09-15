package com.snehpandya.popularmovies.data;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.snehpandya.popularmovies.R;
import com.snehpandya.popularmovies.activity.DetailActivity;
import com.snehpandya.popularmovies.databinding.ListItemBinding;
import com.snehpandya.popularmovies.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sneh.pandya on 14/09/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Result> mMoviesList;

    public MoviesAdapter(List<Result> moviesList) {
        mMoviesList = moviesList;
    }

    @BindingAdapter({"android:src"})
    public static void setImage(ImageView imageView, String url) {
        final String IMAGE_URL = "http://image.tmdb.org/t/p/w342";
        Picasso.with(imageView.getContext()).load(IMAGE_URL + url).into(imageView);
        imageView.setAdjustViewBounds(true);
    }

    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new MoviesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MoviesViewHolder holder, int position) {
        holder.setBinding(mMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding mBinding;

        MoviesViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void setBinding(Result result) {
            mBinding.setResult(result);
            mBinding.executePendingBindings();
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("movie", mMoviesList.get(position));
                view.getContext().startActivity(intent);
            });
        }
    }
}
