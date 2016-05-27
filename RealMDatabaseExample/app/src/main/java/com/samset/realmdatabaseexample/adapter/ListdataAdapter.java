package com.samset.realmdatabaseexample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samset.realmdatabaseexample.R;
import com.samset.realmdatabaseexample.model.Person;

import java.util.List;

/**
 * Created by weesync on 27/05/16.
 */
public class ListdataAdapter extends BaseAdapter {
    List<Person> data;
     Context context;
    public ListdataAdapter(List<Person> dt,Context context1)
    {
        this.data=dt;
        this.context=context1;
        Log.e("Adapter"," size "+data.size());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        MyHolder myHolder=null;
        Log.e("Adapter"," size 2 "+data.size());
        if (convertView==null)
        {
            myHolder=new MyHolder();
            convertView=layoutInflater.inflate(R.layout.item,parent,false);
            myHolder.fname = (TextView) convertView.findViewById(R.id.tv_fname);
            myHolder.lname = (TextView) convertView.findViewById(R.id.tv_lname);
            myHolder.contact = (TextView) convertView.findViewById(R.id.tv_contact);

            convertView.setTag(myHolder);
        }
        else
        {
            myHolder= (MyHolder) convertView.getTag();
        }
        Person person=data.get(position);
        myHolder.fname.setText(person.getFname());
        myHolder.lname.setText(person.getLname());
        myHolder.contact.setText(person.getContact());


        return convertView;
    }

    public class MyHolder
    {
        TextView fname;
        TextView lname;
        TextView contact;
    }
}
