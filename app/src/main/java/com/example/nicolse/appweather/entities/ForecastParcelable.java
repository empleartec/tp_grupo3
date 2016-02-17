package com.example.nicolse.appweather.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NicolásE on 02/02/2016.
 */
public class ForecastParcelable implements Parcelable {

    private String code;
    private String date;
    private String day;
    private String high;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String low;
    private String text;

    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public ForecastParcelable createFromParcel(Parcel in) {

            return new ForecastParcelable(in);
        }

        @Override
        public ForecastParcelable[] newArray(int size) {

            return new ForecastParcelable[0];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(date);
        dest.writeString(day);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(text);
    }

    public ForecastParcelable(Parcel in) {
        code = in.readString();
        date = in.readString();
        day = in.readString();
        high = in.readString();
        low = in.readString();
        text = in.readString();
    }

    public ForecastParcelable() {
        super();
    }

}
