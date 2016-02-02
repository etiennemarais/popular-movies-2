package com.olx.etiennemarais.popularmovies.presenters;

import android.util.Log;

import com.olx.etiennemarais.popularmovies.models.Movie;
import com.olx.etiennemarais.popularmovies.services.MovieApiService;
import com.olx.etiennemarais.popularmovies.views.list.ListFragment;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListPresenter {
    private final static String LOG_TAG = ListPresenter.class.getSimpleName();
    private final ListFragment mListFragment;
    private final MovieApiService mMovieApiService;

    public ListPresenter(ListFragment listFragment, MovieApiService movieApiService) {
        this.mListFragment = listFragment;
        this.mMovieApiService = movieApiService;
    }

    public void loadMovies() {
        Log.i(LOG_TAG, ": Some movies are getting loaded starting");
        mMovieApiService.getMoviesApi()
            .getMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<Movie>>() {
                @Override
                public void onNext(List<Movie> movies) {
                    Log.i(LOG_TAG, ": Some movies are getting loaded onNext()");
                    mListFragment.displayMovies(movies);
                }

                @Override
                public void onCompleted() {
                    Log.i(LOG_TAG, ": Some movies are getting loaded onCompleted()");
                }

                @Override
                public void onError(Throwable e) {
                }
            });
    }
}
