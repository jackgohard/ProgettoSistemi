import java.util.Scanner;

public class RequestBuilder {

    private Scanner scanner;

    public RequestBuilder() {
        this.scanner = new Scanner(System.in);
    }

    public String prossimaRichiesta() {
        System.out.println("\nInserisci un comando (AIUTO per vedere i comandi disponibili):");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("AIUTO") ||
                input.equalsIgnoreCase("MOSTRA_TUTTO") ||
                input.equalsIgnoreCase("ESCI")) {
            return input.toUpperCase();
        }

        if (input.toUpperCase().startsWith("MOSTRA_RIGA")) {
            String[] parts = input.split("\\s+");
            if (parts.length == 2) {
                try {
                    Integer.parseInt(parts[1]);
                    return "MOSTRA_RIGA " + parts[1];
                } catch (NumberFormatException e) {
                    System.out.println("Errore: inserisci un numero valido dopo MOSTRA_RIGA.");
                }
            } else {
                System.out.println("Uso corretto: MOSTRA_RIGA <numero>");
            }
        }

        if (input.toUpperCase().startsWith("MOSTRA_COMUNE")) {
            String[] parts = input.split("\\s+", 2);
            if (parts.length == 2) {
                return "MOSTRA_COMUNE " + parts[1];
            } else {
                System.out.println("Uso corretto: MOSTRA_COMUNE <nome_comune>");
            }
        }

        if (input.toUpperCase().startsWith("MOSTRA_ALTITUDINE")) {
            String[] parts = input.split("\\s+");
            if (parts.length == 3) {
                try {
                    Integer.parseInt(parts[1]);
                    Integer.parseInt(parts[2]);
                    return "MOSTRA_ALTITUDINE " + parts[1] + " " + parts[2];
                } catch (NumberFormatException e) {
                    System.out.println("Errore: i valori di altitudine devono essere numeri.");
                }
            } else {
                System.out.println("Uso corretto: MOSTRA_ALTITUDINE <min> <max>");
            }
        }

        System.out.println("Comando non valido.");
        return "";
    }
}