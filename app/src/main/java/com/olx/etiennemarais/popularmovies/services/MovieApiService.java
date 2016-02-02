package com.olx.etiennemarais.popularmovies.services;

import com.olx.etiennemarais.popularmovies.BuildConfig;
import com.olx.etiennemarais.popularmovies.models.Movie;
import com.olx.etiennemarais.popularmovies.utils.Util;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import rx.Observable;

public class MovieApiService {
    private static final String MOVIE_DB_API_URL = "http://api.themoviedb.org/3/";
    private MoviesApi mMoviesApi;

    public MovieApiService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
                request.addQueryParam("api_key", BuildConfig.MOVIE_DB_API_KEY);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(MOVIE_DB_API_URL)
            .setConverter(Util.getGsonConverter())
            .setRequestInterceptor(requestInterceptor)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

        mMoviesApi = restAdapter.create(MoviesApi.class);
    }

    public MoviesApi getMoviesApi() {
        return mMoviesApi;
    }

    public interface MoviesApi {
        @GET("/discover/movie")
        public Observable<List<Movie>> getMovies();
    }
}
