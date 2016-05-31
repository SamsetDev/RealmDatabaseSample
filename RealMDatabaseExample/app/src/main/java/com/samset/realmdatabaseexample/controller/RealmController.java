package com.samset.realmdatabaseexample.controller;

import android.util.Log;

import com.samset.realmdatabaseexample.model.Person;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by samset on 27/05/16.
 */
public class RealmController {

    Realm realm;
    public RealmController(Realm rl) {
        this.realm = rl;
    }


    public void addData(String fname, String lname, String contact) {
        long i = PrimaryKeyFactory.getInstance().nextKey(Person.class);
        Person person = realm.createObject(Person.class);
        person.setId(i);
        // person.setId((int) (System.currentTimeMillis() / 1000));
        person.setFname(fname);
        person.setLname(lname);
        person.setContact(contact);

        realm.beginTransaction();
        realm.copyToRealm(person);
        realm.commitTransaction();

        Log.e("Controller", " key " + i);

    }

    public List<Person> getAllData() {
        List<Person> resultdata = new ArrayList<>();
        realm.beginTransaction();
        RealmResults<Person> results = realm.where(Person.class).findAll();
        results.sort("id", Sort.DESCENDING);
        for (int i = 0; i < results.size(); i++) {
            Person person = new Person();
            person.setId(results.get(i).getId());
            person.setFname(results.get(i).getFname());
            person.setLname(results.get(i).getLname());
            person.setContact(results.get(i).getContact());

            resultdata.add(person);
        }
        realm.commitTransaction();
        Log.e("Controller", " all data " + resultdata.size());

        return resultdata;
    }

    public void deleteById(long id) {
        RealmResults<Person> dataDesults = realm.where(Person.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataDesults.remove(0);
        dataDesults.removeLast();
        dataDesults.clear();
        realm.commitTransaction();
    }

    public void updatePerson(long id, String fname, String lname, String contact) {
        realm.beginTransaction();
        Person person = realm.where(Person.class).equalTo("id", id).findFirst();
        person.setFname(fname);
        person.setLname(lname);
        person.setContact(contact);
        realm.commitTransaction();
        Log.e("Updated : ", "" + fname);

    }

}
