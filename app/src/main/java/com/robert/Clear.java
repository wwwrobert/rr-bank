package com.robert;

import java.io.IOException;
import java.util.Scanner;

public class Clear {

    public static void limparConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Se for Windows, usa o comando "cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Se for Unix-like (Linux, MacOS), usa códigos de escape ANSI
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao tentar limpar o console: " + e.getMessage());
        }
    }

    public static void aguardarEnter(Scanner scanner) {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine(); 
        scanner.nextLine(); 
    }

}
