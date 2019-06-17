package codeclobber.com.ytsbrowser.models.movieDetailResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import codeclobber.com.ytsbrowser.models.Meta;

public class MovieDetailResponse implements Parcelable {

    public final static Parcelable.Creator<MovieDetailResponse> CREATOR = new Creator<MovieDetailResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDetailResponse createFromParcel(Parcel in) {
            MovieDetailResponse instance = new MovieDetailResponse();
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            instance.Meta = ((codeclobber.com.ytsbrowser.models.Meta) in.readValue((Meta.class.getClassLoader())));
            return instance;
        }

        public MovieDetailResponse[] newArray(int size) {
            return (new MovieDetailResponse[size]);
        }

    };
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("@meta")
    @Expose
    private Meta Meta;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage The status_message
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * @return The Meta
     */
    public Meta getMeta() {
        return Meta;
    }

    /**
     * @param Meta The @meta
     */
    public void setMeta(Meta Meta) {
        this.Meta = Meta;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(statusMessage);
        dest.writeValue(data);
        dest.writeValue(Meta);
    }

    public int describeContents() {
        return 0;
    }

}
