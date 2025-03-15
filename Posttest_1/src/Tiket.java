public class Tiket {
    int id;
    Film film;
    Studio studio;
    String hariTayang;
    int harga;

    public Tiket(int id, Film film, Studio studio, String hariTayang, int harga) {
        this.id = id;
        this.film = film;
        this.studio = studio;
        this.hariTayang = hariTayang;
        this.harga = harga;
    }

    public void lihatDetailTiket() {
        System.out.println("==============================");
        System.out.println("ID\t\t: " + this.id);
        System.out.println("Film\t\t: " + this.film.judul);
        System.out.println("Studio\t\t: " + this.studio.nama);
        System.out.println("Hari Tayang\t: " + this.hariTayang);
        System.out.println("Harga\t\t: " + this.harga);
        System.out.println("==============================");
    }
}
