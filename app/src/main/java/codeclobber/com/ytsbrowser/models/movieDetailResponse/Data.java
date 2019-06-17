package codeclobber.com.ytsbrowser.models.movieDetailResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import codeclobber.com.ytsbrowser.models.Movie;

public class Data implements Parcelable {

    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            instance.movie = ((Movie) in.readValue((Movie.class.getClassLoader())));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    };
    @SerializedName("movie")
    @Expose
    private Movie movie;

    /**
     * @return The movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * @param movie The movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(movie);
    }

    public int describeContents() {
        return 0;
    }

}
