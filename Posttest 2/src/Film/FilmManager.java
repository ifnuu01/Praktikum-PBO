package Film;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmManager {
    protected static ArrayList<Film> listFilm = new ArrayList<Film>();
    static Scanner input = new Scanner(System.in);

    public Film getFilmById(int id) {
        for (Film film : listFilm) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

    boolean validasiFilm(String judul, String Genre) {
        if (judul.length() > 0 && Genre.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void lihatFilm(boolean isSingkat) {
        System.out.println("====================================");
        System.out.println("            DAFTAR FILM            ");
        System.out.println("====================================");

        if (listFilm.isEmpty()) {
            System.out.println("Belum ada film yang tersedia.");
        } else {
            for (Film film : listFilm) {
                System.out.println("------------------------------------");
                if (isSingkat) {
                    film.lihatFilmSingkat();
                } else {
                    film.lihatDetailFilm();
                }
            }
        }

        System.out.println("====================================");
    }

    public void tambahFilm() {
        System.out.println("====================================");
        System.out.println("           TAMBAH FILM              ");
        System.out.println("====================================");

        System.out.print("Masukkan Judul Film: ");
        String judul = input.nextLine().trim();
        System.out.print("Masukkan Genre Film: ");
        String genre = input.nextLine().trim();

        if (!validasiFilm(judul, genre)) {
            System.out.println("[!] Judul dan genre film harus diisi.");
            return;
        }

        Film film = new Film(listFilm.size() + 1, judul, genre);
        listFilm.add(film);
        System.out.println("[!] Film berhasil ditambahkan.");
    }

    public void editFilm() {
        if (listFilm.isEmpty()) {
            System.out.println("Belum ada film yang tersedia untuk diedit.");
            return;
        }

        lihatFilm(false);
        System.out.print("Masukkan ID Film yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();

        if (id > 0 && id <= listFilm.size()) {
            System.out.print("Masukkan Judul Baru: ");
            String judul = input.nextLine().trim();
            System.out.print("Masukkan Genre Baru: ");
            String genre = input.nextLine().trim();

            if (!validasiFilm(judul, genre)) {
                System.out.println("[!] Judul dan genre film harus diisi.");
                return;
            }

            listFilm.set(id - 1, new Film(id, judul, genre));
            System.out.println("[!] Film berhasil diperbarui.");
        } else {
            System.out.println("[!] ID Film tidak ditemukan.");
        }
    }

    public void hapusFilm() {
        if (listFilm.isEmpty()) {
            System.out.println("Belum ada film yang tersedia untuk dihapus.");
            return;
        }

        lihatFilm(false);
        System.out.print("Masukkan ID Film yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();

        if (id > 0 && id <= listFilm.size()) {
            listFilm.remove(id - 1);
            System.out.println("[!] Film berhasil dihapus.");
        } else {
            System.out.println("[!] ID Film tidak ditemukan.");
        }
    }

}
