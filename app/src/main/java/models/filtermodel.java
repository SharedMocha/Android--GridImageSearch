package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by htammare on 9/26/2015.
 */
public class filtermodel implements Parcelable {
    public String getImageSize() {
        return imageSize;
    }

    public String getColorFilter() {
        return colorFilter;
    }

    public String getImageType() {
        return imageType;
    }

    public String getSiteSearch() {
        return siteSearch;
    }

    public static Creator<filtermodel> getCREATOR() {
        return CREATOR;
    }

    public String imageSize;
    public String colorFilter;
    public String imageType;
    public String siteSearch;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageSize);
        dest.writeString(this.colorFilter);
        dest.writeString(this.imageType);
        dest.writeString(this.siteSearch);
    }

    public filtermodel() {
    }

    protected filtermodel(Parcel in) {
        this.imageSize = in.readString();
        this.colorFilter = in.readString();
        this.imageType = in.readString();
        this.siteSearch = in.readString();
    }

    public static final Parcelable.Creator<filtermodel> CREATOR = new Parcelable.Creator<filtermodel>() {
        public filtermodel createFromParcel(Parcel source) {
            return new filtermodel(source);
        }

        public filtermodel[] newArray(int size) {
            return new filtermodel[size];
        }
    };
}
