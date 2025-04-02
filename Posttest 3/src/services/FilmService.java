package services;

import models.Film;
import java.util.ArrayList;
import java.util.List;

public class FilmService {
    private List<Film> films;
    private int lastId = 0;

    public FilmService() {
        this.films = new ArrayList<>();
    }

    public void addFilm(String judul, String genre, int durasi, String sinopsis) {
        Film newFilm = new Film(++lastId, judul, genre, durasi, sinopsis);
        films.add(newFilm);
    }

    public boolean updateFilm(int id, String judul, String genre, int durasi, String sinopsis) {
        Film film = getFilmById(id);
        if (film != null) {
            film.setJudul(judul);
            film.setGenre(genre);
            film.setDurasi(durasi);
            film.setSinopsis(sinopsis);
            return true;
        }
        return false;
    }

    public boolean deleteFilm(int id) {
        Film film = getFilmById(id);
        if (film != null) {
            films.remove(film);
            return true;
        }
        return false;
    }

    public List<Film> getAllFilms() {
        return new ArrayList<>(films);
    }

    public Film getFilmById(int id) {
        return films.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

}