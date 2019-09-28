package weather.example.com.weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import weather.example.com.weather.entity.AlarmBean;
import weather.example.com.weather.entity.DataBean;
import weather.example.com.weather.entity.HoursBean;
import weather.example.com.weather.entity.IndexBean;
import weather.example.com.weather.entity.WeekendBean;
import weather.example.com.weather.http.HttpRequestUtils;

/**
 * Created by krito on 2019/9/24.
 */

public class WeatherActivity extends Activity{
    private static final String tag = "WeatherActivity";
    private TextView t_city;
    private TextView day1;
    private TextView day2;
    private TextView day3;
    private TextView day4;
    private TextView day5;
    private TextView day6;
    private TextView day7;
    private TextView day1Temp;
    private TextView day1Weather;
    private TextView more_text;
    private TextView num;
    private TextView tiptext;
    private LinearLayout l1;
    private String[] imageArr = {"beijing","beijing2","beijing3","beijing4","beijing5","beijing6"};
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.weather_activity );

        //等待框
        initProgressDialog();
        progressDialog.show();

        //初始化控件
        init();

        //获取值
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra( "bundle" );
        if (bundle != null){
            String city = bundle.getString( "cityName" );

            //查询一周数据
            toSend( city );
        }
    }

    private void toSend(final String city){
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    List<DataBean> dataBeanList = new ArrayList<DataBean>(  );
                    List<HoursBean> hoursBeanList = new ArrayList<HoursBean>(  );
                    List<IndexBean> indexBeanList = new ArrayList<IndexBean>( );
                    WeekendBean bean = new WeekendBean(  );

                    HttpRequestUtils utils = new HttpRequestUtils();
                    String json = utils.sendGet( "https://www.tianqiapi.com/api/" , "?version=v1&city="+ city +"&appid=55871475&appsecret=JSdj7hUZ" );

                    if (json.contains( "null" )){
                       String msg = json.substring( 4 , json.length() );
                        JSONObject jsonObject = new JSONObject( msg );

                        //解析数据
                        String cityid = jsonObject.getString( "cityid" );
                        String update_time = jsonObject.getString( "update_time" );
                        String cityName = jsonObject.getString( "city" );
                        String cityEn = jsonObject.getString( "cityEn" );
                        String country = jsonObject.getString( "country" );
                        String countryEn = jsonObject.getString( "countryEn" );
                        String data = jsonObject.getString( "data" );

                        JSONArray array = new JSONArray( data );

                        for (int i = 0 ; i < array.length() ; i++){
                            String day = null;
                            String data1 = null;
                            String week = null;
                            String wea = null;
                            String wea_img = null;
                            String air = null;
                            String humidity = null;
                            String air_level = null;
                            String air_tips = null;
                            String alarm = null;
                            String alarm_type = null;
                            String alarm_level = null;
                            String alarm_content = null;
                            AlarmBean alarmBean = null;
                            String tem1Mid = null;
                            String tem2Mid = null;
                            String temMid = null;
                            String winMid = null;
                            StringBuilder winlist = null;
                            String win_speedMid = null;
                            String hours = null;
                            HoursBean hoursBean;
                            String index = null;

                            JSONObject object = new JSONObject( array.get( i ).toString() );
                            if (object.has( "day" )){
                                day = object.getString( "day" );
                            }
                            if (object.has( "date" )){
                                data1 = object.getString( "date" );
                            }
                            if (object.has( "week" )){
                                week = object.getString( "week" );
                            }
                            if (object.has( "wea" )){
                                wea = object.getString( "wea" );
                            }
                            if(object.has( "wea_img" )){
                                wea_img = object.getString( "wea_img" );
                            }
                            if (object.has( "air" )){
                                air = object.getString( "air" );
                            }
                            if (object.has( "humidity" )){
                                humidity = object.getString( "humidity" );
                            }
                            if (object.has( "air_level" )){
                                air_level = object.getString( "air_level" );
                            }
                            if (object.has( "air_tips" ))  {
                                air_tips = object.getString( "air_tips" );
                            }
                            if (object.has( "alarm" )){
                                alarm = object.getString( "alarm" );
                                JSONObject alarmObject = new JSONObject( alarm );

                                if (alarmObject.has( "alarm_type" )){
                                    alarm_type = alarmObject.getString( "alarm_type" );
                                }
                                if (alarmObject.has( "alarm_level" )){
                                    alarm_level = alarmObject.getString( "alarm_level" );
                                }
                                if (alarmObject.has( "alarm_content" )){
                                    alarm_content = alarmObject.getString( "alarm_content" );
                                }
                                alarmBean = new AlarmBean( alarm_type , alarm_level , alarm_content );
                            }
                            if (object.has( "tem1" )){
                                tem1Mid = object.getString( "tem1" );
                            }
                            if (object.has( "tem2" )){
                                tem2Mid = object.getString( "tem2" );
                            }
                            if (object.has( "tem" )){
                                temMid = object.getString( "tem" );
                            }
                            if (object.has( "win" )){
                                winMid = object.getString( "win" );
                                JSONArray winArr = new JSONArray( winMid );
                                winlist = new StringBuilder(  );
                                for (int n = 0 ; n < winArr.length() ; n ++){
                                    winlist.append( winArr.get( n ) );
                                }
                            }
                            if (object.has( "win_speed" )){
                                win_speedMid = object.getString( "win_speed" );
                            }
                            if (object.has( "hours" )){
                                hours = object.getString( "hours" );
                                JSONArray hourArr = new JSONArray( hours );
                                for (int j = 0 ; j < hourArr.length() ;j++ ){
                                    JSONObject hourObject = new JSONObject( hourArr.get( j ).toString() );
                                    String dayNight = hourObject.getString( "day" );
                                    String weaNight = hourObject.getString( "wea" );
                                    String temNight = hourObject.getString( "tem" );
                                    String winNight = hourObject.getString( "win" );
                                    String win_speedHour = hourObject.getString( "win_speed" );

                                    hoursBean = new HoursBean( dayNight , weaNight , temNight ,winNight , win_speedHour );
                                    hoursBeanList.add( hoursBean );
                                }
                            }
                            if (object.has( "index" )){
                                index = object.getString( "index" );
                                JSONArray indexArr = new JSONArray( index );
                                for (int k = 0 ; k < indexArr.length() ; k++){
                                    JSONObject indexObject = new JSONObject( indexArr.get( k ).toString() );
                                    String title = indexObject.getString( "title" );
                                    String level = indexObject.getString( "level" );
                                    String desc = indexObject.getString( "desc" );

                                    IndexBean indexBean = new IndexBean( title , level , desc );
                                    indexBeanList.add( indexBean );
                                }
                            }

                            DataBean dataBean = new DataBean( day , data1 , week , wea ,wea_img ,air , humidity , air_level , air_tips , alarmBean ,
                                    tem1Mid , tem2Mid , temMid , winlist.toString() , win_speedMid , hoursBeanList , indexBeanList );
                            dataBeanList.add( dataBean );
                            }
                        bean = new WeekendBean( cityid , update_time , city , cityEn , country , countryEn , dataBeanList );
                        setText( bean );
                        }
            } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } ).start();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(WeatherActivity.this);
        progressDialog.setIndeterminate(false);//循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);//false不能取消显示，true可以取消显示
    }

    //初始化控件
    private void init(){
        l1 = (LinearLayout)findViewById( R.id.l1 );
        tiptext = (TextView)findViewById( R.id.tiptext );
        t_city = (TextView)findViewById( R.id.t_city );
        day2 = (TextView) findViewById( R.id.day2 );
        day3 = (TextView) findViewById( R.id.day3 );
        day4 = (TextView) findViewById( R.id.day4 );
        day5 = (TextView) findViewById( R.id.day5 );
        day6 = (TextView) findViewById( R.id.day6 );
        day7 = (TextView) findViewById( R.id.day7 );
        day1Temp = (TextView)findViewById( R.id.day1Temp );
        day1Weather = (TextView)findViewById( R.id.day1Weather );
        more_text = (TextView)findViewById( R.id.more_text );
        num = (TextView)findViewById( R.id.more_text_value );
    }

    //更新UI线程
    private void setText(final WeekendBean bean){
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                int i = (int)Math.floor( Math.random() * 6 );
                try {
                    Field field = R.mipmap.class.getField( imageArr[i] );
                    int id = field.getInt( field.getName() );

                    l1.setBackgroundResource( id );
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                t_city.setText( bean.getCity() );

                day2.setText(bean.getDataBeanList().get( 1 ).getWeek() + "\t" + bean.getDataBeanList().get( 1 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 1 ).getTem1() + "\t" + bean.getDataBeanList().get( 1 ).getTem2());

                day3.setText(bean.getDataBeanList().get( 2 ).getWeek() + "\t" + bean.getDataBeanList().get( 2 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 2 ).getTem1()+ "\t"  + bean.getDataBeanList().get( 2 ).getTem2());

                day4.setText(bean.getDataBeanList().get( 3 ).getWeek() + "\t" + bean.getDataBeanList().get( 3 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 3 ).getTem1() + "\t" + bean.getDataBeanList().get( 3 ).getTem2());

                day5.setText(bean.getDataBeanList().get( 4 ).getWeek() + "\t" + bean.getDataBeanList().get( 4 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 4 ).getTem1() + "\t"  + bean.getDataBeanList().get( 4 ).getTem2());

                day6.setText(bean.getDataBeanList().get( 5 ).getWeek() + "\t" + bean.getDataBeanList().get( 5 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 5 ).getTem1() + "\t" + bean.getDataBeanList().get( 5 ).getTem2());

                day7.setText(bean.getDataBeanList().get( 6 ).getWeek() + "\t" + bean.getDataBeanList().get( 6 ).getWea()
                        + "\t" + bean.getDataBeanList().get( 6 ).getTem1() + "\t" + bean.getDataBeanList().get( 6 ).getTem2());

                day1Temp.setText( bean.getDataBeanList().get( 0 ).getTem() );
                day1Weather.setText( bean.getDataBeanList().get( 0 ).getWea() + "\n" + bean.getDataBeanList().get( 0 ).getTem1() + "~" + bean.getDataBeanList().get( 0 ).getTem2() );
                tiptext.setText( "今日建议" + "\n" + bean.getDataBeanList().get( 0 ).getAir_tips() );
                more_text.setText(  "风向" + "\n" + "体感温度"  + "\n" + "今日预报" + "\n" + "空气质量" + "\n" + "风向强度" + "\n" + "空气湿度" );
                num.setText(  bean.getDataBeanList().get( 0 ).getWin() + "\n" + bean.getDataBeanList().get( 0 ).getTem() + "\n" +  bean.getDataBeanList().get( 0 ).getWea() + "\n" + bean.getDataBeanList().get( 0 ).getAir_level() + "\n" + bean.getDataBeanList().get( 0 ).getWin_speed()+ "\n" + bean.getDataBeanList().get( 0 ).getHumidity() );

                //取消对话框
                progressDialog.dismiss();
            }
        } );
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent( WeatherActivity.this , MyMainActivity.class );
        startActivity( intent );
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
