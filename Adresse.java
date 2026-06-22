import java.util.*;

public class Adresse {
    private String rue;
    private String codePostal;
    private String ville;
    private int numero;

    public Adresse(int numero, String rue, String ville, String codePostal) {
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    @Override
    public String toString() {
        return numero + " " + rue + ", " + codePostal + " " + ville;
    }
}
