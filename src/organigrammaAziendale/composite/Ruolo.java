package organigrammaAziendale.composite;

import java.io.Serializable;
import java.util.List;

//foglia della classe ElementoOrganigramma (leaf di composite), non può avere figli

public class Ruolo implements ElementoOrganigramma, Serializable {
    private String nome;

    public Ruolo(String nome) {
        this.nome = nome;
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

    public String toString(){ return nome; }
}

