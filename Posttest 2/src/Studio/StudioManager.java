package Studio;

import java.util.ArrayList;
import java.util.Scanner;

public class StudioManager {
    static ArrayList<Studio> listStudio = new ArrayList<Studio>();
    static Scanner input = new Scanner(System.in);

    public Studio getStudioById(int id) {
        for (Studio studio : listStudio) {
            if (studio.getId() == id) {
                return studio;
            }
        }
        return null;
    }

    boolean validasiStudio(String nama, int kapasitas) {
        if (nama.length() > 0 && kapasitas > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void lihatStudio(boolean isSingkat) {
        System.out.println("====================================");
        System.out.println("          DAFTAR STUDIO            ");
        System.out.println("====================================");

        if (listStudio.isEmpty()) {
            System.out.println("Belum ada studio yang tersedia.");
        } else {
            for (Studio studio : listStudio) {
                System.out.println("------------------------------------");
                if (isSingkat) {
                    studio.lihatStudioSingkat();
                } else {
                    studio.lihatDetailStudio();
                }
            }
        }

        System.out.println("====================================");
    }

    public void tambahStudio() {
        System.out.println("====================================");
        System.out.println("           TAMBAH STUDIO            ");
        System.out.println("====================================");

        System.out.print("Masukkan Nama Studio: ");
        String nama = input.nextLine().trim();

        int kapasitas = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Masukkan Kapasitas Studio: ");
            if (input.hasNextInt()) {
                kapasitas = input.nextInt();
                input.nextLine();
                valid = true;
            } else {
                System.out.println("[!] Kapasitas harus berupa angka.");
                input.nextLine();
            }
        }

        if (!validasiStudio(nama, kapasitas)) {
            System.out.println("[!] Nama dan kapasitas studio harus diisi.");
            return;
        }

        Studio studio = new Studio(listStudio.size() + 1, nama, kapasitas);
        listStudio.add(studio);
        System.out.println("[!] Studio berhasil ditambahkan.");
    }

    public void editStudio() {
        if (listStudio.isEmpty()) {
            System.out.println("Belum ada studio yang tersedia untuk diedit.");
            return;
        }

        lihatStudio(false);
        System.out.print("Masukkan ID Studio yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();

        if (id > 0 && id <= listStudio.size()) {
            System.out.print("Masukkan Nama Baru: ");
            String nama = input.nextLine().trim();

            int kapasitas = 0;
            boolean valid = false;
            while (!valid) {
                System.out.print("Masukkan Kapasitas Baru: ");
                if (input.hasNextInt()) {
                    kapasitas = input.nextInt();
                    input.nextLine();
                    valid = true;
                } else {
                    System.out.println("[!] Kapasitas harus berupa angka.");
                    input.nextLine();
                }
            }

            if (!validasiStudio(nama, kapasitas)) {
                System.out.println("[!] Nama dan kapasitas studio harus diisi.");
                return;
            }

            listStudio.set(id - 1, new Studio(id, nama, kapasitas));
            System.out.println("[!] Studio berhasil diperbarui.");
        } else {
            System.out.println("[!] ID Studio tidak ditemukan.");
        }
    }

    public void hapusStudio() {
        if (listStudio.isEmpty()) {
            System.out.println("Belum ada studio yang tersedia untuk dihapus.");
            return;
        }

        lihatStudio(false);
        System.out.print("Masukkan ID Studio yang ingin dihapus: ");
        int id = input.nextInt();
        input.nextLine();

        if (id > 0 && id <= listStudio.size()) {
            listStudio.remove(id - 1);
            System.out.println("[!] Studio berhasil dihapus.");
        } else {
            System.out.println("[!] ID Studio tidak ditemukan.");
        }
    }

}
