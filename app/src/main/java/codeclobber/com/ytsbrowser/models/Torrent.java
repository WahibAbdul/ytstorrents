package codeclobber.com.ytsbrowser.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Torrent implements Parcelable {

    public final static Parcelable.Creator<Torrent> CREATOR = new Creator<Torrent>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Torrent createFromParcel(Parcel in) {
            Torrent instance = new Torrent();
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.hash = ((String) in.readValue((String.class.getClassLoader())));
            instance.quality = ((String) in.readValue((String.class.getClassLoader())));
            instance.seeds = ((int) in.readValue((int.class.getClassLoader())));
            instance.peers = ((int) in.readValue((int.class.getClassLoader())));
            instance.size = ((String) in.readValue((String.class.getClassLoader())));
            instance.sizeBytes = ((Long) in.readValue((int.class.getClassLoader())));
            instance.dateUploaded = ((String) in.readValue((String.class.getClassLoader())));
            instance.dateUploadedUnix = ((Long) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Torrent[] newArray(int size) {
            return (new Torrent[size]);
        }

    };
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("seeds")
    @Expose
    private int seeds;
    @SerializedName("peers")
    @Expose
    private int peers;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("size_bytes")
    @Expose
    private long sizeBytes;
    @SerializedName("date_uploaded")
    @Expose
    private String dateUploaded;
    @SerializedName("date_uploaded_unix")
    @Expose
    private long dateUploadedUnix;

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
     * @return The hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash The hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return The quality
     */
    public String getQuality() {
        return quality;
    }

    /**
     * @param quality The quality
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * @return The seeds
     */
    public int getSeeds() {
        return seeds;
    }

    /**
     * @param seeds The seeds
     */
    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    /**
     * @return The peers
     */
    public int getPeers() {
        return peers;
    }

    /**
     * @param peers The peers
     */
    public void setPeers(int peers) {
        this.peers = peers;
    }

    /**
     * @return The size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return The sizeBytes
     */
    public long getSizeBytes() {
        return sizeBytes;
    }

    /**
     * @param sizeBytes The size_bytes
     */
    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
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
    public long getDateUploadedUnix() {
        return dateUploadedUnix;
    }

    /**
     * @param dateUploadedUnix The date_uploaded_unix
     */
    public void setDateUploadedUnix(long dateUploadedUnix) {
        this.dateUploadedUnix = dateUploadedUnix;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(hash);
        dest.writeValue(quality);
        dest.writeValue(seeds);
        dest.writeValue(peers);
        dest.writeValue(size);
        dest.writeValue(sizeBytes);
        dest.writeValue(dateUploaded);
        dest.writeValue(dateUploadedUnix);
    }

    public int describeContents() {
        return 0;
    }

}
