
package codeclobber.com.ytsbrowser.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesResponse implements Parcelable
{

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
    
    private codeclobber.com.ytsbrowser.models.Meta Meta;
    public final static Parcelable.Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MoviesResponse createFromParcel(Parcel in) {
            MoviesResponse instance = new MoviesResponse();
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.statusMessage = ((String) in.readValue((String.class.getClassLoader())));
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            instance.Meta = ((codeclobber.com.ytsbrowser.models.Meta) in.readValue((Meta.class.getClassLoader())));
            return instance;
        }

        public MoviesResponse[] newArray(int size) {
            return (new MoviesResponse[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * 
     * @param statusMessage
     *     The status_message
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The Meta
     */
    public codeclobber.com.ytsbrowser.models.Meta getMeta() {
        return Meta;
    }

    /**
     * 
     * @param Meta
     *     The @meta
     */
    public void setMeta(codeclobber.com.ytsbrowser.models.Meta Meta) {
        this.Meta = Meta;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(statusMessage);
        dest.writeValue(data);
        dest.writeValue(Meta);
    }

    public int describeContents() {
        return  0;
    }

}
