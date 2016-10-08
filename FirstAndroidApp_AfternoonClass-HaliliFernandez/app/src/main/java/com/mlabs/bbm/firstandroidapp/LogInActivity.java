package com.mlabs.bbm.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 7/20/2016.
 */
public class LogInActivity extends Activity{

    LogInCRUD logInCRUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);
        Button btnlogin = (Button) findViewById(R.id.btn_login);
        Button show = (Button) findViewById(R.id.show);
        TextView btnsignup = (TextView) findViewById(R.id.sign_up);
        final EditText un = (EditText) findViewById(R.id.username);
        final EditText pwd = (EditText) findViewById(R.id.password);

        assert btnsignup != null;
        btnsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), SignActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        logInCRUD = new LogInCRUD(this);
        logInCRUD = logInCRUD.open();

        assert btnlogin != null;
        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String pas = pwd.getText().toString();
                String uns = un.getText().toString();
                String storedPassword = logInCRUD.getSinlgeEntry(uns);

                // check if the Stored password matches with  Password entered by user
                if(pas.equals(storedPassword))
                {
                    Toast.makeText(LogInActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                }
                else
                {
                    Toast.makeText(LogInActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventA = motionEvent.getAction();
                final int cursor = pwd.getSelectionStart();
/*                if (eventA == motionEvent.ACTION_UP) {
                    editpass.setTransformationMethod(new PasswordTransformationMethod());
                } else if (eventA == motionEvent.ACTION_CANCEL) {
                    editpass.setTransformationMethod(new PasswordTransformationMethod());
                } else if (eventA == motionEvent.ACTION_DOWN) {
                    editpass.setTransformationMethod(null);
                }
                return true; */

                switch (eventA) {
                    case MotionEvent.ACTION_DOWN:
                        pwd.setTransformationMethod(null);
                        pwd.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_UP:
                        pwd.setTransformationMethod(new PasswordTransformationMethod());
                        pwd.setSelection(cursor);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        pwd.setTransformationMethod(new PasswordTransformationMethod());
                        pwd.setSelection(cursor);
                        break;
                }
                return true;
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