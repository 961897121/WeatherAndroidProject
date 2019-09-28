package weather.example.com.weather.http;

/**
 * Created by krito on 2019/9/11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http调用方法类
 */
public class HttpRequestUtils {
    private final static String tag = "HttpRequestUtils";
    /**
     * weather.example.com.weatherassistant.http-get
     * @param path(访问的url)
     * @param param(请求参数)
     * @return
     */
    public String sendGet(String path , String param){
        String response = null;//服务端响应内容
        BufferedReader br = null;//读缓冲区

        try {
            String req_url = path + param;//请求url
            URL url = new URL( req_url );
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            httpURLConnection.setRequestMethod( "GET" );
            //设置请求时长
            httpURLConnection.setConnectTimeout( 3000 );

            br = new BufferedReader( new InputStreamReader( httpURLConnection.getInputStream() , "utf-8" ) );

            int status = httpURLConnection.getResponseCode();

            if (status == 200){
                String str = null;
                while ((str = br.readLine()) != null){
                    response = response + str;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null){
                    br.close();//关闭读缓冲
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
