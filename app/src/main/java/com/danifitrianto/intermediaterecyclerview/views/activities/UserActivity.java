package com.danifitrianto.intermediaterecyclerview.views.activities;

import static com.danifitrianto.intermediaterecyclerview.setups.AppApplication.db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.danifitrianto.intermediaterecyclerview.R;
import com.danifitrianto.intermediaterecyclerview.setups.ItemClickListener;
import com.danifitrianto.intermediaterecyclerview.setups.adapters.RecyclerviewUserAdapter;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.AppDatabase;
import com.danifitrianto.intermediaterecyclerview.setups.rooms.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    ItemClickListener rvListener;
    RecyclerView recyclerView;
    RecyclerviewUserAdapter adapter;
    List<Mahasiswa> listMahasiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerView);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();
    }

    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mahasiswa").allowMainThreadQueries().build();
        listMahasiswa = db.userDao().getAll();
    }

    private void setAdapter() {
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        rvListener = new ItemClickListener<Mahasiswa>() {

            @Override
            public void onClick(Mahasiswa model) {
                Intent i = new Intent(UserActivity.this,ControllerUserActivity.class);
                i.putExtra("id",model.getId());
                i.putExtra("nama",model.getNama());
                i.putExtra("nim",model.getNim());
                i.putExtra("alamat",model.getAlamat());
                i.putExtra("kejuruan",model.getKejuruan());
                startActivity(i);
            }

            @Override
            public boolean onLongClick(@Nullable Mahasiswa model) {
                return true;
            }

        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerviewUserAdapter(this,listMahasiswa,rvListener);

    }

}