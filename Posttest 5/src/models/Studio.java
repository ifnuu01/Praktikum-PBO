package models;

import java.util.ArrayList;
import java.util.List;

public class Studio {
    private int nomor;
    private int kapasitas;
    private List<Jadwal> jadwalList;

    public Studio(int nomor, int kapasitas) {
        this.nomor = nomor;
        this.kapasitas = kapasitas;
        this.jadwalList = new ArrayList<>();
    }

    // Getters
    public int getNomor() {
        return nomor;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public List<Jadwal> getJadwalList() {
        return jadwalList;
    }
}