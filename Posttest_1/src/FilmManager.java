import java.util.ArrayList;
import java.util.Scanner;

public class FilmManager {
    static ArrayList<Film> listFilm = new ArrayList<Film>();
    static Scanner input = new Scanner(System.in);

    public Film getFilmById(int id) {
        for (Film film : listFilm) {
            if (film.id == id) {
                return film;
            }
        }
        return null;
    }

    public boolean cekFilmById(int id) {
        for (Film film : listFilm) {
            if (film.id == id) {
                return true;
            }
        }
        return false;
    }

    public void lihatFilm() {
        if (listFilm.size() == 0) {
            System.out.println("Belum ada film yang ditambahkan");
        } else {
            for (Film film : listFilm) {
                film.lihatDetailFilm();
            }
        }
    }

    public void tambahFilm() {
        System.out.print("Masukkan ID Film: ");
        int id = input.nextInt();
        System.out.print("Masukkan Judul Film: ");
        String judul = input.next();
        System.out.print("Masukkan Genre Film: ");
        String genre = input.next();

        if (cekFilmById(id)) {
            System.out.println("ID Film sudah digunakan");
            return;
        }

        Film film = new Film(id, judul, genre);
        listFilm.add(film);
        System.out.println("Film berhasil ditambahkan");
    }

    public void editFilm() {
        if (listFilm.size() == 0) {
            System.out.println("Belum ada film yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Film yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();
        if (id > 0 && id <= listFilm.size()) {
            System.out.print("Masukkan Judul Film: ");
            String judul = input.next();
            System.out.print("Masukkan Genre Film: ");
            String genre = input.next();

            Film film = new Film(id, judul, genre);
            listFilm.set(id - 1, film);
            System.out.println("Film berhasil diubah");
        } else {
            System.out.println("ID Film tidak ditemukan");
        }
    }

    public void hapusFilm() {
        if (listFilm.size() == 0) {
            System.out.println("Belum ada film yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Film yang ingin dihapus: ");
        int id = input.nextInt();
        if (id > 0 && id <= listFilm.size()) {
            listFilm.remove(id - 1);
            System.out.println("Film berhasil dihapus");
        } else {
            System.out.println("ID Film tidak ditemukan");
        }
    }
}
