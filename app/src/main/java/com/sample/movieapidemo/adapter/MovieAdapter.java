package com.sample.movieapidemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sample.movieapidemo.R;
import com.sample.movieapidemo.model.Constant;
import com.sample.movieapidemo.model.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 3/28/2016
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private final LayoutInflater mInflater;
    private List<Results> mResultsList;

    public MovieAdapter(LayoutInflater inflater) {
        mInflater = inflater;
        mResultsList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Results currentResult = mResultsList.get(position);
        holder.title.setText(currentResult.getTitle());
        holder.description.setText(currentResult.getOverview());
        Glide.with(holder.itemView.getContext()).load(Constant.IMAGE_BASE_URL + currentResult.getBackdropPath()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }

    public void addResult(Results results) {
        mResultsList.add(results);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView thumbnail;
        private TextView title, description;

        public Holder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(Holder.this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
