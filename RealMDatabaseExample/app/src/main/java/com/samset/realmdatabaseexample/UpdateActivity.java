package com.samset.realmdatabaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.samset.realmdatabaseexample.controller.RealmController;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {

    private Intent intent;
    private String fname;
    private String lname;
    private String contact;
    private long id;
    private EditText etfname, etlname, contactno;
    private TextView register;
    private AppCompatButton update,delete;
    private RealmController realmController;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        realm = Realm.getDefaultInstance();
        realmController=new RealmController(realm);
        intent=getIntent();
        fname=intent.getStringExtra("fname");
        lname=intent.getStringExtra("lname");
        contact=intent.getStringExtra("contact");
        id=intent.getLongExtra("id",0);


        etfname=(EditText)findViewById(R.id.input_etfname);
        etlname=(EditText)findViewById(R.id.input_etlname);
        contactno=(EditText)findViewById(R.id.input_etcontact);
        update=(AppCompatButton) findViewById(R.id.btn_update);
        delete=(AppCompatButton) findViewById(R.id.btn_delete);
        register=(TextView)findViewById(R.id.link_reg);

        if (!fname.isEmpty() && !lname.isEmpty() && !contact.isEmpty())
        {
            etfname.setText(""+fname);
            etlname.setText(""+lname);
            contactno.setText(""+contact);

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String fname= etfname.getText().toString();
                String lname=  etlname.getText().toString();
                String contact= contactno.getText().toString();

                realmController.updatePerson(id,fname,lname,contact);
                startActivity(new Intent(UpdateActivity.this,ResultActvity.class));

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realmController.deleteById(id);
                startActivity(new Intent(UpdateActivity.this,ResultActvity.class));
            }
        });

    }


}
