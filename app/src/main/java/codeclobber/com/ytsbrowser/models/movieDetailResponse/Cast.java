package codeclobber.com.ytsbrowser.models.movieDetailResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast implements Parcelable {

    public final static Parcelable.Creator<Cast> CREATOR = new Creator<Cast>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cast createFromParcel(Parcel in) {
            Cast instance = new Cast();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.characterName = ((String) in.readValue((String.class.getClassLoader())));
            instance.urlSmallImage = ((String) in.readValue((String.class.getClassLoader())));
            instance.imdbCode = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Cast[] newArray(int size) {
            return (new Cast[size]);
        }

    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("character_name")
    @Expose
    private String characterName;
    @SerializedName("url_small_image")
    @Expose
    private String urlSmallImage;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * @param characterName The character_name
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    /**
     * @return The urlSmallImage
     */
    public String getUrlSmallImage() {
        return urlSmallImage;
    }

    /**
     * @param urlSmallImage The url_small_image
     */
    public void setUrlSmallImage(String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(characterName);
        dest.writeValue(urlSmallImage);
        dest.writeValue(imdbCode);
    }

    public int describeContents() {
        return 0;
    }

}
