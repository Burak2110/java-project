package com.example.vize2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.telephony.SmsManager;
import android.app.PendingIntent;
import android.widget.Toast;

import android.os.Bundle;

public class Sms extends AppCompatActivity {

    private EditText userNumber,userMesagge;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        userNumber = findViewById(R.id.phoneNumber);
        userMesagge = findViewById(R.id.messageText);
        buttonSend = findViewById(R.id.send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Sms.this, android.Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else {

                    ActivityCompat.requestPermissions(Sms.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
            }
        });
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else {
            Toast.makeText(this,"izin verilmedi.",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(){
        String phone = userNumber.getText().toString();
        String message = userMesagge.getText().toString();

        if (!phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);
            Toast.makeText(this,"gönderi başarılı",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"gönderim başarısız",Toast.LENGTH_SHORT).show();Ğ
        }
    }
}