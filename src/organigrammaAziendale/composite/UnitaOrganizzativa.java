package organigrammaAziendale.composite;

import organigrammaAziendale.visitor.ElementoOrganigrammaVisitor;

import java.util.ArrayList;
import java.util.List;

//implementazione dell'interfaccia (observer)

public class UnitaOrganizzativa implements ElementoOrganigramma {
    private String nome;
    private List<ElementoOrganigramma> elementi;    //la faccio così altrimenti non potrei inserire un'unità organizzativa dentro un'altra unità organizzativa
    private int x, y;

    public UnitaOrganizzativa(String nome) {
        this.nome = nome;
        this.elementi = new ArrayList<>();
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

    @Override
    public void accept(ElementoOrganigrammaVisitor visitor) {
        visitor.visit(this);
        for (ElementoOrganigramma elemento : elementi) {
            elemento.accept(visitor);
        }
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