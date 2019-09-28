package weather.example.com.weather.entity;

import java.util.List;

/**
 * Created by krito on 2019/9/26.
 */

public class DataBean {
    private String day;
    private String date;
    private String week;
    private String wea;//天气
    private String wea_img;
    private String air;
    private String humidity;
    private String air_level;
    private String air_tips;
    private AlarmBean alarmBean;
    private String tem1;
    private String tem2;
    private String tem;
    private String win;
    private String win_speed;
    private List<HoursBean> hoursBeanList;
    private List<IndexBean> indexBeanList;

    public DataBean() {
    }

    public DataBean(String day, String date, String week, String wea, String wea_img, String air, String humidity, String air_level, String air_tips, AlarmBean alarmBean, String tem1, String tem2, String tem, String win, String win_speed, List<HoursBean> hoursBeanList, List<IndexBean> indexBeanList) {
        this.day = day;
        this.date = date;
        this.week = week;
        this.wea = wea;
        this.wea_img = wea_img;
        this.air = air;
        this.humidity = humidity;
        this.air_level = air_level;
        this.air_tips = air_tips;
        this.alarmBean = alarmBean;
        this.tem1 = tem1;
        this.tem2 = tem2;
        this.tem = tem;
        this.win = win;
        this.win_speed = win_speed;
        this.hoursBeanList = hoursBeanList;
        this.indexBeanList = indexBeanList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAir_level() {
        return air_level;
    }

    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }

    public AlarmBean getAlarmBean() {
        return alarmBean;
    }

    public void setAlarmBean(AlarmBean alarmBean) {
        this.alarmBean = alarmBean;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public List<HoursBean> getHoursBeanList() {
        return hoursBeanList;
    }

    public void setHoursBeanList(List<HoursBean> hoursBeanList) {
        this.hoursBeanList = hoursBeanList;
    }

    public List<IndexBean> getIndexBeanList() {
        return indexBeanList;
    }

    public void setIndexBeanList(List<IndexBean> indexBeanList) {
        this.indexBeanList = indexBeanList;
    }

    @Override
    public String toString() {
        return "day='" + day + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", wea='" + wea + '\'' +
                ", wea_img='" + wea_img + '\'' +
                ", air='" + air + '\'' +
                ", humidity='" + humidity + '\'' +
                ", air_level='" + air_level + '\'' +
                ", air_tips='" + air_tips + '\'' +
                ", alarmBean=" + alarmBean +
                ", tem1='" + tem1 + '\'' +
                ", tem2='" + tem2 + '\'' +
                ", tem='" + tem + '\'' +
                ", win=" + win +
                ", win_speed='" + win_speed + '\'' +
                ", hoursBeanList=" + hoursBeanList +
                ", indexBeanList=" + indexBeanList ;
    }
}
