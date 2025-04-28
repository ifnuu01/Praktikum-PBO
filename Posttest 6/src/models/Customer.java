package models;

import java.util.ArrayList;
import java.util.List;

public final class Customer extends User {
    private double saldo;
    private List<Tiket> tiketList;
    private final String role = "Customer";

    public Customer(int id, String nama, String email, String password) {
        super(id, nama, email, password);
        this.saldo = 0;
        this.tiketList = new ArrayList<>();
    }

    @Override
    public final String getRoleInfo() {
        return role + " - Pembeli tiket";
    }

    public void topUpSaldo(double amount) {
        saldo += amount;
    }

    public boolean pay(double amount) {
        if (saldo >= amount) {
            saldo -= amount;
            return true;
        }
        return false;
    }

    // Getters & Setters
    public double getSaldo() {
        return saldo;
    }

    public List<Tiket> getTiketList() {
        return tiketList;
    }
}