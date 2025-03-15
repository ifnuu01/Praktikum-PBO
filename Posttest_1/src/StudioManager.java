import java.util.ArrayList;
import java.util.Scanner;

public class StudioManager {
    static ArrayList<Studio> listStudio = new ArrayList<Studio>();
    static Scanner input = new Scanner(System.in);

    public Studio getStudioById(int id) {
        for (Studio studio : listStudio) {
            if (studio.id == id) {
                return studio;
            }
        }
        return null;
    }

    public boolean cekStudioById(int id) {
        for (Studio studio : listStudio) {
            if (studio.id == id) {
                return true;
            }
        }
        return false;
    }

    public void lihatStudio() {
        if (listStudio.size() == 0) {
            System.out.println("Belum ada studio yang ditambahkan");
        } else {
            for (Studio studio : listStudio) {
                studio.lihatDetailStudio();
            }
        }
    }

    public void tambahStudio() {
        System.out.print("Masukkan ID Studio: ");
        int id = input.nextInt();
        System.out.print("Masukkan Nama Studio: ");
        String nama = input.next();
        System.out.print("Masukkan Kapasitas Studio: ");
        int kapasitas = input.nextInt();

        if (cekStudioById(id)) {
            System.out.println("ID Studio sudah digunakan");
            return;
        }

        Studio studio = new Studio(id, nama, kapasitas);
        listStudio.add(studio);
        System.out.println("Studio berhasil ditambahkan");
    }

    public void editStudio() {
        if (listStudio.size() == 0) {
            System.out.println("Belum ada studio yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Studio yang ingin diubah: ");
        int id = input.nextInt();
        input.nextLine();
        if (id > 0 && id <= listStudio.size()) {
            System.out.print("Masukkan Nama Studio: ");
            String nama = input.next();
            System.out.print("Masukkan Kapasitas Studio: ");
            int kapasitas = input.nextInt();

            Studio studio = new Studio(id, nama, kapasitas);
            listStudio.set(id - 1, studio);
            System.out.println("Studio berhasil diubah");
        } else {
            System.out.println("ID Studio tidak ditemukan");
        }
    }

    public void hapusStudio() {
        if (listStudio.size() == 0) {
            System.out.println("Belum ada studio yang ditambahkan");
            return;
        }
        System.out.print("Masukkan ID Studio yang ingin dihapus: ");
        int id = input.nextInt();
        if (id > 0 && id <= listStudio.size()) {
            listStudio.remove(id - 1);
            System.out.println("Studio berhasil dihapus");
        } else {
            System.out.println("ID Studio tidak ditemukan");
        }
    }
}
