package models;

// Menerapkan interface Auth
public interface Auth {
    boolean login(String email, String password);

    boolean register(String name, String email, String password);

    void logout();
}
