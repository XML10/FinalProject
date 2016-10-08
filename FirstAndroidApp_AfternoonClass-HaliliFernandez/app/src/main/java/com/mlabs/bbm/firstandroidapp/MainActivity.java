package com.mlabs.bbm.firstandroidapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        final ImageView imgview = (ImageView)findViewById(R.id.imageView);
        final EditText x1y1 = (EditText)findViewById(R.id.x1y1);
        final EditText x2y2 = (EditText)findViewById(R.id.x2y2);
        final EditText diff = (EditText)findViewById(R.id.diff);
        final EditText motion = (EditText)findViewById(R.id.motion);
        final EditText quad = (EditText)findViewById(R.id.quadrant);


        assert imgview != null;
        imgview.setOnTouchListener(new View.OnTouchListener() {
            float A = 0;
            float A1 = 0;
            float B = 0;
            float B1 = 0;
            float X = 0;
            float Y = 0;
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventA = motionEvent.getAction();
                String Motioned = "";
                switch (eventA) {
                    case MotionEvent.ACTION_DOWN:
                        x1y1.setText("");
                        x2y2.setText("");
                        diff.setText("");
                        motion.setText("");
                        quad.setText("");
                        A = motionEvent.getX();
                        B = motionEvent.getY();
                        x1y1.setText(A+","+B);
                        break;

                    case MotionEvent.ACTION_UP:
                        int O1 = imgview.getRight() / 2;
                        int O2 = imgview.getBottom() / 2;

                        A1= motionEvent.getX();
                        B1 = motionEvent.getY();
                        x2y2.setText(A1+","+B1);

                        if(A1 > O1 && B1 > O2){
                            quad.setText("IV");
                        }
                        if(A1 < O1 && B1 > O2){
                            quad.setText("III");
                        }
                        if(A1 < O1 && B1 < O2){
                            quad.setText("II");
                        }
                        if(A1 > O1 && B1 < O2){
                            quad.setText("I");
                        }

                        X = A - A1;
                        Y = B - B1;

                        if (X == 0 && Y == 0){
                            Motioned = "";
                        }
                        else{
                            if (A > A1){
                                Motioned += "Left to Right!?";
                            }
                            if (A < A1){
                                Motioned += "Right to Left!?";
                            }
                            if (B > B1){
                                Motioned += "Up to Down!?";
                            }
                            if (B < B1){
                                Motioned += "Down to Up!?";
                            }
                        }
                        break;
                }
                motion.setText(Motioned);
                diff.setText(X+","+Y);
                return true;
            }
        });
    }
}
