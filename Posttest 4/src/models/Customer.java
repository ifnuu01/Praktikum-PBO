package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private double saldo;
    private List<Tiket> tiketList;

    public Customer(int id, String nama, String email, String password) {
        super(id, nama, email, password);
        this.saldo = 0;
        this.tiketList = new ArrayList<>();
    }

    @Override
    public String getRoleInfo() {
        return "Customer - Pembeli tiket";
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