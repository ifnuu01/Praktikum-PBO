public class Film {
    int id;
    String judul;
    String genre;

    public Film(int id, String judul, String genre) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
    }

    public void lihatDetailFilm() {
        System.out.println("==============================");
        System.out.println("ID\t: " + this.id);
        System.out.println("Judul\t: " + this.judul);
        System.out.println("Genre\t: " + this.genre);
        System.out.println("==============================");
    }
}
