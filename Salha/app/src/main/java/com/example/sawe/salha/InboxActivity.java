//unused code
package com.example.sawe.salha;
/*
import android.app.PendingIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;


public class InboxActivity extends ActionBarActivity {
    TextView tv;
    PendingIntent sentPI, deliveredPI;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        tv=(TextView)findViewById(R.id.textView);

        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        Intent intent = getIntent();
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



        /*---link to SMSReceiver Class---
        Intent i = new Intent(this, SMSReceiver.class);*/

      /*  Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        tv.append("Body:"+message+"\n");
//* /
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inbox, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
