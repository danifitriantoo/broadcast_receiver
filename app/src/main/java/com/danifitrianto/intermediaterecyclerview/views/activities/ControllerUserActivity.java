package com.danifitrianto.intermediaterecyclerview.views.activities;

import static android.accounts.AccountManager.KEY_INTENT;
import static com.danifitrianto.intermediaterecyclerview.setups.AppApplication.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.Mahasiswa;

public class ControllerUserActivity extends AppCompatActivity {

    private Button btnAction;
    private int key_id;
    private EditText etAlamat, etKejuruan, etNama, etNim;
    private boolean accessType = false;
    Mahasiswa mahasiswa = new Mahasiswa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        btnAction = findViewById(R.id.btnInsert);
        etAlamat = findViewById(R.id.etAlamat);
        etKejuruan = findViewById(R.id.etKejuruan);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);


        try {
            checkData(getIntent().getStringExtra("nama"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAction.setOnClickListener(view -> {
            if(!accessType) {
                insertData();
            } else {
                updateData();
            }
        });
    }

    private void updateData() {
        if(validation()) {
            db.userDao().updateAll(mahasiswa);
            Intent i = new Intent(ControllerUserActivity.this,UserActivity.class);
            i.putExtra(KEY_INTENT,etNama.getText().toString());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    private void insertData() {
        if(validation()) {
            db.userDao().insertAll(mahasiswa);
            Intent i = new Intent(ControllerUserActivity.this,UserActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    private boolean validation() {
        if(!etAlamat.getText().toString().isEmpty() && !etKejuruan.toString().isEmpty() &&
                !etNama.getText().toString().isEmpty() && !etNim.getText().toString().isEmpty()) {

            if(accessType) {
                mahasiswa.setId(key_id);
            }

            mahasiswa.setAlamat(etAlamat.getText().toString());
            mahasiswa.setNama(etNama.getText().toString());
            mahasiswa.setKejuruan(etKejuruan.getText().toString());
            mahasiswa.setNim(etNim.getText().toString());

            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Mohon masukan dengan benar!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void checkData(String keyValue) {
        if(!keyValue.isEmpty()) {
            etNama.setText(getIntent().getStringExtra("nama"));
            etAlamat.setText(getIntent().getStringExtra("alamat"));
            etKejuruan.setText(getIntent().getStringExtra("kejuruan"));
            etNim.setText(getIntent().getStringExtra("nim"));
            key_id = getIntent().getIntExtra("id",0);

            accessType = true;
            btnAction.setText(R.string.btn_update);
        } else {
            btnAction.setText(R.string.btn_insert);
        }
    }
}