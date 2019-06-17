package codeclobber.com.ytsbrowser.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {

    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            instance.movieCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.limit = ((int) in.readValue((int.class.getClassLoader())));
            instance.pageNumber = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.movies, (Movie.class.getClassLoader()));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    };
    @SerializedName("movie_count")
    @Expose
    private int movieCount;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("page_number")
    @Expose
    private int pageNumber;
    @SerializedName("movies")
    @Expose
    private List<Movie> movies = new ArrayList<Movie>();

    /**
     * @return The movieCount
     */
    public int getMovieCount() {
        return movieCount;
    }

    /**
     * @param movieCount The movie_count
     */
    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    /**
     * @return The limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit The limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return The pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber The page_number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return The movies
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * @param movies The movies
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(movieCount);
        dest.writeValue(limit);
        dest.writeValue(pageNumber);
        dest.writeList(movies);
    }

    public int describeContents() {
        return 0;
    }

}
