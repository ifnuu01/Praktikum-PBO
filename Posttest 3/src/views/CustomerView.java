package views;

import models.*;
import services.*;
import utils.*;

import java.util.List;

public class CustomerView {
    private AuthService authService;
    private FilmService filmService;
    private StudioService studioService;
    private BookingService bookingService;

    public CustomerView(AuthService authService, FilmService filmService,
            StudioService studioService, BookingService bookingService) {
        this.authService = authService;
        this.filmService = filmService;
        this.studioService = studioService;
        this.bookingService = bookingService;
    }

    public void showCustomerMenu() {
        Customer customer = (Customer) authService.getCurrentUser();

        while (true) {
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. Lihat Film");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Top Up Saldo");
            System.out.println("4. Lihat Tiket Saya");
            System.out.println("0. Logout");

            int choice = InputUtils.getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    viewFilms();
                    break;
                case 2:
                    bookTicket(customer);
                    break;
                case 3:
                    topUpSaldo(customer);
                    break;
                case 4:
                    viewMyTickets(customer);
                    break;
                case 0:
                    authService.logout();
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void viewFilms() {
        System.out.println("\n=== DAFTAR FILM TERSEDIA ===");
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

            // Show schedules
            List<Jadwal> jadwals = studioService.getJadwalByFilm(film);
            if (!jadwals.isEmpty()) {
                System.out.println("\nJadwal Tayang:");
                for (Jadwal j : jadwals) {
                    System.out.println("- ID Jadwal: " + j.getId() +
                            ", Studio " + j.getStudio().getNomor() +
                            ", " + DateUtils.formatDate(j.getTanggal()) +
                            " " + DateUtils.formatTime(j.getWaktuMulai()) +
                            " - " + DateUtils.formatTime(j.getWaktuSelesai()) +
                            " | Harga: Rp" + j.getHarga());
                }
            }
        }
    }

    private void bookTicket(Customer customer) {
        System.out.println("\n=== PEMESANAN TIKET ===");

        // menunjukan films
        List<Film> films = filmService.getAllFilms();
        if (films.isEmpty()) {
            System.out.println("Belum ada film yang tersedia!");
            return;
        }

        for (Film film : films) {
            System.out.println(film.getId() + ". " + film.getJudul());
        }

        int filmId = InputUtils.getIntInput("Pilih film (ID): ");
        Film selectedFilm = filmService.getFilmById(filmId);
        if (selectedFilm == null) {
            System.out.println("Film tidak ditemukan!");
            return;
        }

        // menunjukan jadwal film
        List<Jadwal> jadwals = studioService.getJadwalByFilm(selectedFilm);
        if (jadwals.isEmpty()) {
            System.out.println("Belum ada jadwal untuk film ini!");
            return;
        }

        System.out.println("\nJadwal Tersedia:");
        for (Jadwal j : jadwals) {
            System.out.println(j.getId() + ". Studio " + j.getStudio().getNomor() +
                    " | " + DateUtils.formatDate(j.getTanggal()) +
                    " " + DateUtils.formatTime(j.getWaktuMulai()) +
                    " | Harga: Rp" + j.getHarga() +
                    " | Kursi tersedia: " + j.getKursiTersedia().size());
        }

        int jadwalId = InputUtils.getIntInput("Pilih jadwal (ID): ");
        Jadwal selectedJadwal = studioService.getJadwalById(jadwalId);
        if (selectedJadwal == null) {
            System.out.println("Jadwal tidak ditemukan!");
            return;
        }

        List<String> kursiTersedia = selectedJadwal.getKursiTersedia();
        showAvailableSeats(kursiTersedia);
        System.out.println();

        String kursi = InputUtils.getStringInput("Pilih kursi (contoh: A1): ");
        if (!kursiTersedia.contains(kursi)) {
            System.out.println("Kursi tidak tersedia!");
            return;
        }

        // Create ticket
        Tiket tiket = bookingService.createTiket(customer, selectedJadwal, kursi);

        System.out.println("\nTotal Pembayaran: Rp" + selectedJadwal.getHarga());
        System.out.println("Saldo Anda: Rp" + customer.getSaldo());

        if (bookingService.processPayment(tiket, customer)) {
            selectedJadwal.pesanKursi(kursi);
            System.out.println("Pembayaran berhasil! Tiket telah dipesan.");
        } else {
            System.out.println("Saldo tidak cukup! Silakan top up terlebih dahulu.");
        }
    }

    private void topUpSaldo(Customer customer) {
        System.out.println("\n=== TOP UP SALDO ===");
        System.out.println("Saldo saat ini: Rp" + customer.getSaldo());

        double amount = InputUtils.getDoubleInput("Masukkan jumlah top up: Rp");
        customer.topUpSaldo(amount);

        System.out.println("Top up berhasil! Saldo baru: Rp" + customer.getSaldo());
    }

    private void viewMyTickets(Customer customer) {
        System.out.println("\n=== TIKET SAYA ===");
        List<Tiket> tikets = bookingService.getTiketByCustomer(customer);

        if (tikets.isEmpty()) {
            System.out.println("Anda belum memiliki tiket!");
            return;
        }

        for (Tiket t : tikets) {
            System.out.println("\nID Tiket: " + t.getId());
            System.out.println("Film: " + t.getJadwal().getFilm().getJudul());
            System.out.println("Studio: " + t.getJadwal().getStudio().getNomor());
            System.out.println("Tanggal: " + DateUtils.formatDate(t.getJadwal().getTanggal()));
            System.out.println("Waktu: " + DateUtils.formatTime(t.getJadwal().getWaktuMulai()));
            System.out.println("Kursi: " + t.getKursi());
            System.out.println("Harga: Rp" + t.getJadwal().getHarga());
            System.out.println("Status: " + (t.isPaid() ? "Lunas" : "Belum Dibayar"));
        }
    }

    private void showAvailableSeats(List<String> kursiTersedia) {
        System.out.println("\nKursi Tersedia:");
        int counter = 0;
        for (String kursi : kursiTersedia) {
            System.out.print(kursi + " ");
            counter++;
            if (counter % 10 == 0) { // Print 10 seats per line
                System.out.println();
            }
        }
        System.out.println();
    }
}