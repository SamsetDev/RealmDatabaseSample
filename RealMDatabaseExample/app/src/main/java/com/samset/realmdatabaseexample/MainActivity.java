package com.samset.realmdatabaseexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.samset.realmdatabaseexample.adapter.ListdataAdapter;
import com.samset.realmdatabaseexample.controller.PrimaryKeyFactory;
import com.samset.realmdatabaseexample.controller.RealmController;
import com.samset.realmdatabaseexample.model.Person;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    private EditText fname, lname, contactno;
    private AppCompatButton btncreate,btnget;
    private RealmController realmController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        realmController=new RealmController(realm);
        PrimaryKeyFactory.getInstance().initialize(realm);
        setView();

    }


    private void setView() {

        fname=(EditText)findViewById(R.id.input_fname);
        lname=(EditText)findViewById(R.id.input_lname);
        contactno=(EditText)findViewById(R.id.input_contact);
        btncreate=(AppCompatButton) findViewById(R.id.btn_signup);
        btnget=(AppCompatButton) findViewById(R.id.btn_get);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ResultActvity.class));


            }
        });
    }

    private void signup()
    {
        if (!validate())
        {
           onSignupFailed();
            return;
        }

        btncreate.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        btncreate.setEnabled(true);
        String strfname = fname.getText().toString();
        String strlname = lname.getText().toString();
        String strcontact = contactno.getText().toString();

        realmController.addData(strfname,strlname,strcontact);
        Log.e("Main"," Success");

        setResult(RESULT_OK, null);
        //finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btncreate.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String name = fname.getText().toString();
        String lanme = lname.getText().toString();
        String contact = contactno.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            fname.setError("at least 3 characters");
            valid = false;
        } else {
            fname.setError(null);
        }

        if (lanme.isEmpty() || lname.length() < 3) {
            lname.setError("Enter last name");
            valid = false;
        } else {
            lname.setError(null);
        }

        if (contact.isEmpty() || contact.length() < 10 || contact.length() > 10) {
            contactno.setError("Enter valid no.");
            valid = false;
        } else {
            contactno.setError(null);
        }

        return valid;
    }
}
