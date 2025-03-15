import java.util.ArrayList;
import java.util.Scanner;

public class TiketManager {
    static ArrayList<Tiket> listTiket = new ArrayList<Tiket>();
    static Scanner input = new Scanner(System.in);
    FilmManager FilmManager = new FilmManager();
    StudioManager StudioManager = new StudioManager();

    public void lihatTiket() {
        if (listTiket.size() == 0) {
            System.out.println("Belum ada tiket yang ditambahkan");
        } else {
            for (Tiket tiket : listTiket) {
                tiket.lihatDetailTiket();
            }
        }
    }

    public boolean cekTiketById(int id) {
        for (Tiket tiket : listTiket) {
            if (tiket.id == id) {
                return true;
            }
        }
        return false;
    }

    public void tambahTiket() {
        System.out.print("Masukkan ID Tiket: ");
        int id = input.nextInt();
        System.out.print("Masukkan ID Film: ");
        int idFilm = input.nextInt();
        System.out.print("Masukkan ID Studio: ");
        int idStudio = input.nextInt();
        System.out.print("Masukkan Hari Tayang: ");
        String hariTayang = input.next();
        System.out.print("Masukkan Harga: ");
        int harga = input.nextInt();

        if (cekTiketById(id)) {
            System.out.println("ID Tiket sudah digunakan");
            return;
        }

        Film film = FilmManager.getFilmById(idFilm);
        if (film == null) {
            System.out.println("Film tidak ditemukan");
            return;
        }
        Studio studio = StudioManager.getStudioById(idStudio);
        if (studio == null) {
            System.out.println("Studio tidak ditemukan");
            return;
        }
        Tiket tiket = new Tiket(id, film, studio, hariTayang, harga);
        listTiket.add(tiket);
        studio.terisi++;
        if (studio.terisi > studio.kapasitas) {
            System.out.println("Kapasitas studio penuh");
            return;
        }
        System.out.println("Tiket berhasil ditambahkan");
    }

    public void editTiket() {
        if (listTiket.size() == 0) {
            System.out.println("Belum ada tiket yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Tiket yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();
        if (id > 0 && id <= listTiket.size()) {
            System.out.print("Masukkan ID Film: ");
            int idFilm = input.nextInt();
            System.out.print("Masukkan ID Studio: ");
            int idStudio = input.nextInt();
            System.out.print("Masukkan Hari Tayang: ");
            String hariTayang = input.next();
            System.out.print("Masukkan Harga: ");
            int harga = input.nextInt();

            Film film = FilmManager.getFilmById(idFilm);
            if (film == null) {
                System.out.println("Film tidak ditemukan");
                return;
            }
            Studio studio = StudioManager.getStudioById(idStudio);
            if (studio == null) {
                System.out.println("Studio tidak ditemukan");
                return;
            }
            Tiket tiket = new Tiket(id, film, studio, hariTayang, harga);
            listTiket.set(id - 1, tiket);
            System.out.println("Tiket berhasil diubah");
        } else {
            System.out.println("ID Tiket tidak ditemukan");
        }
    }

    public void hapusTiket() {
        if (listTiket.size() == 0) {
            System.out.println("Belum ada tiket yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Tiket yang ingin dihapus: ");
        int id = input.nextInt();
        if (id > 0 && id <= listTiket.size()) {
            listTiket.remove(id - 1);
            System.out.println("Tiket berhasil dihapus");
        } else {
            System.out.println("ID Tiket tidak ditemukan");
        }
    }
}
