package Tiket;

import java.util.ArrayList;
import java.util.Scanner;

import Film.Film;
import Film.FilmManager;
import Studio.Studio;
import Studio.StudioManager;

public class TiketManager {
    protected static ArrayList<Tiket> listTiket = new ArrayList<Tiket>();
    static Scanner input = new Scanner(System.in);
    FilmManager FilmManager = new FilmManager();
    StudioManager StudioManager = new StudioManager();

    public void lihatTiket() {
        System.out.println("====================================");
        System.out.println("            DAFTAR TIKET            ");
        System.out.println("====================================");

        if (listTiket.isEmpty()) {
            System.out.println("Belum ada tiket yang tersedia.");
        } else {
            for (Tiket tiket : listTiket) {
                System.out.println("------------------------------------");
                tiket.lihatDetailTiket();
            }
        }

        System.out.println("====================================");
    }

    public void tambahTiket() {
        System.out.println("====================================");
        System.out.println("            TAMBAH TIKET            ");
        System.out.println("====================================");
        FilmManager.lihatFilm(true);
        System.out.print("Masukkan ID Film: ");
        int idFilm = input.nextInt();

        Film film = FilmManager.getFilmById(idFilm);
        if (film == null) {
            System.out.println("[!] Film tidak ditemukan.");
            return;
        }

        StudioManager.lihatStudio(true);
        System.out.print("Masukkan ID Studio: ");
        int idStudio = input.nextInt();
        input.nextLine();

        Studio studio = StudioManager.getStudioById(idStudio);
        if (studio == null) {
            System.out.println("[!] Studio tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan Pukul Tayang: ");
        String pukulTayang = input.nextLine().trim();

        boolean valid = false;
        int harga = 0;

        while (!valid) {
            System.out.print("Masukkan Harga: ");
            if (input.hasNextInt()) {
                harga = input.nextInt();
                input.nextLine();
                if (harga < 0) {
                    System.out.println("[!] Harga tidak boleh negatif.");
                    continue;
                }
                valid = true;
            } else {
                System.out.println("[!] Harga harus berupa angka atau tidak boleh negatif.");
                input.nextLine();
            }
        }

        if (studio.getTerisi() >= studio.getKapasitas()) {
            System.out.println("[!] Kapasitas studio penuh.");
            return;
        }

        Tiket tiket = new Tiket(listTiket.size() + 1, film, studio, pukulTayang, harga);
        listTiket.add(tiket);
        studio.setTerisi(studio.getTerisi() + 1);

        System.out.println("[!] Tiket berhasil ditambahkan.");
    }

    public void editTiket() {
        if (listTiket.isEmpty()) {
            System.out.println("Belum ada tiket yang tersedia untuk diedit.");
            return;
        }

        lihatTiket();
        System.out.print("Masukkan ID Tiket yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        if (id > 0 && id <= listTiket.size()) {
            FilmManager.lihatFilm(true);
            System.out.print("Masukkan ID Film: ");
            int idFilm = input.nextInt();
            input.nextLine();
            StudioManager.lihatStudio(true);
            System.out.print("Masukkan ID Studio: ");
            int idStudio = input.nextInt();
            input.nextLine();
            System.out.print("Masukkan Pukul Tayang: ");
            String pukulTayang = input.nextLine().trim();
            boolean valid = false;
            int harga = 0;

            while (!valid) {
                System.out.print("Masukkan Harga: ");
                if (input.hasNextInt()) {
                    harga = input.nextInt();
                    input.nextLine();
                    if (harga < 0) {
                        System.out.println("[!] Harga tidak boleh negatif.");
                        continue;
                    }
                    valid = true;
                } else {
                    System.out.println("[!] Harga harus berupa angka atau tidak boleh negatif.");
                    input.nextLine();
                }
            }

            Film film = FilmManager.getFilmById(idFilm);
            if (film == null) {
                System.out.println("[!] Film tidak ditemukan.");
                return;
            }

            Studio studio = StudioManager.getStudioById(idStudio);
            if (studio == null) {
                System.out.println("[!] Studio tidak ditemukan.");
                return;
            }

            Tiket tiket = new Tiket(id, film, studio, pukulTayang, harga);
            listTiket.set(id - 1, tiket);

            System.out.println("[!] Tiket berhasil diperbarui.");
        } else {
            System.out.println("[!] ID Tiket tidak ditemukan.");
        }
    }

    public void hapusTiket() {
        if (listTiket.isEmpty()) {
            System.out.println("Belum ada tiket yang tersedia untuk dihapus.");
            return;
        }

        lihatTiket();
        System.out.print("Masukkan ID Tiket yang ingin dihapus: ");
        int id = input.nextInt();

        if (id > 0 && id <= listTiket.size()) {
            listTiket.remove(id - 1);
            System.out.println("[!] Tiket berhasil dihapus.");
        } else {
            System.out.println("[!] ID Tiket tidak ditemukan.");
        }
    }

}
