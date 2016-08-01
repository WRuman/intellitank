/**
 * Created by will.ruman on 8/1/16.
 */

package com.ruman.android.intellitank;

import android.os.Parcel;
import android.os.Parcelable;

public class Tank implements Parcelable {
    private String tankName;
    private String tankType;

    public Tank(String name, String type) {
        this.tankName = name;
        this.tankType = type;
    }

    public Tank(Parcel p) {
        this.tankName = p.readString();
        this.tankType = p.readString();
    }

    public String getTankName() {
        return tankName;
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankName(String name) {
        this.tankName = name;
    }

    public  void setTankType(String type) {
        this.tankType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tankName);
        parcel.writeString(tankType);
    }

    public static final Parcelable.Creator<Tank> CREATOR = new Parcelable.Creator<Tank>() {
        public Tank createFromParcel(Parcel in ) {
            return new Tank(in);
        }

        public Tank[] newArray(int size) {
            return new Tank[size];
        }
    };
}
