package com.samset.realmdatabaseexample.controller;

import android.util.Log;

import com.samset.realmdatabaseexample.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by samset on 27/05/16.
 */
public class RealmController {

    Realm realm;

    public RealmController(Realm rl)
    {
        this.realm=rl;
    }


    public void addData(String fname,String lname,String contact)
    {
      //  Log.e("Controller"," Primary key "+getprimary());



        realm.beginTransaction();
        long i= PrimaryKeyFactory.getInstance().nextKey(Person.class);
        Person person=realm.createObject(Person.class);
        person.setId(i);
        person.setFname(fname);
        person.setLname(lname);
        person.setContact(contact);
        realm.commitTransaction();

        Log.e("Controller"," key "+i);

    }

    public List<Person> getAllData()
    {
        List<Person> resultdata=new ArrayList<>();
        realm.beginTransaction();
        RealmResults<Person> results=realm.where(Person.class).findAll();

        for (int i=0;i<results.size();i++)
        {
            Person person=new Person();
            person.setFname(results.get(i).getFname());
            person.setLname(results.get(i).getLname());
            person.setContact(results.get(i).getContact());

            resultdata.add(person);
        }

        Log.e("Controller"," all data "+resultdata.size());

        return resultdata;
    }




}
