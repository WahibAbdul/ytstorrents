package codeclobber.com.ytsbrowser.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable {

    public final static Parcelable.Creator<Meta> CREATOR = new Creator<Meta>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Meta createFromParcel(Parcel in) {
            Meta instance = new Meta();
            instance.serverTime = ((int) in.readValue((int.class.getClassLoader())));
            instance.serverTimezone = ((String) in.readValue((String.class.getClassLoader())));
            instance.apiVersion = ((int) in.readValue((int.class.getClassLoader())));
            instance.executionTime = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Meta[] newArray(int size) {
            return (new Meta[size]);
        }

    };
    @SerializedName("server_time")
    @Expose
    private int serverTime;
    @SerializedName("server_timezone")
    @Expose
    private String serverTimezone;
    @SerializedName("api_version")
    @Expose
    private int apiVersion;
    @SerializedName("execution_time")
    @Expose
    private String executionTime;

    /**
     * @return The serverTime
     */
    public int getServerTime() {
        return serverTime;
    }

    /**
     * @param serverTime The server_time
     */
    public void setServerTime(int serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * @return The serverTimezone
     */
    public String getServerTimezone() {
        return serverTimezone;
    }

    /**
     * @param serverTimezone The server_timezone
     */
    public void setServerTimezone(String serverTimezone) {
        this.serverTimezone = serverTimezone;
    }

    /**
     * @return The apiVersion
     */
    public int getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion The api_version
     */
    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return The executionTime
     */
    public String getExecutionTime() {
        return executionTime;
    }

    /**
     * @param executionTime The execution_time
     */
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(serverTime);
        dest.writeValue(serverTimezone);
        dest.writeValue(apiVersion);
        dest.writeValue(executionTime);
    }

    public int describeContents() {
        return 0;
    }

}
