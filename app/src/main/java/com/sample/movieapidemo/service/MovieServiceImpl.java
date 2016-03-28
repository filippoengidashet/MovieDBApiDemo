package com.sample.movieapidemo.service;

import com.sample.movieapidemo.model.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 3/28/2016
 */
public class MovieServiceImpl implements MovieService {

    private MovieApi mResponseApi;

    @Override
    public MovieApi getMovieApi() {
        if (mResponseApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mResponseApi = retrofit.create(MovieApi.class);
        }
        return mResponseApi;
    }
}
