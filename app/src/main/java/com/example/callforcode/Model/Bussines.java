package com.example.callforcode.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Bussines implements Parcelable {

    //private String id = "";
    private String name = "";
    private String description = "";
    private Double lat = 0.0;
    private Double lon = 0.0;
    private String status = "";
    private String category = "";

    public String getCategory() {
        return category;
    }

    //public String getId() {
      //  return id;
   // }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getStatus() {
        return status;
    }

    public Bussines( String name, String description, Double lat, Double lon, String status, String category) {
        //this.id = id;
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
        this.category = category;
    }

    protected Bussines(Parcel in) {
       // id = in.readString();
        name = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            lat = null;
        } else {
            lat = in.readDouble();
        }
        if (in.readByte() == 0) {
            lon = null;
        } else {
            lon = in.readDouble();
        }
        status = in.readString();
        category=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       // dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        if (lat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lat);
        }
        if (lon == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lon);
        }
        dest.writeString(status);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bussines> CREATOR = new Creator<Bussines>() {
        @Override
        public Bussines createFromParcel(Parcel in) {
            return new Bussines(in);
        }

        @Override
        public Bussines[] newArray(int size) {
            return new Bussines[size];
        }
    };
}
