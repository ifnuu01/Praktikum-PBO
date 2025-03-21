import java.util.Scanner;
import Film.FilmManager;
import Studio.StudioManager;
import Tiket.TiketManager;

public class App {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            System.out.println("======================================");
            System.out.println("         APLIKASI TIKET BIOSKOP       ");
            System.out.println("======================================");
            System.out.println("[1] Kelola Film");
            System.out.println("[2] Kelola Studio");
            System.out.println("[3] Kelola Tiket");
            System.out.println("[4] Keluar");
            System.out.println("======================================");
            System.out.print("Pilih menu: ");

            int menu = input.nextInt();
            input.nextLine();
            clearScreen();

            switch (menu) {
                case 1:
                    menuFilm();
                    break;
                case 2:
                    menuStudio();
                    break;
                case 3:
                    menuTiket();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Menu tidak tersedia! Mohon pilih yang sesuai.");
                    break;
            }
        }
    }

    public static void menuFilm() {
        FilmManager filmManager = new FilmManager();
        while (true) {
            System.out.println("====================================");
            System.out.println("          KELOLA FILM               ");
            System.out.println("====================================");
            System.out.println("[1] Lihat Film");
            System.out.println("[2] Tambah Film");
            System.out.println("[3] Edit Film");
            System.out.println("[4] Hapus Film");
            System.out.println("[5] Kembali");
            System.out.println("====================================");
            System.out.print("Pilih menu: ");

            int menu = input.nextInt();
            input.nextLine();
            clearScreen();

            switch (menu) {
                case 1:
                    filmManager.lihatFilm(false);
                    break;
                case 2:
                    filmManager.tambahFilm();
                    break;
                case 3:
                    filmManager.editFilm();
                    break;
                case 4:
                    filmManager.hapusFilm();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Menu tidak tersedia!");
                    break;
            }
        }
    }

    public static void menuStudio() {
        StudioManager studioManager = new StudioManager();
        while (true) {
            System.out.println("====================================");
            System.out.println("          KELOLA STUDIO             ");
            System.out.println("====================================");
            System.out.println("[1] Lihat Studio");
            System.out.println("[2] Tambah Studio");
            System.out.println("[3] Edit Studio");
            System.out.println("[4] Hapus Studio");
            System.out.println("[5] Kembali");
            System.out.println("====================================");
            System.out.print("Pilih menu: ");

            int menu = input.nextInt();
            input.nextLine();
            clearScreen();

            switch (menu) {
                case 1:
                    studioManager.lihatStudio(false);
                    break;
                case 2:
                    studioManager.tambahStudio();
                    break;
                case 3:
                    studioManager.editStudio();
                    break;
                case 4:
                    studioManager.hapusStudio();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Menu tidak tersedia!");
                    break;
            }
        }
    }

    public static void menuTiket() {
        TiketManager tiketManager = new TiketManager();
        while (true) {
            System.out.println("====================================");
            System.out.println("          KELOLA TIKET              ");
            System.out.println("====================================");
            System.out.println("[1] Lihat Tiket");
            System.out.println("[2] Tambah Tiket");
            System.out.println("[3] Edit Tiket");
            System.out.println("[4] Hapus Tiket");
            System.out.println("[5] Kembali");
            System.out.println("====================================");
            System.out.print("Pilih menu: ");

            int menu = input.nextInt();
            input.nextLine();
            clearScreen();

            switch (menu) {
                case 1:
                    tiketManager.lihatTiket();
                    break;
                case 2:
                    tiketManager.tambahTiket();
                    break;
                case 3:
                    tiketManager.editTiket();
                    break;
                case 4:
                    tiketManager.hapusTiket();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Menu tidak tersedia!");
                    break;
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}