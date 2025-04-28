package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Jadwal {
    private int id;
    private LocalDate tanggal;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    private Film film;
    private Studio studio;
    private double harga;
    private List<String> kursiTersedia;

    public Jadwal(int id, LocalDate tanggal, LocalTime waktuMulai, LocalTime waktuSelesai,
            Film film, Studio studio, double harga) {
        this.id = id;
        this.tanggal = tanggal;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.film = film;
        this.studio = studio;
        this.harga = harga;
        initKursiTersedia(studio.getKapasitas());
    }

    private void initKursiTersedia(int kapasitas) {
        kursiTersedia = new ArrayList<>();
        for (int i = 1; i <= kapasitas; i++) {
            kursiTersedia.add("A" + i);
        }
    }

    public boolean pesanKursi(String kursi) {
        return kursiTersedia.remove(kursi);
    }

    // Getters
    public List<String> getKursiTersedia() {
        return kursiTersedia;
    }

    public double getHarga() {
        return harga;
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public LocalTime getWaktuMulai() {
        return waktuMulai;
    }

    public LocalTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public Film getFilm() {
        return film;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public void setWaktuMulai(LocalTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public void setWaktuSelesai(LocalTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}