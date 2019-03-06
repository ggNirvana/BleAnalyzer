package com.example.nirvana.bleanalysor.centroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.nirvana.bleanalysor.R;
/**
 * Created by Administrator on 2018/3/17.
 */

/***************************************************************************************************
 * 用于显示测量结果的适配器
 **************************************************************************************************/
public class SensorResultListAdapter extends BaseAdapter {
    private ArrayList<SensorResult> mSensorResult;//传感器结果列表
    private int resourceID;//保存初始化时传入的viewId
    private Context context;//保存初始化时传进来的context
    private Callback mCallback;//注：所有listview的item共用同一个

    /***************************************************************************************************
     * 自定义接口，用于回调按钮点击事件到Activity
     **************************************************************************************************/
     public interface Callback {
         public void click(View v);
     }
    public SensorResult getSensorResult(int i ){
         return mSensorResult.get(i);
    }
    /***************************************************************************************************
     * SensorResultListAdapter构造函数
     **************************************************************************************************/
    public SensorResultListAdapter(Context context, int viewResourceId, Callback callback) {
        super();
        this.context = context;
        resourceID = viewResourceId;
        mSensorResult = new ArrayList<SensorResult>();
        mCallback = callback;
    }
    /***************************************************************************************************
     * 添加测量结果
     **************************************************************************************************/
    public void addResult(SensorResult result) {
        mSensorResult.add(result);
    }
    /***************************************************************************************************
     * 更新测量结果
     **************************************************************************************************/
    public void updateResult(int i, String result1)
    {
        mSensorResult.get(i).setResult(result1);
    }
    /***************************************************************************************************
     * 更新测量结果对应的标题
     **************************************************************************************************/
    public void setResultTitle(int i,String resultTitle)
    {
        mSensorResult.get(i).setName(resultTitle);
    }
    /***************************************************************************************************
     * 获取测量结果数量
     **************************************************************************************************/
    @Override
    public int getCount() {
        return mSensorResult.size();
    }
    /***************************************************************************************************
     * 获取测量结果
     **************************************************************************************************/
    @Override
    public Object getItem(int i) {
        return mSensorResult.get(i);
    }
    /***************************************************************************************************
     * 获取测量结果Id
     **************************************************************************************************/
    @Override
    public long getItemId(int i) {
        return i;
    }
    /***************************************************************************************************
     * getView函数
     **************************************************************************************************/
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view;
// General ListView optimization code.
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceID, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.result1 = (TextView) view.findViewById(R.id.result1);
            viewHolder.button = (Button)view.findViewById(R.id.button);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        SensorResult result = mSensorResult.get(i);
        viewHolder.name.setText(result.name);
        viewHolder.result1.setText(result.result1);

        if(result.button!=null) {
            viewHolder.button.setVisibility(View.VISIBLE);
            //viewHolder.button.setEnabled(false);
            viewHolder.button.setText(result.button);
            viewHolder.button.setTag(i);

            viewHolder.button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    mCallback.click(v);
                }
            });
        }
        else {
            //viewHolder.button.setEnabled(false);
            viewHolder.button.setVisibility(View.INVISIBLE);
        }
        if(i!=0&&i!=1&&i!=2){
            viewHolder.button.setVisibility(View.INVISIBLE);
        }

        return view;
    }
    class ViewHolder {
        TextView name;
        TextView result1;
        Button button;
    }

    /**
     * Created by Administrator on 2018/3/14.
     */
    /***************************************************************************************************
     * SensorResult类
     **************************************************************************************************/
    public static class SensorResult {
        String name;
        String result1;
        String button;

        public SensorResult(String name, String result1,String button) {
            this.name = name;
            this.result1 = result1;
            this.button = button;
        }
        public void setButton(String s){
            this.button=s;
        }
        public void setResult(String result1){
            this.result1 = result1;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }
}