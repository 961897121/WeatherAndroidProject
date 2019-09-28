package weather.example.com.weather.entity;

/**
 * Created by krito on 2019/9/26.
 */

import java.util.List;

/**
 * 一周七天的数据
 */
public class WeekendBean {
    private String cityid;
    private String update_time;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private List<DataBean> dataBeanList;

    public WeekendBean() {
    }

    public WeekendBean(String cityid, String update_time, String city, String cityEn, String country, String countryEn, List<DataBean> dataBeanList) {
        this.cityid = cityid;
        this.update_time = update_time;
        this.city = city;
        this.cityEn = cityEn;
        this.country = country;
        this.countryEn = countryEn;
        this.dataBeanList = dataBeanList;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public List<DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public String toString() {
        return  "cityid='" + cityid + '\'' +
                ", update_time='" + update_time + '\'' +
                ", city='" + city + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", country='" + country + '\'' +
                ", countryEn='" + countryEn + '\'' +
                ", dataBeanList=" + dataBeanList ;
    }
}
