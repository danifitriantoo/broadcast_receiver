package com.danifitrianto.intermediaterecyclerview.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.services.TimeServices;

public class TimerActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    TextView tvTimer;
    boolean access = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tvTimer = findViewById(R.id.tvTimer);

        btnStart.setOnClickListener(view -> {
            registerReceiver(broadcastReceiver, new IntentFilter(TimeServices.BROADCAST_ACTION));
            Intent serviceIntent = new Intent(getApplicationContext(),TimeServices.class);
            startService(serviceIntent);
        });

        btnStop.setOnClickListener(view -> {
            Intent serviceIntent = new Intent(getApplicationContext(),TimeServices.class);
            stopService(serviceIntent);
            unregisterReceiver(broadcastReceiver);
            access = false;

            tvTimer.setText("" + 0 + ":" + String.format("%02d",0));
        });
    }

    private void updateUi(Intent intent) {
        int mins = intent.getIntExtra("mins",0);
        int secs = intent.getIntExtra("secs",0);

        tvTimer.setText("" + mins + ":" + String.format("%02d",secs));
    }

    private boolean isMyServiceRunning(Class<TimeServices> timeServices) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo serviceInfo :
                manager.getRunningServices(Integer.MAX_VALUE)) {
            if(timeServices.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUi(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        if(access) {
            registerReceiver(broadcastReceiver, new IntentFilter(TimeServices.BROADCAST_ACTION));
            Intent serviceIntent = new Intent(getApplicationContext(),TimeServices.class);
            startService(serviceIntent);
        }

    }
}