public class Studio {
    int id;
    String nama;
    int kapasitas;
    int terisi;

    public Studio(int id, String nama, int kapasitas) {
        this.id = id;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.terisi = 0;
    }

    public boolean isFull() {
        return this.terisi == this.kapasitas;
    }

    public void tambahPenonton() {
        if (this.terisi < this.kapasitas) {
            this.terisi++;
        }
    }

    public void lihatDetailStudio() {
        System.out.println("==============================");
        System.out.println("ID\t\t: " + this.id);
        System.out.println("Nama\t\t: " + this.nama);
        System.out.println("Kapasitas\t: " + this.kapasitas);
        System.out.println("Terisi\t\t: " + this.terisi);
        System.out.println("==============================");
    }
}
