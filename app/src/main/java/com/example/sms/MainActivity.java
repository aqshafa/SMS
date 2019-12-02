package com.example.sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button shareIntent;
    private Button send;
    private EditText phoneNo;
    private EditText messageBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNo = findViewById(R.id.mobileNumber);
        messageBody = findViewById(R.id.smsBody);

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNo.getText().toString();
                String sms = messageBody.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, sms, null, null);

                    Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "SMS Failed, Please try again later!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        shareIntent = findViewById(R.id.sendViaIntent);
        shareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.putExtra("sms_body", messageBody.getText().toString());
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(sendIntent);
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS Failed, Please try again later!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
