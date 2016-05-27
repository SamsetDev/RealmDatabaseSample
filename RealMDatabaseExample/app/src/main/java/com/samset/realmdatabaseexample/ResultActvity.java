package com.samset.realmdatabaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.samset.realmdatabaseexample.adapter.ListdataAdapter;
import com.samset.realmdatabaseexample.controller.RealmController;
import com.samset.realmdatabaseexample.model.Person;

import java.util.List;

import io.realm.Realm;

public class ResultActvity extends AppCompatActivity {
    private ListView listView;
    private Realm realm;
    private RealmController realmController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_actvity);
        realm = Realm.getDefaultInstance();
        realmController=new RealmController(realm);
        listView=(ListView) findViewById(R.id.list);
        List<Person> person= realmController.getAllData();
        ListdataAdapter listdataAdapter=new ListdataAdapter(person,ResultActvity.this);
        listView.setAdapter(listdataAdapter);

    }

}
