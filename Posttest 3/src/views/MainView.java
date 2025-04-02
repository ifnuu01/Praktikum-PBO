package views;

import services.AuthService;
import utils.InputUtils;
import models.Admin;

public class MainView {
    private AuthService authService;
    private AdminView adminView;
    private CustomerView customerView;

    public MainView(AuthService authService, AdminView adminView, CustomerView customerView) {
        this.authService = authService;
        this.adminView = adminView;
        this.customerView = customerView;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== CINEMA TICKET BOOKING ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            int choice = InputUtils.getIntInput("Pilih menu: ");

            switch (choice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registerMenu();
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void loginMenu() {
        System.out.println("\n=== LOGIN ===");
        String email = InputUtils.getStringInput("Email: ");
        String password = InputUtils.getStringInput("Password: ");

        if (authService.login(email, password)) {
            System.out.println("Login berhasil!");
            if (authService.getCurrentUser() instanceof Admin) {
                adminView.showAdminMenu();
            } else {
                customerView.showCustomerMenu();
            }
        } else {
            System.out.println("Email atau password salah!");
        }
    }

    private void registerMenu() {
        System.out.println("\n=== REGISTER ===");
        String nama = InputUtils.getStringInput("Nama: ");
        String email = InputUtils.getStringInput("Email: ");
        String password = InputUtils.getStringInput("Password: ");

        if (authService.register(nama, email, password)) {
            System.out.println("Registrasi berhasil! Silakan login.");
        } else {
            System.out.println("Email sudah terdaftar!");
        }
    }
}