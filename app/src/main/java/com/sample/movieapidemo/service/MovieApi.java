package com.sample.movieapidemo.service;

import com.sample.movieapidemo.model.Constant;
import com.sample.movieapidemo.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 3/28/2016
 */
public interface MovieApi {

    @GET("/3/discover/movie")
    Call<MovieResponse> getMovies(@Query(Constant.SORT_KEY) String sort, @Query(Constant.API_KEY_KEY) String apiKey);
}
