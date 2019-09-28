package weather.example.com.weather.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import weather.example.com.weather.entity.ItemBean;

/**
 * Created by krito on 2019/9/11.
 */

/**
 * dom 文档对象模型解析xml
 */
public class XmlParse {
    private final static String Tag = "DOMParse";
    private String quName;//省名(中文)
    private String pyName;//省名(拼音)
    private String cityname;//城市名
    private String state1;
    private String state2;
    private String stateDetailed;//天气状态
    private String tem1;//最低温度
    private String tem2;//最高温度
    private String temNow;//当前温度
    private String windState;//风的状态
    private String windDir;//风向
    private String windPower;//风级
    private String humidity;//湿度
    private String time;//时间
    private String url;//城市代码

    private List<ItemBean> itemBeanList = new ArrayList<>(  );

    public List<ItemBean> parse(String msg) throws XmlPullParserException, IOException, ExecutionException, InterruptedException {

        String mess = null;//待处理
        if (msg.contains( "null" )){
            mess = msg.split( "null" )[1];
        }

        XmlPullParser xpp = Xml.newPullParser();//获得XmlPullParser解析器
        xpp.setInput( new StringReader( mess ));

//        获取解析的事件类别
        int evtType = xpp.getEventType();

        while (evtType != 1)//文档结束标志
        {
            switch (evtType){
                case 0:
                    break;

                case 2://开始标志
                    String tag = xpp.getName();
//                    如果是city可以开始赋值给bean
                    if (tag != null && tag.equals( "city" )){

                        quName = xpp.getAttributeValue( null , "quName" );//省名(中文)
                        pyName = xpp.getAttributeValue( null , "pyName" );//省名(拼音)
                        cityname = xpp.getAttributeValue( null , "cityname" );//城市名
                        state1 = xpp.getAttributeValue( null , "state1" );
                        state2 = xpp.getAttributeValue( null , "state2" );
                        stateDetailed = xpp.getAttributeValue( null , "stateDetailed" );//天气状态
                        tem1 = xpp.getAttributeValue( null , "tem1" );//最低温度
                        tem2 = xpp.getAttributeValue( null , "tem2" ); //最高温度
                        temNow = xpp.getAttributeValue( null , "temNow" );//当前温度
                        windState = xpp.getAttributeValue( null , "windState" );//风的状态
                        windDir = xpp.getAttributeValue( null , "windDir" );//风向
                        windPower = xpp.getAttributeValue( null , "windPower" );//风级
                        humidity = xpp.getAttributeValue( null , "humidity" );//湿度
                        time = xpp.getAttributeValue( null , "time" );//时间
                        url = xpp.getAttributeValue( null , "url" );//城市代码

                        ItemBean bean = new ItemBean();
                        bean.setCityname( cityname );

                        bean.setState1( state1 );
                        bean.setState2( state2 );
                        bean.setHumi( humidity );
                        bean.setStateDetailed( stateDetailed );
                        bean.setTemNow( temNow );
                        bean.setTemp1( tem1 );
                        bean.setTemp2( tem2 );
                        bean.setTime( time );
                        bean.setWindPower( windPower );
                        bean.setWindState( windState );

                        itemBeanList.add( bean );
                    }
                    break;
                case 3://一个标签结束标签
                    break;
                default:
                    break;
            }
            evtType = xpp.next();
        }
        return itemBeanList;
    }
}
