package weather.example.com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.reflect.Field;
import java.util.List;

import weather.example.com.weather.R;
import weather.example.com.weather.entity.ItemBean;

/**
 * Created by krito on 2019/9/17.
 */

public class MyAdapter extends BaseAdapter {
    private List<ItemBean> list;
    private Context context;

    /**
     * 构造器
     * @param list（数据）
     * @param context（界面）
     */
    public MyAdapter(List<ItemBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    //集合数据长度
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get( position );//根据索引取出数据
    }

    @Override
    public long getItemId(int position) {
        return position;//返回索引
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
//            给xml创建java对象
            convertView = LayoutInflater.from( context ).inflate( R.layout.list_item , parent , false );

            viewHolder.imageView1 = (ImageView)convertView.findViewById( R.id.image1 );
            viewHolder.imageView2 = (ImageView)convertView.findViewById( R.id.image2 );
            viewHolder.imageView3 = (ImageView)convertView.findViewById( R.id.image3 );
            //指定布局控件
            viewHolder.text = (TextView)convertView.findViewById( R.id.text );
            viewHolder.time = (TextView)convertView.findViewById( R.id.time );
            viewHolder.head = (TextView)convertView.findViewById( R.id.head );

            convertView.setTag( viewHolder );
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        /**
         * 晴转多云
         */
        try {
            //图片
            String state1 = "b_" + list.get( position ).getState1();
            String state2 = "b_" + list.get( position ).getState2();

            Field field1 = R.drawable.class.getField( state1 );
            Field field2 = R.drawable.class.getField( state2 );
            int id1 = field1.getInt( field1.getName() );
            int id2 = field1.getInt( field2.getName() );
            Glide.with( context ).load( id1 ).diskCacheStrategy( DiskCacheStrategy.ALL ).into( viewHolder.imageView1 );
            Glide.with( context ).load( id2 ).diskCacheStrategy( DiskCacheStrategy.ALL ).into( viewHolder.imageView3 );

            viewHolder.imageView2.setImageResource( R.mipmap.rotate1 );
            //内容
            String cityName = list.get( position ).getCityname();
            String stateDetailed = list.get( position ).getStateDetailed();
            String temp1 = list.get( position ).getTemp1();
            String temp2 = list.get( position ).getTemp2();
            String nowtemp = list.get( position ).getTemNow();
            String windState = list.get( position ).getWindState();
            String humi = list.get( position ).getHumi();
            String windPower = list.get( position ).getWindPower();
            String time = list.get( position ).getTime();

            //标题
            viewHolder.head.setText( cityName );
            viewHolder.time.setText( time );

            viewHolder.text.setText( stateDetailed );

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    /**
     * 内部类
     */
    private class ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        TextView text;
        TextView time;
        TextView head;
    }
}
