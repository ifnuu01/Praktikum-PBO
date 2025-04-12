package models;

public class Admin extends User {
    public Admin(int id, String nama, String email, String password) {
        super(id, nama, email, password);
    }

    @Override
    public String getRoleInfo() {
        return "Admin - Mengelola film dan jadwal tayang";
    }
}