package weather.example.com.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import weather.example.com.weather.adapter.MyAdapter;
import weather.example.com.weather.entity.GeoRecvBean;
import weather.example.com.weather.entity.ItemBean;
import weather.example.com.weather.service.LocationService;
import weather.example.com.weather.thread.SearchThread;
import weather.example.com.weather.utils.PingyinParse;

/**
 * Created by krito on 2019/9/18.
 */

public class MyMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String tag = "MyMainActivity";
    private TextView tex;
    private AutoCompleteTextView autoCompleteTextView;
    private ListView listView;
    private boolean actvflag = true;
    private String[] countries = null;//下拉菜单文字选项
    private MyBroadcastReceiver myBroadcastReceiver;
    private List<ItemBean> itemBeanList = new ArrayList<>(  );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mylist );

        //初始化控件
        init();

        //启动定位服务
        Intent intent = new Intent( this , LocationService.class );
        startService( intent );

        //注册广播
        register();

        listView.setOnItemClickListener( this );
    }

    //初始化控件
    private void init(){
        tex = (TextView)findViewById( R.id.tex );
        autoCompleteTextView = (AutoCompleteTextView)findViewById( R.id.autocomplete_country );
        listView = (ListView)findViewById( R.id.listview );
        //查询按钮
        findViewById( R.id.btn_search ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e( tag , "点击了查询按钮" );
                //按钮点击事件(查询城市的天气情况)
                //文本框内容
                String cityName = autoCompleteTextView.getText().toString().trim();
                //回调线程
                FutureTask task = new FutureTask( new SearchThread( cityName ) );
                Thread thread = new Thread( task );
                //启动线程
                thread.start();

                //获取返回的天气集合list
                try {
                    itemBeanList = (List<ItemBean>)task.get();

                    //更新线程使用listview
                    listView.setAdapter( new MyAdapter( itemBeanList , MyMainActivity.this ) );

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } );

        //文本框有字的时候提示
        if (actvflag){
            countries = getResources().getStringArray( R.array.contries_array );
        }

        //给自定义textview配置Adaptor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this , R.layout.support_simple_spinner_dropdown_item , countries );
        autoCompleteTextView.setAdapter( adapter );
    }

    /**
     * 注册广播
     */
    private void register() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentfilter = new IntentFilter(  );
        intentfilter.addAction( "jsonstring" );
        registerReceiver( myBroadcastReceiver , intentfilter );
    }

    //点击item触发事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent;
        ItemBean itemBean = (ItemBean) listView.getAdapter().getItem( position );

        Intent intent = new Intent( MyMainActivity.this , WeatherActivity.class );
        Bundle bundle = new Bundle(  );

        bundle.putString( "cityName",itemBean.getCityname() );
        intent.putExtra( "bundle" , bundle );
        startActivity( intent );
    }


    //自定义广播接收器
    private class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            GeoRecvBean bean = (GeoRecvBean)intent.getSerializableExtra( "bean" );
            if (bean != null){
                String city = bean.getAddressComponetBean().getCity();

                //中文变拼音
                String cityName = city.split( "市" )[0];
                PingyinParse parse = new PingyinParse();
                String pingying = parse.toPinYing( cityName );

                //获取数据
                FutureTask task = new FutureTask( new SearchThread( pingying ) );
                Thread thread = new Thread( task );
                //启动线程
                thread.start();

                try {
                    //得到回调,获得该城市天气bean的list
                    List<ItemBean> itemBeanList = (List<ItemBean>) task.get();

                    //更新ui
                    listView.setAdapter( new MyAdapter( itemBeanList , MyMainActivity.this ));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver( myBroadcastReceiver );
        finish();
    }
}
