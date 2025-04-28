package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static int getIntInput(String prompt) {
        // Error handling untuk input integer
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new NumberFormatException("Input tidak boleh kosong!");
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka! " + e.getMessage());
                System.err.println();
            }
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new NumberFormatException("Input tidak boleh kosong!");
                }
                return Double.parseDouble(input.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka decimal!" + e.getMessage());
                System.err.println();
            }
        }
    }

    public static LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt + " [dd/MM/yyyy]: ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah!");
                System.out.println("Gunakan format dd/MM/yyyy (contoh: 25/12/2023)");
                System.err.println();
            }
        }
    }

    public static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print("Input tidak boleh kosong! " + prompt);
            System.err.println();
        }
    }

    public static String getStringInputV2(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input;
    }

    public static LocalTime getTimeInput(String prompt) {
        while (true) {
            System.out.print(prompt + " [HH:mm]: ");
            String input = scanner.nextLine();
            try {
                return LocalTime.parse(input, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Format waktu salah! Gunakan format HH:mm (contoh: 14:30)");
                System.err.println();
            }
        }
    }

    public static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt + " [y/n]: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Input tidak valid! Masukkan y atau n.");
            System.err.println();
        }
    }
}