package weather.example.com.weather.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import org.json.JSONException;
import org.json.JSONObject;

import weather.example.com.weather.entity.AddressComponetBean;
import weather.example.com.weather.entity.GeoRecvBean;
import weather.example.com.weather.entity.LocationBean;
import weather.example.com.weather.http.HttpRequestUtils;
/**
 * Created by krito on 2019/9/12.
 */

/**
 *
 */
public class LocationService extends Service implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String tag = "LocationService";
    private double latitude = 0.0;
    private double longitude = 0.0;
    private static final String url = "http://api.map.baidu.com/geocoder";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获取经纬度
        getLocation();
        return START_NOT_STICKY ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //获取经纬度
//        getLocation();
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if (locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation( LocationManager.GPS_PROVIDER );
            //初始获取不到经纬度
           if(location != null){
               latitude = location.getLatitude();
               longitude = location.getLongitude();
           }
       }else{
           final LocationListener locationListener = new LocationListener() {

               // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
               @Override
               public void onStatusChanged(String provider, int status, Bundle extras) {
               }

               // Provider被enable时触发此函数，比如GPS被打开
               @Override
               public void onProviderEnabled(String provider) {
               }

               // Provider被disable时触发此函数，比如GPS被关闭
               @Override
               public void onProviderDisabled(String provider) {
               }

               //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
               @Override
               public void onLocationChanged(Location location) {
                   if (location != null) {
//                       Log.e("LocationService", "Location changed : Lat: "
//                               + location.getLatitude() + " Lng: "
//                               + location.getLongitude());

                   }
               }
           };
           locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);
           Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
           if(location != null){
               latitude = location.getLatitude(); //经度
               longitude = location.getLongitude(); //纬度

               //网络请求
               send();

               //停止监听器
               locationManager.removeUpdates( locationListener );
           }
       }
   }

    /**
     * 新建线程http-post
     */
    public void send(){
        new Thread( new Runnable() {
            @Override
            public void run() {
                //http-get发送请求给百度查询信息
                HttpRequestUtils utils = new HttpRequestUtils();
                String response = utils.sendGet( url , "?location="+latitude+","+ longitude + "&output=json&key=%9DZ7XiWqDFN2xwHLmtvZxdw0EZFzLi4i" );

                if (response.length() > 0 && response != null) {
                    String msg = response.substring( 4, response.length() );

                    GeoRecvBean bean = new GeoRecvBean();
                    LocationBean locationBean = new LocationBean();
                    AddressComponetBean componetBean = new AddressComponetBean();

                    try {
                        JSONObject root = new JSONObject( msg );
                        if (root.has( "status" )) {
                            String status = root.getString( "status" );
                            bean.setStatus( status );
                        }
                        if (root.has( "result" )) {
                            String result = root.getString( "result" );

                            JSONObject location = new JSONObject( result );
                            String locObj = location.getString( "location" );

                            JSONObject object = new JSONObject( locObj );

                            String lng = object.getString( "lng" );
                            String lat = object.getString( "lat" );

                            locationBean.setLng( lng );
                            locationBean.setLat( lat );

                            bean.setLocationBean( locationBean );

                            if (location.has( "formatted_address" )) {
                                String formatted_address = location.getString( "formatted_address" );
                                bean.setFormatted_address( formatted_address );
                            }
                            if (location.has( "business" )) {
                                String business = location.getString( "business" );
                                bean.setBusiness( business );
                            }
                            if (location.has( "addressComponent" )) {
                                String addressComponent = location.getString( "addressComponent" );

                                JSONObject addrObj = new JSONObject( addressComponent );
                                String city = addrObj.getString( "city" );
                                String direction = addrObj.getString( "direction" );
                                String distance = addrObj.getString( "distance" );
                                String district = addrObj.getString( "district" );
                                String province = addrObj.getString( "province" );
                                String street = addrObj.getString( "street" );
                                String street_number = addrObj.getString( "street_number" );

                                componetBean.setCity( city );
                                componetBean.setDirection( direction );
                                componetBean.setDistance( distance );
                                componetBean.setDistrict( district );
                                componetBean.setProvince( province );
                                componetBean.setStreet( street );
                                componetBean.setStreet_number( street_number );

                                bean.setAddressComponetBean( componetBean );

                                Intent intent = new Intent(  );
                                intent.putExtra( "bean" , bean );
                                intent.setAction( "jsonstring" );
                                sendBroadcast( intent );
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } ).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
