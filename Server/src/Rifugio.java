public class Rifugio {
    private final String comune;
    private final String provincia;
    private final String nomeItaliano;
    private final String nomeTedesco;
    private final int altitudine;
    private final String proprieta;
    private final String telefono;
    private final String email;
    private final String sitoWeb;
    private final String gruppo;

    public Rifugio(String comune, String provincia, String nomeItaliano, String nomeTedesco,
                   int altitudine, String proprieta, String telefono,
                   String email, String sitoWeb, String gruppo) {
        this.comune = comune;
        this.provincia = provincia;
        this.nomeItaliano = nomeItaliano;
        this.nomeTedesco = nomeTedesco;
        this.altitudine = altitudine;
        this.proprieta = proprieta;
        this.telefono = telefono;
        this.email = email;
        this.sitoWeb = sitoWeb;
        this.gruppo = gruppo;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) | %s / %s | %dm | %s | Tel: %s | Email: %s | Web: %s | Gruppo: %s",
                comune, provincia, nomeItaliano, nomeTedesco, altitudine,
                proprieta, telefono, email, sitoWeb, gruppo);
    }

    public String getComune() {
        return comune;
    }

    public int getAltitudine() {
        return altitudine;
    }
}