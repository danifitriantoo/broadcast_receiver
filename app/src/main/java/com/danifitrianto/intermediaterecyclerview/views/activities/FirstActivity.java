package com.danifitrianto.intermediaterecyclerview.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.utils.PreferencesHelper;

public class FirstActivity extends AppCompatActivity {

    private static final String KEY_INTENT = "PUT_EXTRA";
    private PreferencesHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        helper = PreferencesHelper.getInstance(getApplicationContext());

        TextView txtFirst = findViewById(R.id.txtFirst);

//        Intent i = getIntent();
//        String value = i.getStringExtra(KEY_INTENT);
        txtFirst.setText(helper.getNama());
    }
}