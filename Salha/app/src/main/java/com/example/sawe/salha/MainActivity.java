//                  2 new stuff to do: Algorithm.
package com.example.sawe.salha;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
//import androidx.appcompat.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.telephony.SmsManager;
import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity { //5/12/19: ActionBar to AppCompat
    TextView tv;
// used to control textView2
    TextView textView2;
    public int buttonIsPressed;

    public int getButtonIsPressed() {
        return buttonIsPressed;
    }
    //public void onRequestPermissionsResult();

    /*private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted));

            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            enableSmsButton();
        }
    }*/
    public void setButtonIsPressed(int buttonIsPressed) {
        this.buttonIsPressed = buttonIsPressed;
    }
    String text;

    //SQLiteDatabase db;

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    //NEW
    //private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIsPressed = 100;
        SMSReceiver newObject = new SMSReceiver();
        newObject.setCount(0);

        tv=(TextView)findViewById(R.id.textView);

        textView2 = (TextView) findViewById(R.id.textView2);

        DatabaseHelper db = new DatabaseHelper(this);

        db.addSMS(new SMSInbox("11111", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*2, */"11111", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*3, */"11111", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*4, */"11111", null, null));        // Assuming all messages would already be there

        List<SMSInbox> smsInboxes = db.getAllSMSes();

        for(SMSInbox s : smsInboxes){
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
            String log = "ID: " + s.getId() + ", NAME: " + s.getName() + ", NUMBER: " + s.getPhone_number() + "\n";
            text = text + log;
        }

        textView2.setText(text);//*/
        tv.setText(text);
        /*        db= openOrCreateDatabase("Mydb", MODE_PRIVATE, null);
        db.execSQL("create table if not exists mytable(Body, varchar)");        */

        //---link to Activity2---
        //Intent i = new Intent("com.example.sawe.InboxActivity");
        //startActivity(i);


        // Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
        //Toast.makeText(message,
        //        Toast.LENGTH_SHORT).show();

/*        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1);
        } else {
            // Permission already granted. Enable the SMS button.

        }
*/        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

    }


        @Override
        public void onResume () {
            super.onResume();

           // final DatabaseHelper db = new DatabaseHelper(this);


//---create the BroadcastReceiver when the SMS is sent---
            smsSentReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode()) {
                        case AppCompatActivity.RESULT_OK:

// seems like I can't use db.addSMS inside a broadcastReceiver?

                            Toast.makeText(getBaseContext(), "SMS sent",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            Toast.makeText(getBaseContext(), "Generic failure",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_NO_SERVICE:
                            Toast.makeText(getBaseContext(), "No service",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            Toast.makeText(getBaseContext(), "Null PDU",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            Toast.makeText(getBaseContext(), "Radio off",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };
//---create the BroadcastReceiver when the SMS is delivered---
            smsDeliveredReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {
                    switch (getResultCode()) {
                        case AppCompatActivity.RESULT_OK:
                            Toast.makeText(getBaseContext(), "SMS delivered",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case AppCompatActivity.RESULT_CANCELED:
                            Toast.makeText(getBaseContext(), "SMS not delivered",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };


//---register the two BroadcastReceivers---
            registerReceiver(smsSentReceiver, new IntentFilter(SENT));
            registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));}




    public void buttonStart(View view){


    }
    public void onClickWhatsApp(View view) {

        PackageManager pm = getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    public void buttonStop (View view){


    }

    public void ton (View view){

        DatabaseHelper db = new DatabaseHelper(this);

        db.addSMS(new SMSInbox("2221", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*2, */"112222", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*3, */"122211", null, null));        // Assuming all messages would already be there
        db.addSMS(new SMSInbox(/*4, */"12211", null, null));        // Assuming all messages would already be there

        List<SMSInbox> smsInboxes = db.getAllSMSes();

        for(SMSInbox s : smsInboxes){
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
            String log = "ID: " + s.getId() + ", NAME: " + s.getName() + ", NUMBER: " + s.getPhone_number() + "\n";
            text = text + log;
        }

        textView2.setText(text);//*/
        tv.setText(text);
        //List<SMSInbox> smsInboxes = db.getAllSMSes();

//        for(SMSInbox s : smsInboxes){
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
//            String log = "ID: " + s.getId() + ", NAME: " + s.getName() + ", NUMBER: " + s.getPhone_number() + "\n";
//            text = text + log;
//        }
    }
    public void communicate (View view){

        if(buttonIsPressed == 0 || buttonIsPressed == 100){
            Toast.makeText(this,"Button ON",Toast.LENGTH_SHORT)
                    .show();

            setButtonIsPressed(1);
                                // above loc isn't passing any data to receiver cause the receiver is being initialized
                                // to zero upon every running of the class.
            // the value being passed for true is 1.
            //Intent broadcastedIntent = new Intent(this, SMSReceiver.class);
            //broadcastedIntent.putExtra("COMMUNICATE",1);
            //sendBroadcast(broadcastedIntent);

        }else if(buttonIsPressed == 1){
            Toast.makeText(this,"Button OFF",Toast.LENGTH_SHORT)
                    .show();

            setButtonIsPressed(0);
            // the value being passed for false (default value) is 0.

            //Intent broadcastedIntent = new Intent(this, SMSReceiver.class);
            //broadcastedIntent.putExtra("COMMUNICATE",0);
            //sendBroadcast(broadcastedIntent);
        }
    }

/*    public void onRequestPermissionsResult(){
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase
                        (Manifest.permission.SEND_SMS)
                        && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable sms button.
                    enableSmsButton();
                } else {
                    // Permission denied.
                    Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this,
                            getString(R.string.failure_permission),
                            Toast.LENGTH_LONG).show();
                    // Disable the sms button.
                    disableSmsButton();
                }
            }
        }
    }
    /*
    public void insert(View v)
    {
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        db.execSQL("insert into mytable values('"+message+"')");

    }*/
    @Override
    public void onPause() {
        super.onPause();
//---unregister the two BroadcastReceivers---
        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);
    }
}
