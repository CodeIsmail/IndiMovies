package com.idealorb.indimovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.idealorb.indimovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class HeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.movies_header_sort_tv)
    TextView headerTextView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
