package com.sample.movieapidemo.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sample.movieapidemo.R;
import com.sample.movieapidemo.adapter.MovieAdapter;
import com.sample.movieapidemo.model.Constant;
import com.sample.movieapidemo.model.MovieResponse;
import com.sample.movieapidemo.model.Results;
import com.sample.movieapidemo.service.MovieServiceImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieServiceImpl mService;
    private MovieAdapter mMovieAdapter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configViews();

        mService = new MovieServiceImpl();

        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setTitle("Please wait");
        mDialog.setMessage("Retrieving Movies....");
        mDialog.setCancelable(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);

        makeServiceCall();
    }

    private void makeServiceCall() {

        mDialog.show();

        Call<MovieResponse> movies = mService.getMovieApi().getMovies(Constant.SORT_VALUE, Constant.API_KEY_VALUE);
        movies.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    Results[] results = movieResponse.getResults();
                    for (Results result : results) {
                        mMovieAdapter.addResult(result);
                    }
                }
                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        mMovieAdapter = new MovieAdapter(getLayoutInflater());

        mRecyclerView.setAdapter(mMovieAdapter);
    }
}
