package com.danifitrianto.intermediaterecyclerview.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.utils.PreferencesHelper;

public class RegistrasiActivity extends AppCompatActivity {

    PreferencesHelper preferencesHelper;
    EditText etUser, etKeypass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());

        etUser = findViewById(R.id.etUser);
        etKeypass = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(view -> {
            String data[] = {etUser.getText().toString(),
                    etKeypass.getText().toString()};

            preferencesHelper.setUserData(data);

            Toast.makeText(this,"username : " + preferencesHelper.getUserData()[0] +
                    " password : " + preferencesHelper.getUserData()[1],Toast.LENGTH_LONG).show();
        });
    }
}