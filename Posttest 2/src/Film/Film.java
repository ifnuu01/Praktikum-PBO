package Film;

public class Film {
    private int id;
    private String judul;
    private String genre;

    public Film(int id, String judul, String genre) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void lihatDetailFilm() {
        System.out.println("+------------------------------+");
        System.out.printf("| %-3s | %-20s |\n", "ID", getId());
        System.out.println("+------------------------------+");
        System.out.printf("| Judul : %-20s |\n", getJudul());
        System.out.printf("| Genre : %-20s |\n", getGenre());
        System.out.println("+------------------------------+");
    }

    public void lihatFilmSingkat() {
        System.out.printf("ID: %d | Judul: %s\n", getId(), getJudul());
    }

}
