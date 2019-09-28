package weather.example.com.weather.entity;

import java.io.Serializable;

/**
 * Created by krito on 2019/9/15.
 */

public class LocationBean implements Serializable{
    private String lng;//lon
    private String lat;//lat

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        String str = "";
        if (lng != null){
            str = str + "Lng=" + lng + ",";
        }
        if (lat != null){
            str = str + "lat=" + lat + ",";
        }
//        return "LocationBean{" +
//                "Lng='" + Lng + '\'' +
//                ", lat='" + lat + '\'' +
//                '}';
        return str;
    }
}
