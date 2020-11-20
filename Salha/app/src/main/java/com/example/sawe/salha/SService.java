package com.example.sawe.salha;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
//import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.content.Intent.getIntent;
/*
public class SService extends Service {
/*
    PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0), 
            deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);

    //Intent intent = getIntent();


    /*public SService() {
    }
2/
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    //boolean paused = false;


    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this,"Intercepting incoming messages",
                Toast.LENGTH_LONG).show();

        
        /*paused = false;
        Thread initBkgdThread = new Thread(new Runnable() {
            @Override
            public void run() {
                send_sms();
            }
        });
        initBkgdThread.start();3/
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //Intent intent = getIntent();
        //Toast.makeText(this,"Intercepting incoming messages",
        /*        Toast.LENGTH_LONG).show();

        String message = intent.getStringExtra("message");
        String address = intent.getStringExtra("address");

        SmsManager sms = SmsManager.getDefault();
        switch (message) {
            case "hi":
                sms.sendTextMessage(address, null, "How are you?",
                        sentPI, deliveredPI);
                break;
            case "How are you?":
                sms.sendTextMessage(address, null, "I'm good",
                        sentPI, deliveredPI);
                break;
            case "How's everything":
                sms.sendTextMessage(address, null, "It's all good",
                        sentPI, deliveredPI);
                break;
            case "hey":
                sms.sendTextMessage(address, null, "Hey",
                        sentPI, deliveredPI);
                sms.sendTextMessage(address, null, "Why down?",
                        sentPI, deliveredPI);
                break;
            case "No, I'm just..":
                sms.sendTextMessage(address, null, "What is it?",
                        sentPI, deliveredPI);
                break;
            case "Forget it":
                sms.sendTextMessage(address, null, "Don't give up :'(",
                        sentPI, deliveredPI);
                break;

        }
return START_NOT_STICKY;
        /4/return START_STICKY;
    }
    /*
    private Intent getIntent() {
     5/
    }

    //@Override
    public void onDestroy() {
        //super.onDestroy();
        //Toast.makeText(this, "End of intercept",
        //        Toast.LENGTH_LONG).show();
        //paused = true;

        //at this point, I don't know what the problem was, just trying random fixes in the hope that
        // one of them is the correct one #trialno.7
    }
/*
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void send_sms(){
        /*Intent intent = new Intent();
        String message = intent.getStringExtra("message");
        String address = intent.getStringExtra("address");

        SmsManager sms = SmsManager.getDefault();
        switch (message) {
            case "hi":
                sms.sendTextMessage(address, null, "How are you?",
                        sentPI, deliveredPI);
                break;
            case "How are you?":
                sms.sendTextMessage(address, null, "I'm good",
                        sentPI, deliveredPI);
                break;
            case "How's everything":
                sms.sendTextMessage(address, null, "It's all good",
                        sentPI, deliveredPI);
                break;
            case "hey":
                sms.sendTextMessage(address, null, "Hey",
                        sentPI, deliveredPI);
                sms.sendTextMessage(address, null, "Why down?",
                        sentPI, deliveredPI);
                break;
            case "No, I'm just..":
                sms.sendTextMessage(address, null, "What is it?",
                        sentPI, deliveredPI);
                break;
            case "Forget it":
                sms.sendTextMessage(address, null, "Don't give up :'(",
                        sentPI, deliveredPI);
                break;

        }
    }*/
//}
