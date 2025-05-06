import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {

    private static final String INDIRIZZO_SERVER = "localhost";
    private static final int PORTA_SERVER = 12345;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("     Benvenuto al client Rifugi Alpini  ");
        System.out.println("========================================");
        System.out.println("Comandi disponibili:");
        System.out.println(" - AIUTO                      : mostra questo messaggio");
        System.out.println(" - MOSTRA_TUTTO                   : mostra tutti i rifugi");
        System.out.println(" - MOSTRA_RIGA <n>               : mostra il rifugio alla riga n");
        System.out.println(" - MOSTRA_COMUNE <nome_comune> : filtra per comune");
        System.out.println(" - MOSTRA_ALTITUDINE <min> <max>: filtra per altitudine");
        System.out.println(" - ESCI                      : chiude la connessione");
        System.out.println("========================================");

        try (
                Socket socket = new Socket(INDIRIZZO_SERVER, PORTA_SERVER);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            RequestBuilder requestBuilder = new RequestBuilder();
            ResponseParser responseParser = new ResponseParser();

            // Stampa risposta iniziale dal server
            String serverLine;
            while ((serverLine = in.readLine()) != null && !serverLine.isEmpty()) {
                System.out.println(serverLine);
            }

            while (true) {
                String request = requestBuilder.prossimaRichiesta();
                if (request.isEmpty()) {
                    System.out.println("Comando non riconosciuto. Digita AIUTO per l’elenco dei comandi.");
                    continue;
                }

                out.println(request);

                if (request.equalsIgnoreCase("ESCI")) {
                    break;
                }

                // Legge e mostra la risposta del server finché ci sono righe
                String responseLine;
                while ((responseLine = in.readLine()) != null && !responseLine.isEmpty()) {
                    System.out.println(responseLine);
                }
            }

        } catch (IOException e) {
            System.err.println("Errore di connessione al server: " + e.getMessage());
        }
    }
}