public class ResponseParser {

    /**
     * Analizza la risposta del server e ritorna una versione formattata (se necessario).
     *
     * @param response la stringa ricevuta dal server
     * @return una stringa interpretata/visualizzabile
     */
    public static String parse(String response) {
        if (response == null || response.isEmpty()) {
            return "[Risposta vuota dal server]";
        }

        if (response.toLowerCase().contains("errore") || response.toLowerCase().contains("non riconosciuto")) {
            return "[ERRORE] " + response;
        }

        if (response.toLowerCase().contains("connessione terminata")) {
            return "[DISCONNESSO] " + response;
        }

        if (response.contains("\n")) {
            // Probabilmente è un elenco (es. GET_ALL)
            return "[ELENCO RIFUGI]\n" + response;
        }

        return response;
    }
}