package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Screen {
    public static void bersihkanLayar() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void tunggu() {
        System.out.println("Tekan Enter untuk melanjutkan...");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
