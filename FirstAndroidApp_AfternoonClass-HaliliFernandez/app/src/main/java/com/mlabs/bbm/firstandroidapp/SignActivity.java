package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class SignActivity extends AppCompatActivity{

    LogInCRUD logInCRUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Button buttonok = (Button) findViewById(R.id.btnok);

        final EditText etfirstname = (EditText) findViewById(R.id.ETfn);
        final EditText etlastname = (EditText) findViewById(R.id.ETln);
        final EditText etun = (EditText) findViewById(R.id.ETun);
        final EditText etemail = (EditText) findViewById(R.id.ETemail);
        final EditText etpass = (EditText) findViewById(R.id.ETpass);
        final EditText etcp = (EditText) findViewById(R.id.ETcp);

        logInCRUD = new LogInCRUD(this);
        logInCRUD = logInCRUD.open();

        assert buttonok != null;
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etemail != null && etpass != null && etfirstname != null && etlastname != null && etun !=null) {
                    if (Pattern.compile("[a-zA-Z]+").matcher(etfirstname.getText()).matches() && Pattern.compile("[a-zA-Z]+").matcher(etlastname.getText()).matches()) {

                        if (Pattern.compile("[([a-zA-Z0-9]+_?)+]").matcher(etun.getText()).matches()){
                            Toast.makeText(getBaseContext(), "Invalid Username", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            boolean loginun;
                            loginun = logInCRUD.getUsernameEntry(etun.getText().toString());
                            if (loginun == true){
                                if (Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9/.]+").matcher(etemail.getText()).matches()) {
                                    boolean loginemail;
                                    loginemail = logInCRUD.getEmailEntry(etemail.getText().toString());
                                    if (loginemail == true) {
                                        if (etpass.length() > 8) {
                                            String etpass1 = etpass.getText().toString();
                                            String etcp1 = etcp.getText().toString();
                                            if (etpass1.equals(etcp1)) {
                                                logInCRUD.insertEntry(String.valueOf(etun.getText()), String.valueOf(etpass.getText()), String.valueOf(etemail.getText()), String.valueOf(etfirstname.getText()), String.valueOf(etlastname.getText()));

                                                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                                                Intent a = new Intent(SignActivity.this, LogInActivity.class);
                                                startActivity(a);
                                                finish();
                                            } else {
                                                Toast.makeText(getBaseContext(), "Password Does not Match", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getBaseContext(), "Password too short", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(getBaseContext(), "Email is not Availabele", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getBaseContext(), "Invalid Email address", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getBaseContext(), "Username is not available", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Invalid First name or Last name", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please fill in the blank field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        logInCRUD.close();
    }
}