package services;

import models.Admin;
import models.Customer;
import models.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users;
    private User currentUser;

    public AuthService() {
        this.users = new ArrayList<>();

        users.add(new Admin(1, "Admin", "admin@cinema.com", "admin123"));
    }

    public boolean register(String nama, String email, String password) {

        if (users.stream().anyMatch(u -> u.getEmail().equals(email))) {
            return false;
        }

        int newId = users.size() + 1;
        Customer newCustomer = new Customer(newId, nama, email, password);
        users.add(newCustomer);
        return true;
    }

    public boolean login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}