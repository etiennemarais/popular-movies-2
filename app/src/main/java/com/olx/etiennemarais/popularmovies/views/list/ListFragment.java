package com.olx.etiennemarais.popularmovies.views.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.olx.etiennemarais.popularmovies.R;
import com.olx.etiennemarais.popularmovies.adapters.MoviesListAdapter;
import com.olx.etiennemarais.popularmovies.models.Movie;
import com.olx.etiennemarais.popularmovies.models.MovieResults;
import com.olx.etiennemarais.popularmovies.presenters.ListPresenter;
import com.olx.etiennemarais.popularmovies.services.MovieApiService;
import java.util.ArrayList;

import butterknife.InjectView;

public class ListFragment extends Fragment {
    private final static String LOG_TAG = ListFragment.class.getSimpleName();
    private Context context;

    @InjectView(R.layout.fragment_list)
    GridView mListViewMovies;

    MoviesListAdapter mMoviesListAdapter;
    ListPresenter mListPresenter;
    MovieApiService mMovieApiService;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ArrayList<Movie> dummyMovies = new ArrayList<Movie>();
        mMoviesListAdapter = new MoviesListAdapter(this.context, dummyMovies);
        mListViewMovies = (GridView) rootView.findViewById(R.id.moviesGridView);
        mListViewMovies.setAdapter(mMoviesListAdapter);

        mMovieApiService = new MovieApiService();
        mListPresenter = new ListPresenter(this, mMovieApiService);
        mListPresenter.loadMovies();

        return rootView;
    }

    public void displayMovies(MovieResults movieResults) {
        mMoviesListAdapter.clear();
        mMoviesListAdapter.addAll(movieResults.results);
        mMoviesListAdapter.notifyDataSetInvalidated();
    }
}
