package codeclobber.com.ytsbrowser.models.movieDetailResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import codeclobber.com.ytsbrowser.models.Torrent;

public class Movie implements Parcelable {

    public final static Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            Movie instance = new Movie();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.imdbCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.titleEnglish = ((String) in.readValue((String.class.getClassLoader())));
            instance.titleLong = ((String) in.readValue((String.class.getClassLoader())));
            instance.slug = ((String) in.readValue((String.class.getClassLoader())));
            instance.year = ((int) in.readValue((int.class.getClassLoader())));
            instance.rating = ((double) in.readValue((double.class.getClassLoader())));
            instance.runtime = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.genres, (java.lang.String.class.getClassLoader()));
            instance.downloadCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.likeCount = ((int) in.readValue((int.class.getClassLoader())));
            instance.descriptionIntro = ((String) in.readValue((String.class.getClassLoader())));
            instance.descriptionFull = ((String) in.readValue((String.class.getClassLoader())));
            instance.ytTrailerCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.language = ((String) in.readValue((String.class.getClassLoader())));
            instance.mpaRating = ((String) in.readValue((String.class.getClassLoader())));
            instance.backgroundImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.backgroundImageOriginal = ((String) in.readValue((String.class.getClassLoader())));
            instance.smallCoverImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.mediumCoverImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.largeCoverImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.mediumScreenshotImage1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mediumScreenshotImage2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.mediumScreenshotImage3 = ((String) in.readValue((String.class.getClassLoader())));
            instance.largeScreenshotImage1 = ((String) in.readValue((String.class.getClassLoader())));
            instance.largeScreenshotImage2 = ((String) in.readValue((String.class.getClassLoader())));
            instance.largeScreenshotImage3 = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.cast, (Cast.class.getClassLoader()));
            in.readList(instance.torrents, (Torrent.class.getClassLoader()));
            instance.dateUploaded = ((String) in.readValue((String.class.getClassLoader())));
            instance.dateUploadedUnix = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    };
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_english")
    @Expose
    private String titleEnglish;
    @SerializedName("title_long")
    @Expose
    private String titleLong;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("runtime")
    @Expose
    private int runtime;
    @SerializedName("genres")
    @Expose
    private List<String> genres = new ArrayList<String>();
    @SerializedName("download_count")
    @Expose
    private int downloadCount;
    @SerializedName("like_count")
    @Expose
    private int likeCount;
    @SerializedName("description_intro")
    @Expose
    private String descriptionIntro;
    @SerializedName("description_full")
    @Expose
    private String descriptionFull;
    @SerializedName("yt_trailer_code")
    @Expose
    private String ytTrailerCode;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("mpa_rating")
    @Expose
    private String mpaRating;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("background_image_original")
    @Expose
    private String backgroundImageOriginal;
    @SerializedName("small_cover_image")
    @Expose
    private String smallCoverImage;
    @SerializedName("medium_cover_image")
    @Expose
    private String mediumCoverImage;
    @SerializedName("large_cover_image")
    @Expose
    private String largeCoverImage;
    @SerializedName("medium_screenshot_image1")
    @Expose
    private String mediumScreenshotImage1;
    @SerializedName("medium_screenshot_image2")
    @Expose
    private String mediumScreenshotImage2;
    @SerializedName("medium_screenshot_image3")
    @Expose
    private String mediumScreenshotImage3;
    @SerializedName("large_screenshot_image1")
    @Expose
    private String largeScreenshotImage1;
    @SerializedName("large_screenshot_image2")
    @Expose
    private String largeScreenshotImage2;
    @SerializedName("large_screenshot_image3")
    @Expose
    private String largeScreenshotImage3;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = new ArrayList<Cast>();
    @SerializedName("torrents")
    @Expose
    private List<Torrent> torrents = new ArrayList<Torrent>();
    @SerializedName("date_uploaded")
    @Expose
    private String dateUploaded;
    @SerializedName("date_uploaded_unix")
    @Expose
    private int dateUploadedUnix;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The imdbCode
     */
    public String getImdbCode() {
        return imdbCode;
    }

    /**
     * @param imdbCode The imdb_code
     */
    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The titleEnglish
     */
    public String getTitleEnglish() {
        return titleEnglish;
    }

    /**
     * @param titleEnglish The title_english
     */
    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    /**
     * @return The titleLong
     */
    public String getTitleLong() {
        return titleLong;
    }

    /**
     * @param titleLong The title_long
     */
    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    /**
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return The year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year The year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return The rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return The runtime
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * @param runtime The runtime
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * @return The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * @param genres The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * @return The downloadCount
     */
    public int getDownloadCount() {
        return downloadCount;
    }

    /**
     * @param downloadCount The download_count
     */
    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * @return The likeCount
     */
    public int getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount The like_count
     */
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return The descriptionIntro
     */
    public String getDescriptionIntro() {
        return descriptionIntro;
    }

    /**
     * @param descriptionIntro The description_intro
     */
    public void setDescriptionIntro(String descriptionIntro) {
        this.descriptionIntro = descriptionIntro;
    }

    /**
     * @return The descriptionFull
     */
    public String getDescriptionFull() {
        return descriptionFull;
    }

    /**
     * @param descriptionFull The description_full
     */
    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    /**
     * @return The ytTrailerCode
     */
    public String getYtTrailerCode() {
        return ytTrailerCode;
    }

    /**
     * @param ytTrailerCode The yt_trailer_code
     */
    public void setYtTrailerCode(String ytTrailerCode) {
        this.ytTrailerCode = ytTrailerCode;
    }

    /**
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The mpaRating
     */
    public String getMpaRating() {
        return mpaRating;
    }

    /**
     * @param mpaRating The mpa_rating
     */
    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

    /**
     * @return The backgroundImage
     */
    public String getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * @param backgroundImage The background_image
     */
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * @return The backgroundImageOriginal
     */
    public String getBackgroundImageOriginal() {
        return backgroundImageOriginal;
    }

    /**
     * @param backgroundImageOriginal The background_image_original
     */
    public void setBackgroundImageOriginal(String backgroundImageOriginal) {
        this.backgroundImageOriginal = backgroundImageOriginal;
    }

    /**
     * @return The smallCoverImage
     */
    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    /**
     * @param smallCoverImage The small_cover_image
     */
    public void setSmallCoverImage(String smallCoverImage) {
        this.smallCoverImage = smallCoverImage;
    }

    /**
     * @return The mediumCoverImage
     */
    public String getMediumCoverImage() {
        return mediumCoverImage;
    }

    /**
     * @param mediumCoverImage The medium_cover_image
     */
    public void setMediumCoverImage(String mediumCoverImage) {
        this.mediumCoverImage = mediumCoverImage;
    }

    /**
     * @return The largeCoverImage
     */
    public String getLargeCoverImage() {
        return largeCoverImage;
    }

    /**
     * @param largeCoverImage The large_cover_image
     */
    public void setLargeCoverImage(String largeCoverImage) {
        this.largeCoverImage = largeCoverImage;
    }

    /**
     * @return The mediumScreenshotImage1
     */
    public String getMediumScreenshotImage1() {
        return mediumScreenshotImage1;
    }

    /**
     * @param mediumScreenshotImage1 The medium_screenshot_image1
     */
    public void setMediumScreenshotImage1(String mediumScreenshotImage1) {
        this.mediumScreenshotImage1 = mediumScreenshotImage1;
    }

    /**
     * @return The mediumScreenshotImage2
     */
    public String getMediumScreenshotImage2() {
        return mediumScreenshotImage2;
    }

    /**
     * @param mediumScreenshotImage2 The medium_screenshot_image2
     */
    public void setMediumScreenshotImage2(String mediumScreenshotImage2) {
        this.mediumScreenshotImage2 = mediumScreenshotImage2;
    }

    /**
     * @return The mediumScreenshotImage3
     */
    public String getMediumScreenshotImage3() {
        return mediumScreenshotImage3;
    }

    /**
     * @param mediumScreenshotImage3 The medium_screenshot_image3
     */
    public void setMediumScreenshotImage3(String mediumScreenshotImage3) {
        this.mediumScreenshotImage3 = mediumScreenshotImage3;
    }

    /**
     * @return The largeScreenshotImage1
     */
    public String getLargeScreenshotImage1() {
        return largeScreenshotImage1;
    }

    /**
     * @param largeScreenshotImage1 The large_screenshot_image1
     */
    public void setLargeScreenshotImage1(String largeScreenshotImage1) {
        this.largeScreenshotImage1 = largeScreenshotImage1;
    }

    /**
     * @return The largeScreenshotImage2
     */
    public String getLargeScreenshotImage2() {
        return largeScreenshotImage2;
    }

    /**
     * @param largeScreenshotImage2 The large_screenshot_image2
     */
    public void setLargeScreenshotImage2(String largeScreenshotImage2) {
        this.largeScreenshotImage2 = largeScreenshotImage2;
    }

    /**
     * @return The largeScreenshotImage3
     */
    public String getLargeScreenshotImage3() {
        return largeScreenshotImage3;
    }

    /**
     * @param largeScreenshotImage3 The large_screenshot_image3
     */
    public void setLargeScreenshotImage3(String largeScreenshotImage3) {
        this.largeScreenshotImage3 = largeScreenshotImage3;
    }

    /**
     * @return The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * @param cast The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * @return The torrents
     */
    public List<Torrent> getTorrents() {
        return torrents;
    }

    /**
     * @param torrents The torrents
     */
    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    /**
     * @return The dateUploaded
     */
    public String getDateUploaded() {
        return dateUploaded;
    }

    /**
     * @param dateUploaded The date_uploaded
     */
    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    /**
     * @return The dateUploadedUnix
     */
    public int getDateUploadedUnix() {
        return dateUploadedUnix;
    }

    /**
     * @param dateUploadedUnix The date_uploaded_unix
     */
    public void setDateUploadedUnix(int dateUploadedUnix) {
        this.dateUploadedUnix = dateUploadedUnix;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(url);
        dest.writeValue(imdbCode);
        dest.writeValue(title);
        dest.writeValue(titleEnglish);
        dest.writeValue(titleLong);
        dest.writeValue(slug);
        dest.writeValue(year);
        dest.writeValue(rating);
        dest.writeValue(runtime);
        dest.writeList(genres);
        dest.writeValue(downloadCount);
        dest.writeValue(likeCount);
        dest.writeValue(descriptionIntro);
        dest.writeValue(descriptionFull);
        dest.writeValue(ytTrailerCode);
        dest.writeValue(language);
        dest.writeValue(mpaRating);
        dest.writeValue(backgroundImage);
        dest.writeValue(backgroundImageOriginal);
        dest.writeValue(smallCoverImage);
        dest.writeValue(mediumCoverImage);
        dest.writeValue(largeCoverImage);
        dest.writeValue(mediumScreenshotImage1);
        dest.writeValue(mediumScreenshotImage2);
        dest.writeValue(mediumScreenshotImage3);
        dest.writeValue(largeScreenshotImage1);
        dest.writeValue(largeScreenshotImage2);
        dest.writeValue(largeScreenshotImage3);
        dest.writeList(cast);
        dest.writeList(torrents);
        dest.writeValue(dateUploaded);
        dest.writeValue(dateUploadedUnix);
    }

    public int describeContents() {
        return 0;
    }

}
