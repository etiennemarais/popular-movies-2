package com.olx.etiennemarais.popularmovies.presenters;

import android.util.Log;

import com.olx.etiennemarais.popularmovies.models.MovieResults;
import com.olx.etiennemarais.popularmovies.services.MovieApiService;
import com.olx.etiennemarais.popularmovies.views.list.ListFragment;
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
        mMovieApiService.getMoviesApi()
            .getMovies()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<MovieResults>() {
                @Override
                public void onNext(MovieResults movieResults) {
                    mListFragment.displayMovies(movieResults);
                }

                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    Log.i(LOG_TAG, ": ONERROR: " + e.toString());
                }
            });
    }
}
