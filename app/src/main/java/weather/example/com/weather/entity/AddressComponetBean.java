package weather.example.com.weather.entity;

import java.io.Serializable;

/**
 * Created by krito on 2019/9/15.
 */

public class AddressComponetBean implements Serializable{
    private String city;//城市名
    private String direction;//附近
    private String distance;//
    private String district;//区
    private String province;//省份
    private String street;//街道
    private String street_number;//街道编号

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    @Override
    public String toString() {
        String str = "";
        if (city != null){
            str = str + "city=" + city + ",";
        }
        if (direction != null){
            str = str + "direction=" + direction + ",";
        }
        if (distance != null){
            str = str + "distance=" + distance + ",";
        }
        if (district != null){
            str = str + "district=" + district + ",";
        }
        if (province != null){
            str = str + "province=" + province + ",";
        }
        if (street != null){
            str = str + "street=" + street + ",";
        }
        if (street_number != null){
            str = str + "street_number=" + getStreet() + ",";
        }
//        return "AddressComponetBean{" +
//                "city='" + city + '\'' +
//                ", direction='" + direction + '\'' +
//                ", distance='" + distance + '\'' +
//                ", district='" + district + '\'' +
//                ", province='" + province + '\'' +
//                ", street='" + street + '\'' +
//                ", street_number='" + street_number + '\'' +
//                '}';
        return str;
    }
}
