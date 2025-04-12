package models;

public class Film {
    private int id;
    private String judul;
    private String genre;
    private int durasi; // menit
    private String sinopsis;

    public Film(int id, String judul, String genre, int durasi, String sinopsis) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
        this.sinopsis = sinopsis;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    // Setters

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setId(int id) {
        this.id = id;
    }

}