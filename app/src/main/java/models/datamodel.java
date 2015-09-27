package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by htammare on 9/25/2015.
 */
public class datamodel implements Parcelable {
    public String url;
    public String titleNoFormatting;
    public String tbUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.titleNoFormatting);
    }

    public datamodel() {
    }

    protected datamodel(Parcel in) {
        this.url = in.readString();
        this.titleNoFormatting = in.readString();
    }

    public static final Parcelable.Creator<datamodel> CREATOR = new Parcelable.Creator<datamodel>() {
        public datamodel createFromParcel(Parcel source) {
            return new datamodel(source);
        }

        public datamodel[] newArray(int size) {
            return new datamodel[size];
        }
    };
}
