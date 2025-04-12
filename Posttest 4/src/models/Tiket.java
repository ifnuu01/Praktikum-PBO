package models;

import java.time.LocalDateTime;

public class Tiket {
    private int id;
    private Customer customer;
    private Jadwal jadwal;
    private String kursi;
    private LocalDateTime waktuPemesanan;
    private boolean isPaid;

    public Tiket(int id, Customer customer, Jadwal jadwal, String kursi) {
        this.id = id;
        this.customer = customer;
        this.jadwal = jadwal;
        this.kursi = kursi;
        this.waktuPemesanan = LocalDateTime.now();
        this.isPaid = false;
    }

    // Getters & Setters
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public String getKursi() {
        return kursi;
    }

    public LocalDateTime getWaktuPemesanan() {
        return waktuPemesanan;
    }

    public boolean isPaid() {
        return isPaid;
    }
}