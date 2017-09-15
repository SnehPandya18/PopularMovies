package com.snehpandya.popularmovies.viewholder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.snehpandya.popularmovies.activity.DetailActivity;
import com.snehpandya.popularmovies.databinding.ListItemBinding;
import com.snehpandya.popularmovies.model.Result;

/**
 * Created by sneh.pandya on 15/09/17.
 */

public class MoviesViewHolder extends RecyclerView.ViewHolder {

    ListItemBinding mBinding;

    public MoviesViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public void setBinding(Result result) {
        mBinding.setResult(result);
        mBinding.executePendingBindings();
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("movie", result);
            view.getContext().startActivity(intent);
        });
    }
}