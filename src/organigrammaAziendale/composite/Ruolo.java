package organigrammaAziendale.composite;

import java.util.List;

//foglia della classe ElementoOrganigramma (leaf di composite), non può avere figli

public class Ruolo implements ElementoOrganigramma {
    private String nome;
    private int x, y;


    public Ruolo(String nome) {
        this.nome = nome;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void add(ElementoOrganigramma elemento) {
        throw new UnsupportedOperationException("Un ruolo non può contenere altri elementi.");
    }

    @Override
    public void remove(ElementoOrganigramma elemento) {
        throw new UnsupportedOperationException("Un ruolo non può contenere altri elementi.");
    }

    @Override
    public List<ElementoOrganigramma> getElementi() {
        throw new UnsupportedOperationException("Un ruolo non contiene altri elementi.");
    }

    // dal main di esempio di disegna visitor
    // Metodi getter e setter per le coordinate
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

