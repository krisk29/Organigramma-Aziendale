package organigrammaAziendale.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UnitaOrganizzativa implements ElementoOrganigramma, Serializable {
    private String nome;
    private List<ElementoOrganigramma> elementi;    //unità organizzativa dentro un'altra unità organizzativa

    public UnitaOrganizzativa(String nome) {
        this.nome = nome;
        this.elementi = new ArrayList<>();
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
        elementi.add(elemento);
    }

    @Override
    public void remove(ElementoOrganigramma elemento) {
        elementi.remove(elemento);
    }

    @Override
    public List<ElementoOrganigramma> getElementi() {
        return elementi;
    }

    public String toString(){
        return nome;
    }

}
