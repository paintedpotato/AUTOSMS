package com.example.sawe.salha;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

/*
public class SalhaService extends Service {
    public SalhaService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT"), 0),
                deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED"), 0);
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
*/