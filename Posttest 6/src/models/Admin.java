package models;

public final class Admin extends User {
    private final String role = "Admin";

    public Admin(int id, String nama, String email, String password) {
        super(id, nama, email, password);
    }

    @Override
    public final String getRoleInfo() {
        return role + " - Mengelola film dan jadwal tayang";
    }
}