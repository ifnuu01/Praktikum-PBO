import services.*;
import views.*;

public class App {
    public static void main(String[] args) {

        AuthService authService = new AuthService();
        FilmService filmService = new FilmService();
        StudioService studioService = new StudioService();
        BookingService bookingService = new BookingService();

        AdminView adminView = new AdminView(authService, filmService, studioService);
        CustomerView customerView = new CustomerView(authService, filmService, studioService, bookingService);
        MainView mainView = new MainView(authService, adminView, customerView);

        mainView.showMainMenu();
    }
}