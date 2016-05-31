package com.samset.realmdatabaseexample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.samset.realmdatabaseexample.R;
import com.samset.realmdatabaseexample.UpdateActivity;
import com.samset.realmdatabaseexample.listeners.OnItemClick;
import com.samset.realmdatabaseexample.model.Person;

import java.util.List;

/**
 * Created by samset on 27/05/16.
 */
public class ListdataAdapter extends BaseAdapter {
    List<Person> data;
    static Context context;

    public ListdataAdapter(List<Person> dt, Context context1) {
        this.data = dt;
        this.context = context1;
        Log.e("Adapter", " size " + data.size());
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
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        MyHolder myHolder = null;
        Log.e("Adapter", " size 2 " + data.size());
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            myHolder.fname = (TextView) convertView.findViewById(R.id.tv_fname);
            myHolder.lname = (TextView) convertView.findViewById(R.id.tv_lname);
            myHolder.contact = (TextView) convertView.findViewById(R.id.tv_contact);
            myHolder.cardView = (CardView) convertView.findViewById(R.id.main);

            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        final Person person = data.get(position);
        myHolder.fname.setText(person.getFname());
        myHolder.lname.setText(person.getLname());
        myHolder.contact.setText(person.getContact());

        final long id = person.getId();

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, UpdateActivity.class).putExtra("id", id).
                        putExtra("fname", person.getFname()).putExtra("lname", person.getLname()).putExtra("contact", person.getContact()));
            }
        });


        return convertView;
    }


    public class MyHolder {
        TextView fname;
        TextView lname;
        TextView contact;
        CardView cardView;
    }
}
