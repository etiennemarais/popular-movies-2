package com.olx.etiennemarais.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movie implements Parcelable {
    private final static String LOG_TAG = Movie.class.getSimpleName();
    public static final String ORDER_POPULARITY_DESC = "popularity.desc";
    public static final String ORDER_RATING_DESC = "vote_average.desc";
    private static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/";
    private static final String BACKDROP_DEFAULT_SIZE = "w1280";
    private static final String COVER_DEFAULT_SIZE = "w185";
    public static final String MOVIE = "com.olx.etiennemarais.popularmovies.Movies.Movie";

    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("adult")
    @Expose
    public boolean adult;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("popularity")
    @Expose
    public double popularity;
    @SerializedName("vote_count")
    @Expose
    public int voteCount;
    @SerializedName("video")
    @Expose
    public boolean video;
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;

    public String getPosterPath() {
        return BASE_IMAGE_PATH + COVER_DEFAULT_SIZE + posterPath;
    }

    public String getBackdropPath() {
        return BASE_IMAGE_PATH + BACKDROP_DEFAULT_SIZE + this.backdropPath;
    }

    @Override
    public String toString() {
        return "Movie{"
                + " title=" + title
                + " id=" + id
                + " backdrop=" + this.getBackdropPath()
                + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.originalTitle);
        dest.writeString(this.backdropPath);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.releaseDate);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.originalTitle = in.readString();
        this.backdropPath = in.readString();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.popularity = in.readFloat();
        this.voteAverage = in.readFloat();
        this.releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
