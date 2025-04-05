package views;

import models.*;
import services.*;
import utils.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AdminView {
    private AuthService authService;
    private FilmService filmService;
    private StudioService studioService;

    public AdminView(AuthService authService, FilmService filmService, StudioService studioService) {
        this.authService = authService;
        this.filmService = filmService;
        this.studioService = studioService;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. Kelola Film");
            System.out.println("2. Kelola Jadwal");
            System.out.println("0. Logout");

            int choice = InputUtils.getIntInput("Pilih menu: ");
            Screen.bersihkanLayar();

            switch (choice) {
                case 1:
                    showFilmManagementMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 2:
                    showScheduleManagementMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 0:
                    authService.logout();
                    Screen.bersihkanLayar();
                    System.out.println("Anda telah logout.");
                    return;
                default:
                    Screen.bersihkanLayar();
                    System.out.println("Pilihan tidak valid!");
                    Screen.tunggu();
                    Screen.bersihkanLayar();
            }
        }
    }

    private void showFilmManagementMenu() {
        while (true) {
            System.out.println("\n=== KELOLA FILM ===");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Semua Film");
            System.out.println("3. Edit Film");
            System.out.println("4. Hapus Film");
            System.out.println("0. Kembali");

            int choice = InputUtils.getIntInput("Pilih menu: ");
            Screen.bersihkanLayar();

            switch (choice) {
                case 1:
                    addFilmMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 2:
                    viewAllFilms();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 3:
                    editFilmMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 4:
                    deleteFilmMenu();
                    System.out.println("Film berhasil dihapus!");
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 0:
                    Screen.bersihkanLayar();
                    System.out.println("Kembali ke menu admin...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    Screen.tunggu();
                    Screen.bersihkanLayar();
            }
        }
    }

    private void showScheduleManagementMenu() {
        while (true) {
            System.out.println("\n=== KELOLA JADWAL ===");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Lihat Semua Jadwal");
            System.out.println("3. Edit Jadwal");
            System.out.println("4. Hapus Jadwal");
            System.out.println("0. Kembali");

            int choice = InputUtils.getIntInput("Pilih menu: ");
            Screen.bersihkanLayar();

            switch (choice) {
                case 1:
                    addScheduleMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 2:
                    viewAllSchedules();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 3:
                    editScheduleMenu();
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 4:
                    deleteScheduleMenu();
                    System.out.println("Jadwal berhasil dihapus!");
                    Screen.tunggu();
                    Screen.bersihkanLayar();
                    break;
                case 0:
                    Screen.bersihkanLayar();
                    System.out.println("Kembali ke menu admin...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    Screen.tunggu();
                    Screen.bersihkanLayar();
            }
        }
    }

    private void editFilmMenu() {
        viewAllFilms();
        if (filmService.getAllFilms().isEmpty())
            return;

        int filmId = InputUtils.getIntInput("Masukkan ID Film yang akan diedit: ");
        Screen.bersihkanLayar();
        Film film = filmService.getFilmById(filmId);

        if (film == null) {
            System.out.println("Film tidak ditemukan!");
            return;
        }

        System.out.println("\nData saat ini:");
        System.out.println("Judul: " + film.getJudul());
        System.out.println("Genre: " + film.getGenre());
        System.out.println("Durasi: " + film.getDurasi() + " menit");
        System.out.println("Sinopsis: " + film.getSinopsis());

        String judul = InputUtils.getStringInput("Judul baru (kosongkan jika tidak diubah): ");
        String genre = InputUtils.getStringInput("Genre baru (kosongkan jika tidak diubah): ");
        String durasiStr = InputUtils.getStringInput("Durasi baru (menit, kosongkan jika tidak diubah): ");
        String sinopsis = InputUtils.getStringInput("Sinopsis baru (kosongkan jika tidak diubah): ");

        if (!judul.isEmpty())
            film.setJudul(judul);
        if (!genre.isEmpty())
            film.setGenre(genre);
        if (!durasiStr.isEmpty())
            film.setDurasi(Integer.parseInt(durasiStr));
        if (!sinopsis.isEmpty())
            film.setSinopsis(sinopsis);
        Screen.bersihkanLayar();
        System.out.println("Film berhasil diperbarui!");
    }

    private void deleteFilmMenu() {
        viewAllFilms();
        if (filmService.getAllFilms().isEmpty())
            return;

        int filmId = InputUtils.getIntInput("Masukkan ID Film yang akan dihapus: ");
        Screen.bersihkanLayar();

        if (InputUtils.getYesNoInput("Yakin ingin menghapus film ini?")) {
            Screen.bersihkanLayar();
            if (filmService.deleteFilm(filmId)) {
                System.out.println("Film berhasil dihapus!");
            } else {
                System.out.println("Gagal menghapus film. ID tidak ditemukan.");
            }
        }
    }

    private void viewAllSchedules() {
        System.out.println("\n=== DAFTAR JADWAL ===");
        List<Jadwal> allJadwals = studioService.getAllJadwals();

        if (allJadwals.isEmpty()) {
            System.out.println("Belum ada jadwal yang tersedia!");
            return;
        }

        for (Jadwal j : allJadwals) {
            System.out.println("\nID: " + j.getId());
            System.out.println("Film: " + j.getFilm().getJudul());
            System.out.println("Studio: " + j.getStudio().getNomor());
            System.out.println("Tanggal: " + DateUtils.formatDate(j.getTanggal()));
            System.out.println("Waktu: " + DateUtils.formatTime(j.getWaktuMulai()) +
                    " - " + DateUtils.formatTime(j.getWaktuSelesai()));
            System.out.println("Harga: Rp" + j.getHarga());
            System.out.println("Kursi Tersedia: " + j.getKursiTersedia().size());
        }
    }

    private void editScheduleMenu() {
        viewAllSchedules();
        if (studioService.getAllJadwals().isEmpty())
            return;

        int jadwalId = InputUtils.getIntInput("Masukkan ID Jadwal yang akan diedit: ");
        Screen.bersihkanLayar();
        Jadwal jadwal = studioService.getJadwalById(jadwalId);

        if (jadwal == null) {
            System.out.println("Jadwal tidak ditemukan!");
            return;
        }

        System.out.println("\nData saat ini:");
        System.out.println("Film: " + jadwal.getFilm().getJudul());
        System.out.println("Studio: " + jadwal.getStudio().getNomor());
        System.out.println("Tanggal: " + DateUtils.formatDate(jadwal.getTanggal()));
        System.out.println("Waktu: " + DateUtils.formatTime(jadwal.getWaktuMulai()));
        System.out.println("Harga: Rp" + jadwal.getHarga());

        String dateStr = InputUtils.getStringInput("Tanggal baru (dd/MM/yyyy, kosongkan jika tidak diubah): ");
        String timeStr = InputUtils.getStringInput("Waktu baru (HH:mm, kosongkan jika tidak diubah): ");
        String hargaStr = InputUtils.getStringInput("Harga baru (kosongkan jika tidak diubah): ");

        try {
            LocalDate newDate = dateStr.isEmpty() ? jadwal.getTanggal()
                    : LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime newTime = timeStr.isEmpty() ? jadwal.getWaktuMulai()
                    : LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("HH:mm"));
            double newHarga = hargaStr.isEmpty() ? jadwal.getHarga() : Double.parseDouble(hargaStr);

            if (studioService.updateJadwal(jadwalId, newDate, newTime, newHarga)) {
                System.out.println("Jadwal berhasil diperbarui!");
            } else {
                System.out.println("Gagal memperbarui jadwal.");
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Format input tidak valid!");
        }
    }

    private void deleteScheduleMenu() {
        viewAllSchedules();
        if (studioService.getAllJadwals().isEmpty())
            return;

        int jadwalId = InputUtils.getIntInput("Masukkan ID Jadwal yang akan dihapus: ");
        Screen.bersihkanLayar();

        if (InputUtils.getYesNoInput("Yakin ingin menghapus jadwal ini?")) {
            Screen.bersihkanLayar();
            if (studioService.deleteJadwal(jadwalId)) {
                System.out.println("Jadwal berhasil dihapus!");
            } else {
                System.out.println("Gagal menghapus jadwal. ID tidak ditemukan.");
            }
        }
    }

    private void addFilmMenu() {
        System.out.println("\n=== TAMBAH FILM ===");
        String judul = InputUtils.getStringInput("Judul: ");
        String genre = InputUtils.getStringInput("Genre: ");
        int durasi = InputUtils.getIntInput("Durasi (menit): ");
        String sinopsis = InputUtils.getStringInput("Sinopsis: ");

        filmService.addFilm(judul, genre, durasi, sinopsis);
        Screen.bersihkanLayar();
        System.out.println("Film berhasil ditambahkan!");
    }

    private void addScheduleMenu() {
        System.out.println("\n=== TAMBAH JADWAL ===");

        List<Film> films = filmService.getAllFilms();
        if (films.isEmpty()) {
            System.out.println("Belum ada film yang tersedia!");
            return;
        }

        System.out.println("\nDaftar Film:");
        for (Film film : films) {
            System.out.println(film.getId() + ". " + film.getJudul());
        }

        int filmId = InputUtils.getIntInput("Pilih film (ID): ");
        Screen.bersihkanLayar();
        Film selectedFilm = filmService.getFilmById(filmId);
        if (selectedFilm == null) {
            System.out.println("Film tidak ditemukan!");
            return;
        }

        List<Studio> studios = studioService.getAllStudios();
        System.out.println("\nDaftar Studio:");
        for (Studio studio : studios) {
            System.out.println(studio.getNomor() + ". Kapasitas: " + studio.getKapasitas());
        }

        int studioNomor = InputUtils.getIntInput("Pilih studio (nomor): ");
        Screen.bersihkanLayar();
        Studio selectedStudio = studios.stream()
                .filter(s -> s.getNomor() == studioNomor)
                .findFirst()
                .orElse(null);

        if (selectedStudio == null) {
            System.out.println("Studio tidak ditemukan!");
            return;
        }

        LocalDate tanggal = InputUtils.getDateInput("Tanggal tayang: ");
        LocalTime waktuMulai = InputUtils.getTimeInput("Waktu mulai (HH:mm): ");
        double harga = InputUtils.getDoubleInput("Harga tiket: ");

        studioService.addJadwal(tanggal, waktuMulai, selectedFilm, selectedStudio, harga);
        Screen.bersihkanLayar();
        System.out.println("Jadwal berhasil ditambahkan!");
    }

    private void viewAllFilms() {
        System.out.println("\n=== DAFTAR FILM ===");
        List<Film> films = filmService.getAllFilms();

        if (films.isEmpty()) {
            System.out.println("Belum ada film yang tersedia!");
            return;
        }

        for (Film film : films) {
            System.out.println("\nID: " + film.getId());
            System.out.println("Judul: " + film.getJudul());
            System.out.println("Genre: " + film.getGenre());
            System.out.println("Durasi: " + film.getDurasi() + " menit");
            System.out.println("Sinopsis: " + film.getSinopsis());

            List<Jadwal> jadwals = studioService.getJadwalByFilm(film);
            if (!jadwals.isEmpty()) {
                System.out.println("\nJadwal Tayang:");
                for (Jadwal j : jadwals) {
                    System.out.println("- Studio " + j.getStudio().getNomor() +
                            ", " + DateUtils.formatDate(j.getTanggal()) +
                            " " + DateUtils.formatTime(j.getWaktuMulai()) +
                            " - " + DateUtils.formatTime(j.getWaktuSelesai()) +
                            " | Harga: Rp" + j.getHarga());
                }
            }
        }
    }
}