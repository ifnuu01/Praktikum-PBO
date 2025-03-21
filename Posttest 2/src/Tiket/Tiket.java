package Tiket;

import Film.Film;
import Studio.Studio;

public class Tiket {
    private int id;
    private Film film;
    private Studio studio;
    private String pukulTayang;
    private int harga;

    public Tiket(int id, Film film, Studio studio, String pukulTayang, int harga) {
        this.id = id;
        this.film = film;
        this.studio = studio;
        this.pukulTayang = pukulTayang;
        this.harga = harga;
    }

    public int getId() {
        return this.id;
    }

    public Film getFilm() {
        return this.film;
    }

    public Studio getStudio() {
        return this.studio;
    }

    public String getPukulTayang() {
        return this.pukulTayang;
    }

    public int getHarga() {
        return this.harga;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public void setHariTayang(String pukulTayang) {
        this.pukulTayang = pukulTayang;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void lihatDetailTiket() {
        System.out.println("+--------------------------------------+");
        System.out.printf("| %-12s : %-20s |\n", "ID", getId());
        System.out.printf("| %-12s : %-20s |\n", "Film", film.getJudul());
        System.out.printf("| %-12s : %-20s |\n", "Studio", studio.getNama());
        System.out.printf("| %-12s : %-20s |\n", "Hari Tayang", getPukulTayang());
        System.out.printf("| %-12s : Rp %-17d |\n", "Harga", getHarga());
        System.out.println("+--------------------------------------+");
    }

}
