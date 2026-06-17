import java.util.*;

public class Adresse {
    private String numero;
    private String rue;
    private String ville;
    private String codePostale;

    public String getNumero(){
        return numero;
    }

    public void setNumero(String n){
        this.numero = n;
    }

    public String getRue(){
        return rue;
    }

    public void setRue(String r){
        this.rue = r;
    }

    public String getVille(){
        return ville;
    }

    public void setVille(String v){
        this.ville = v
    }

    public String getCodePostale(){
        return codePostale;
    }

    public void setCodePostale(String cP){
        this.codePostale = cP;
    }
    
    public Adresse(String n, String r, String v, String cP) {
        this.numero = n;
        this.rue = r;
        this.ville = v;
        this.codePostale = cP;
    }
}