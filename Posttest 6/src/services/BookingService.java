package services;

import models.*;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Tiket> tikets;
    private int lastTiketId = 0;

    public BookingService() {
        this.tikets = new ArrayList<>();
    }

    public Tiket createTiket(Customer customer, Jadwal jadwal, String kursi) {
        Tiket newTiket = new Tiket(++lastTiketId, customer, jadwal, kursi);
        tikets.add(newTiket);
        customer.getTiketList().add(newTiket);
        return newTiket;
    }

    public boolean processPayment(Tiket tiket, Customer customer) {
        double harga = tiket.getJadwal().getHarga();
        if (customer.pay(harga)) {
            tiket.setPaid(true);
            return true;
        }
        return false;
    }

    public List<Tiket> getTiketByCustomer(Customer customer) {
        return new ArrayList<>(customer.getTiketList());
    }
}