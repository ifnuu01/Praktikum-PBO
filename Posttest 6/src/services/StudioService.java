package services;

import models.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudioService {
    private List<Studio> studios;
    private List<Jadwal> jadwals;
    private int lastJadwalId = 0;

    public StudioService() {
        this.studios = new ArrayList<>();
        this.jadwals = new ArrayList<>();
        // Initialize with 3 studios
        studios.add(new Studio(1, 50));
        studios.add(new Studio(2, 50));
        studios.add(new Studio(3, 30));
    }

    public void addJadwal(LocalDate tanggal, LocalTime waktuMulai,
            Film film, Studio studio, double harga) {
        LocalTime waktuSelesai = waktuMulai.plusMinutes(film.getDurasi() + 15); // +15 mins for cleaning

        Jadwal newJadwal = new Jadwal(++lastJadwalId, tanggal, waktuMulai,
                waktuSelesai, film, studio, harga);
        jadwals.add(newJadwal);
        studio.getJadwalList().add(newJadwal);
    }

    public List<Studio> getAllStudios() {
        return new ArrayList<>(studios);
    }

    public List<Jadwal> getJadwalByFilm(Film film) {
        List<Jadwal> result = new ArrayList<>();
        for (Jadwal j : jadwals) {
            if (j.getFilm().getId() == film.getId()) {
                result.add(j);
            }
        }
        return result;
    }

    public boolean updateJadwal(int id, LocalDate newDate, LocalTime newTime, double newHarga) {
        Jadwal jadwal = getJadwalById(id);
        if (jadwal != null) {
            jadwal.setTanggal(newDate);
            jadwal.setWaktuMulai(newTime);
            jadwal.setWaktuSelesai(newTime.plusMinutes(jadwal.getFilm().getDurasi() + 15));
            jadwal.setHarga(newHarga);
            return true;
        }
        return false;
    }

    public boolean deleteJadwal(int id) {
        Jadwal jadwal = getJadwalById(id);
        if (jadwal != null) {
            jadwal.getStudio().getJadwalList().remove(jadwal);
            jadwals.remove(jadwal);
            return true;
        }
        return false;
    }

    public Jadwal getJadwalById(int id) {
        return jadwals.stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Jadwal> getAllJadwals() {
        return new ArrayList<>(jadwals);
    }
}