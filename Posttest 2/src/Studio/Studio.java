package Studio;

public class Studio {
    private int id;
    private String nama;
    private int kapasitas;
    private int terisi;

    public Studio(int id, String nama, int kapasitas) {
        this.id = id;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.terisi = 0;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getTerisi() {
        return terisi;
    }

    public boolean isFull() {
        return terisi >= kapasitas;
    }

    public void setTerisi(int terisi) {
        this.terisi = terisi;
    }

    public void tambahPenonton() {
        if (!isFull()) {
            terisi++;
        } else {
            System.out.println("Studio penuh!");
        }
    }

    public void lihatDetailStudio() {
        System.out.println("+--------------------------------+");
        System.out.printf("| %-10s : %-15s |\n", "ID", getId());
        System.out.printf("| %-10s : %-15s |\n", "Nama", getNama());
        System.out.printf("| %-10s : %-15d |\n", "Kapasitas", getKapasitas());
        System.out.printf("| %-10s : %-15d |\n", "Terisi", getTerisi());
        System.out.println("+--------------------------------+");
    }

    public void lihatStudioSingkat() {
        System.out.printf("ID: %d | Sisa Kapasitas: %d\n", getId(), getKapasitas() - getTerisi());
    }

}
