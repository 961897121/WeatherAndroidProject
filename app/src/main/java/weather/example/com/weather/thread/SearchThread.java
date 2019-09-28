package weather.example.com.weather.thread;

import java.util.List;
import java.util.concurrent.Callable;

import weather.example.com.weather.entity.ItemBean;
import weather.example.com.weather.http.HttpRequestUtils;
import weather.example.com.weather.utils.PingyinParse;
import weather.example.com.weather.utils.XmlParse;

/**
 * Created by krito on 2019/9/18.
 */

public class SearchThread implements Callable<List<ItemBean>> {
    private String cityName;//城市名

    public SearchThread(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public List<ItemBean> call() throws Exception {

        //转拼音
        PingyinParse pingyinParse = new PingyinParse();
        String city = pingyinParse.toPinYing( cityName );

        //http-get发送数据
        HttpRequestUtils utils = new HttpRequestUtils();
        String src = utils.sendGet( "http://flash.weather.com.cn/wmaps/xml/", city + ".xml" );

        //dom解析
        XmlParse xmlParse = new XmlParse();
        List<ItemBean> list = xmlParse.parse( src );

        return list;
    }
}
