package com.example.rumahbersama.Activity;

import java.util.List;
public class ModelResponse {
    private int kode;
    private String pesan;
    private List<ModelRumahBersama> data;

    public ModelResponse(int kode, String pesan, List<ModelRumahBersama> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public int getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelRumahBersama> getData() {
        return data;
    }
}
