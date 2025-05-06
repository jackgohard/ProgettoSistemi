import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerMain {
    private static final int PORTA = 1300;
    private static final String PERCORSO_CSV = "C:\\Users\\jacopo.rigo\\Documents\\ProgettoSistemi\\Server\\Provincia-Autonoma-di-Bolzano---Elenco-dei-rifugi-alpini.csv";

    public static void main(String[] args) {
        System.out.println("Server dei rifugi in avvio...");

        List<Rifugio> rifugi;
        try {
            rifugi = CSVLoader.caricaDaCSV(PERCORSO_CSV);
            System.out.println("Caricati " + rifugi.size() + " rifugi dal file CSV.");
        } catch (IOException e) {
            System.err.println("Errore nel caricamento del file CSV: " + e.getMessage());
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server in ascolto sulla porta " + PORTA);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, rifugi);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            System.err.println("Errore nel server socket: " + e.getMessage());
        }
    }
}