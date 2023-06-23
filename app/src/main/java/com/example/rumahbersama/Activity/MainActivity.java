package com.example.rumahbersama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwiki.rsplg.R;
import com.example.rumahbersama.API.APIRequestData;
import com.example.rumahbersama.API.RetroServer;
import com.example.rumahbersama.Adapter.AdapterRumahBersama;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvRumahBersama;
    private ProgressBar pbRumahBersama;
    private RecyclerView.Adapter adRumahBersama;
    private RecyclerView.LayoutManager lmRumahBersama;
    private List<ModelRumahBersama> listRumahBersama = new ArrayList<>();
    private FloatingActionButton fabTambahData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRumahBersama = findViewById(R.id.rv_rumah_sakit);
        pbRumahBersama = findViewById(R.id.pb_rumah_sakit);
        fabTambahData = findViewById(R.id.fab_tambah_data);

        lmRumahBersama = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvRumahBersama.setLayoutManager(lmRumahBersama);
        fabTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveRumahBersama();
    }

    public void retrieveRumahBersama(){
        pbRumahBersama.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        ((Call<?>) proses).enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listRumahBersama = response.body().getData();

                adRumahBersama = new RecyclerView.Adapter(MainActivity.this, listRumahBersama);
                rvRumahBersama.setAdapter(adRumahBersama);
                adRumahBersama.notifyDataSetChanged();

                pbRumahBersama.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal menghubungi server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbRumahBersama.setVisibility(View.GONE);
            }
        });

    }

}
