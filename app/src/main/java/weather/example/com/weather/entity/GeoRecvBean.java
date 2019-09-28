package weather.example.com.weather.entity;

import java.io.Serializable;

/**
 * Created by krito on 2019/9/11.
 */

public class GeoRecvBean implements Serializable{
    private String status;
    private String result;
    private LocationBean locationBean;//包含经纬度
    private String formatted_address;
    private String business;
    private AddressComponetBean addressComponetBean;//包含城市名，区名等详细信息
    private String cityCode;//城市代号


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocationBean getLocationBean() {
        return locationBean;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponetBean getAddressComponetBean() {
        return addressComponetBean;
    }

    public void setAddressComponetBean(AddressComponetBean addressComponetBean) {
        this.addressComponetBean = addressComponetBean;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        String str = "";

        if (status != null){
            str = str + "status=" + status + ",";
        }
        if (result != null){
            str = str + "result=" + result + ",";
        }
        if (locationBean != null){
            str = str + locationBean.toString() + ",";
        }
        if (formatted_address != null){
            str = str + "formatted_address=" + formatted_address + ",";
        }
        if (business != null){
            str = str + "business=" + business + ",";
        }
        if (addressComponetBean != null){
            str = str + addressComponetBean.toString() + ",";
        }
        if (cityCode != null){
            str = str + "cityCode=" + cityCode + ",";
        }

//        return "GeoRecvBean{" +
//                "status='" + status + '\'' +
//                ", result='" + result + '\'' +
//                ", locationBean=" + locationBean +
//                ", formatted_address='" + formatted_address + '\'' +
//                ", business='" + business + '\'' +
//                ", addressComponetBean=" + addressComponetBean +
//                ", cityCode='" + cityCode + '\'' +
//                '}';
        return str;
    }
}
