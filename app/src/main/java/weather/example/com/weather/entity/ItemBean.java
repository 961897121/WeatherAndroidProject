package weather.example.com.weather.entity;

import java.io.Serializable;

/**
 * Created by krito on 2019/9/18.
 */

public class ItemBean implements Serializable{
    //天气：多云转下雨
    private String state1;
    private String state2;
    private String cityname;
    private String stateDetailed;
    private String temp1;//最低温度
    private String temp2;//最高温度
    private String temNow;//当前温度
    private String windState;//风的状态
    private String humi;//湿度
    private String windPower;//风级
    private String time;//时间

    public ItemBean() {
    }

    public ItemBean(String state1, String state2, String cityname, String stateDetailed, String temp1, String temp2, String temNow, String windState, String humi, String windPower, String time) {
        this.state1 = state1;
        this.state2 = state2;
        this.cityname = cityname;
        this.stateDetailed = stateDetailed;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temNow = temNow;
        this.windState = windState;
        this.humi = humi;
        this.windPower = windPower;
        this.time = time;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStateDetailed() {
        return stateDetailed;
    }

    public void setStateDetailed(String stateDetailed) {
        this.stateDetailed = stateDetailed;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemNow() {
        return temNow;
    }

    public void setTemNow(String temNow) {
        this.temNow = temNow;
    }

    public String getWindState() {
        return windState;
    }

    public void setWindState(String windState) {
        this.windState = windState;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "state1='" + state1 + '\'' +
                ", state2='" + state2 + '\'' +
                ", cityname='" + cityname + '\'' +
                ", stateDetailed='" + stateDetailed + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                ", temNow='" + temNow + '\'' +
                ", windState='" + windState + '\'' +
                ", humi='" + humi + '\'' +
                ", windPower='" + windPower + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
