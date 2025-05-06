import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private List<Rifugio> rifugi;

    public ClientHandler(Socket socket, List<Rifugio> rifugi) {
        this.clientSocket = socket;
        this.rifugi = rifugi;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ) {
            out.println("Benvenuto! Scrivi AIUTO per vedere i comandi disponibili.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] parts = inputLine.trim().split("\\s+");
                String command = parts[0].toUpperCase();

                switch (command) {
                    case "AIUTO":
                        out.println("Comandi disponibili:");
                        out.println("- AIUTO: mostra questo messaggio");
                        out.println("- MOSTRA_TUTTO: elenca tutti i rifugi");
                        out.println("- MOSTRA_RIGA <n>: mostra il rifugio alla riga n (0-based)");
                        out.println("- MOSTRA_COMUNE <nome_comune>: elenca i rifugi nel comune specificato");
                        out.println("- MOSTRA_ALTITUDINE <min> <max>: elenca i rifugi con altitudine nel range indicato");
                        out.println("- ESCI: termina la connessione");
                        break;

                    case  "MOSTRA_TUTTO":
                        for (int i = 0; i < rifugi.size(); i++) {
                            out.println(i + ": " + rifugi.get(i).toString());
                        }
                        break;

                    case "MOSTRA_RIGA":
                        if (parts.length < 2) {
                            out.println("Uso corretto: MOSTRA_RIGA <numero>");
                            break;
                        }
                        try {
                            int index = Integer.parseInt(parts[1]);
                            if (index >= 0 && index < rifugi.size()) {
                                out.println(rifugi.get(index).toString());
                            } else {
                                out.println("Indice fuori intervallo.");
                            }
                        } catch (NumberFormatException e) {
                            out.println("Indice non valido.");
                        }
                        break;

                    case "MOSTRA_COMUNE":
                        if (parts.length < 2) {
                            out.println("Uso corretto: MOSTRA_COMUNE <nome_comune>");
                            break;
                        }
                        String comune = inputLine.substring(inputLine.indexOf(" ") + 1).toLowerCase();
                        List<Rifugio> perComune = rifugi.stream()
                                .filter(r -> r.getComune().toLowerCase().contains(comune))
                                .collect(Collectors.toList());

                        if (perComune.isEmpty()) {
                            out.println("Nessun rifugio trovato nel comune specificato.");
                        } else {
                            perComune.forEach(r -> out.println(r.toString()));
                        }
                        break;

                    case "MOSTRA_ALTITUDINE":
                        if (parts.length < 3) {
                            out.println("Uso corretto: MOSTRA_ALTITUDINE <min> <max>");
                            break;
                        }
                        try {
                            int min = Integer.parseInt(parts[1]);
                            int max = Integer.parseInt(parts[2]);
                            List<Rifugio> perAltitudine = rifugi.stream()
                                    .filter(r -> r.getAltitudine() >= min && r.getAltitudine() <= max)
                                    .collect(Collectors.toList());

                            if (perAltitudine.isEmpty()) {
                                out.println("Nessun rifugio trovato nel range specificato.");
                            } else {
                                perAltitudine.forEach(r -> out.println(r.toString()));
                            }
                        } catch (NumberFormatException e) {
                            out.println("I valori di altitudine devono essere numeri interi.");
                        }
                        break;

                    case "ESCI":
                        out.println("Connessione terminata.");
                        return;

                    default:
                        out.println("Comando non riconosciuto. Scrivi AIUTO per vedere i comandi disponibili.");
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nella comunicazione con il client: " + e.getMessage());
        }
    }
}