package com.example.jsonlistnew;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem> {
    Context context;
    int resourceid;
    List<DataItem> data=null;

    public CustomAdapter(Context context, int resource, List<DataItem> objects) {
        super(context, resource, objects);
        this.resourceid=resource;
        this.context=context;
        this.data=objects;
    }
    static class DataHoder
    {
        ImageView iv;
        TextView tv1;
        TextView tv2;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        DataHoder holder=null;
        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView=inflater.inflate(resourceid,parent,false);
            holder=new DataHoder();
            holder.iv=(ImageView)convertView.findViewById(R.id.imageView);
            holder.tv1=(TextView)convertView.findViewById(R.id.textView);
            holder.tv2=(TextView)convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        }
        else
        {
            holder=(DataHoder)convertView.getTag();
        }
        DataItem dataitem=data.get(position);
        holder.iv.setImageResource(dataitem.imgurl);
        holder.tv1.setText(dataitem.subjectname);
        holder.tv2.setText(dataitem.profname);
        return convertView;


    }
}
