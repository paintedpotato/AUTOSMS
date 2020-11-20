package com.example.sawe.salha;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import android.content.IntentFilter;

//import android.database.Cursor;
import android.widget.TextView;

//import junit.framework.Assert;

import java.util.Random;

//
  //Created by Sawe on 1/27/2016.
 //


public class SMSReceiver extends BroadcastReceiver {


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int communicate = 1;
    private int count;
    
    private String body;

    private String reply;

    private String temp;

    public Context findContext;

    public String getReply() {      // This method is used when fetching sent messages in MainActivity
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*Assert.assertTrue(condition);
        logger.info(condition ? "PASS" : "FAIL");*/
        Log.d("MainActivity","an sms has been received");
//---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        //count = 0;
        count = getCount(); // initializing count from mainActivity's last recorded count
        //findContext = context;

// is this the right context?

        //DatabaseHelper db = new DatabaseHelper(findContext);


//10:16 11/12/19    LATEST COMMENT
        //communicate = intent.getIntExtra("COMMUNICATE",0);      // if I'm already
            // bundling up my intents, isn't using another new one adding to the existing bundle?

//This piece of comment below has been the missing piece of the puzzle, the last bug to be cleared

      /*  Intent w = new Intent(context, SService.class);
        w.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      */  //w.putExtra("message", message.getMessageBody());

        /*
        Intent j = new Intent("broadCastName");
        Intent k = new
                Intent("com.example.sawe.salha.MainActivity");
*/
        SmsMessage[] msgs;// = null;
        String str = "SMS from ";
        if (bundle != null) {
//---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (i == 0) {
                    //---get the sender address/phone number---
                    str += msgs[i].getOriginatingAddress();
                    str += ": ";
                }
//---get the message body---

                str += msgs[i].getMessageBody().toString();
            }
//---display the new SMS message---
            // Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

            // Log.d("SMSReceiver", str);

            /* my code*/


            /*String*/
            address = "";
            body = "";
            reply = "";

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (i == 0) {
                    //---get the sender address/phone number---
                    address += msgs[i].getOriginatingAddress();
                }
            }
// the first time I tried collecting the address and the body separately I went wrong, let's hope this will work
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                //---get the message body---

                body += msgs[i].getMessageBody();//.toString();

            }
            //---display the new SMS message---
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

            Log.d("SMSReceiver", str);

/*
            Object[] rawMsgs=(Object[])intent.getExtras().get("pdus");
            for (  Object raw : rawMsgs) {
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) raw);
                w.putExtra("message", msg.getMessageBody());
            }
*/

          /*w.putExtra("message", body);
            w.putExtra("address",address);
*/
            // here is the first porblem with my app: It starts new activities upon every
            // broadcast received, instead of staying a background silent service
            //context.startActivity(w);

            //          context.startService(w);

           /* System.out.println("from " + address);
            System.out.println("msg " + body);*/

            SmsManager sms = SmsManager.getDefault();

            //String a[] = {"not good", "bad", "I'm not doing good"};
            //for (int j=0; j<2; j++){
            int i;

            count = 1;
            switch (body) {
                case "hi":
                    reply = "how are you?";
                    sendSMS();
             //       db.addSMS(new SMSInbox(count, "name1", address, body + "\nResponse: " + reply + "\n"));
                    // P. S. Note the contacts are yet to be synchronized hence there is not a name listed

                    count++;
                    break;

          /*     case "not good":
                    Random r = new Random();
                    i = r.nextInt(3 - 1) + 1; // generates a random number btn 1-3 inclusive

                    if (i == 1) {
                        reply = "\uD83E\uDD14 What happened?";
                        db.addSMS(new SMSInbox(count,"Name1", address, body + "\nResponse: " + reply + "\n"));
                        
                        count++;
                        sendSMS();
                    } else if (i == 2) {
                        reply = "Here's a hug \uD83E\uDD17 \nI promise you it will get better";
                        db.addSMS(new SMSInbox(count,"Name1", address, body + "\nResponse: " + reply + "\n"));
                        
                        count++;
                        sendSMS();
                    } else {
                        reply = "Talk to me..";
                        db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n"));
                        
                        count++;
                        sendSMS();
                    }

                    break;

                case "I'm not doing good":
                    Random rip = new Random();
                    i = rip.nextInt(3 - 1) + 1; // generates a random number btn 1-3 inclusive

                    if (i == 1) {
                        reply = "\uD83E\uDD14 What happened?";
                        setAddress(address);
                        setReply(reply);
                        db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n"));

                        count++;
                        sendSMS();
                    } else if (i == 2) {
                        reply = "Here's a hug \uD83E\uDD17 \nI promise you it will get better";
                        setAddress(address);
                        setReply(reply);
                        db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n"));

                        count++;
                        sendSMS();
                    } else {
                        reply = "Talk to me..";
                        setAddress(address);
                        setReply(reply);
                        db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n"));
                        
                        count++;
                        sendSMS();
                    }

                    break;


                case "How are you?":
                    reply = "I'm good";
                    setAddress(address);
                    setReply(reply);
                    db.addSMS(new SMSInbox(count, "Name1", getAddress(), body + "\nResponse: " + reply + "\n"));

                    count++;
                    sendSMS();
                    break;
                //Talk mode
               /* case "I'm good":
                    reply = "How's Salha?";
                    setAddress(address);

                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n" + "\n"));

                    count++;
                    temp = reply;
                    sendSMS();

                    // I'll need a new Thread to complete this code
                    //Thread.sleep(2000);

                    //SystemClock.sleep(2000);
                    reply = "You're probably blushing right now\n" +
                            "Hihi, I know u are ;) :* <3";
                    setAddress(address);
                    setReply(temp + "\n" + reply + "\n");

                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n" + "\n"));

                    sendSMS();
                    break;
*//*

                case "How's everything":
                    reply = "It's all good";
                    setAddress(address);
                    setReply(reply);

                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n" + "\n"));

                    sendSMS();
                    count++;
                    break;
                case "hey":
                    reply = "Hey";
                    setAddress(address);
                    temp = reply;
                    sendSMS();

                    reply = "Why down?";
                    setReply(temp + "\n" + reply + "\n");

                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + getReply() + "\n" + "\n"));

                    sendSMS();
                    count++;
                    break;
                case "No, I'm just..":
                    reply = "What is it?";
                    setAddress(address);
                    setReply(reply);
                    sendSMS();
                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n" + "\n"));
                    count++;
                    break;

                case "Forget it":
                    reply = "\"What do you want with me?\"";
                    setAddress(address);
                    temp = reply;
                    // sms.sendTextMessage(address, null, "Don't give up :'(",
                    //       null, null);
                    sendSMS();

                    SystemClock.sleep(2000);

                    reply = "Charot";
                    sendSMS();

                    temp = temp + "\n" + reply + "\n";

                    SystemClock.sleep(10000);

                    reply = "\"What's the weather?\"";
                    sendSMS();

                    setReply(temp + "\n" + reply + "\n");
                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + getReply() + "\n" + "\n"));
                    count++;
                    break;

// Note for 2 responses the entire response will be sent once with two patches of reply
// and use getReply() method
                case "Sunny":
                    reply = "Oh no";
                    setAddress(address);
                    setReply(reply);


                    sendSMS();
                    temp = reply;

                    // sms.sendTextMessage(address, null, "Don't give up :'(",
                    //       null, null);
                    SystemClock.sleep(5000);
                    reply = "Nah";

                    sendSMS();

                    setReply(temp + "\n" + reply + "\n");
                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + getReply() + "\n" + "\n"));
                    count++;

                    break;
                case "Rainy":
                    reply = "Wait";
                    setAddress(address);
                    setReply(reply);
                    // sms.sendTextMessage(address, null, "Don't give up :'(",
                    //       null, null);

                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + reply + "\n" + "\n"));

                    sendSMS();
                    count++;
                    break;
                case "Cloudy":

                    reply = "For real?";
                    // sms.sendTextMessage(address, null, "Don't give up :'(",
                    //       null, null);
                    setAddress(address);
                    setReply(reply);
                    temp = reply;


                    sendSMS();

                    SystemClock.sleep(10000);

                    reply = "..";

                    setAddress(address);


                    sendSMS();


                    setReply(temp + "\n" + reply + "\n");
                    db.addSMS(new SMSInbox(count, "Name1", address, body + "\nResponse: " + getReply() + "\n" + "\n"));

                    count++;
                    break;

*/


            }
            setCount(count);
            //sms.sendTextMessage(address, null, /*reply*/ "niambie",
              //      null, null);

        }
    }


    private void sendSMS() {
// To pass a non-static variable or method to a static method, one must make an object
                                    //MainActivity object = new MainActivity();
        //SMSInbox obje = new SMSInbox(1, "L", "999", "hello");/*Thank you my LORD*/

        /*if(communicate == 1){                         //object.getButtonIsPressed() == 0) {
            //DatabaseHelper db = new DatabaseHelper(findContext);
            SmsManager sms = SmsManager.getDefault();

            sms.sendTextMessage(address, null, *//*reply*//* reply,
                    null, null);

// Do I want to add the following code here?
// Because it will mean changing the structure of how temp works and change
            //db.addSMS(new SMSInbox("Name1", address, body + "\nResponse: " + reply + "\n"));
        }else{SmsManager sms = SmsManager.getDefault();

            sms.sendTextMessage(address, null, *//*reply*//* reply,
                    null, null);}*/

        //else{

                //DatabaseHelper db = new DatabaseHelper(findContext);
                //SmsManager sms = SmsManager.getDefault();

                //sms.sendTextMessage(getAddress(), null, reply,
                //        null, null);
        //}
    }

}
