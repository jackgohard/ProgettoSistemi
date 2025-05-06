import java.io.*;
import java.util.*;

public class CSVLoader {

    public static List<Rifugio> caricaDaCSV(String csvPath) throws IOException {
        List<Rifugio> rifugi = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(csvPath), "ISO-8859-1"))) {

            String header = reader.readLine(); // salta intestazione

            String line;
            while ((line = reader.readLine()) != null) {
                Rifugio r = parseLine(line);
                if (r != null) {
                    rifugi.add(r);
                }
            }
        }

        return rifugi;
    }

    private static Rifugio parseLine(String line) {
        try {
            String[] parts = line.split(";", -1);

            String comune = parts[0].trim();
            String provincia = parts[1].trim();
            String nomeItaliano = parts[2].trim();
            String nomeTedesco = parts[3].trim();
            int altitudine = (int) Double.parseDouble(parts[4].trim());
            String proprieta = parts[5].trim();
            String telefono = parts[6].trim();
            String email = parts[7].trim();
            String internet = parts[8].trim();
            String gruppo = parts[9].trim();

            return new Rifugio(comune, provincia, nomeItaliano, nomeTedesco,
                    altitudine, proprieta, telefono, email, internet, gruppo);
        } catch (Exception e) {
            System.err.println("Errore nel parsing della riga: " + line);
            return null;
        }
    }
}