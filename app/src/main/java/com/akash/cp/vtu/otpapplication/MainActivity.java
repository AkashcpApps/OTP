package com.akash.cp.vtu.otpapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    EditText et1, et2, et3, et4, et5, et6;
    ArrayList<Character> i;
    private View view;
    /*private MainActivity(View view)
    {
        this.view = view;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAndRequestPermissions();
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);
        et6 = (EditText) findViewById(R.id.et6);
        findViewById(R.id.sendMsg_ID).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et1.getText())|| TextUtils.isEmpty(et2.getText())|| TextUtils.isEmpty(et3.getText())|| TextUtils.isEmpty(et4.getText())|| TextUtils.isEmpty(et5.getText())|| TextUtils.isEmpty(et6.getText()))

                {
                    Toast.makeText(MainActivity.this, " please enter all the field", Toast.LENGTH_SHORT).show();
                }
                else {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(et1.getText());
                    stringBuilder.append(et2.getText());
                    stringBuilder.append(et3.getText());
                    stringBuilder.append(et4.getText());
                    stringBuilder.append(et5.getText());
                    stringBuilder.append(et6.getText());


                    String finalOtp = stringBuilder.toString();
                    Toast.makeText(MainActivity.this, " " + finalOtp, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                String message = intent.getStringExtra("message");
                String sender = intent.getStringExtra("Sender");
                char[] otp = message.replaceAll("\\D+", "").toCharArray();
                et1.setText(otp[0] + "");
                et2.setText(otp[1] + "");
                et3.setText(otp[2] + "");
                et4.setText(otp[3] + "");
                et5.setText(otp[4] + "");
                et6.setText(otp[5] + "");


            }
        }
    };

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            int receiveSMS = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS);
            int readSMS = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);
            }
            if (readSMS != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.READ_SMS);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
                return false;
            }
            return true;
        }
        return true;

    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

   /* @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch(view.getId())
        {

            case R.id.et1:
                if(text.length()==1)
                    et2.requestFocus();
                break;
            case R.id.et2:
                if(text.length()==1)
                    et3.requestFocus();
                else if(text.length()==0)
                    et1.requestFocus();
                break;
            case R.id.et3:
                if(text.length()==1)
                    et4.requestFocus();
                else if(text.length()==0)
                    et2.requestFocus();
                break;
            case R.id.et4:
                if(text.length()==0)
                    et5.requestFocus();
                else if(text.length()==0)
                    et3.requestFocus();
                break;
            case R.id.et5:
                if(text.length()==0)
                    et6.requestFocus();
                else if(text.length()==0)
                    et4.requestFocus();
                break;
            *//*case R.id.et6:
                if(text.length()==0)
                    et3.requestFocus();
                break;*//*
        }
*/
    }
