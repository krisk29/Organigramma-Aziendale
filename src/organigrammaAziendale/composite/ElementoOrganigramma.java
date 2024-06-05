package organigrammaAziendale.composite;

import organigrammaAziendale.visitor.ElementoOrganigrammaVisitor;

import java.util.List;

//design pattern Composite, per la gestione dell'albero dei ruoli

public interface ElementoOrganigramma {
    String getNome();
    void setNome(String nome);
    void add(ElementoOrganigramma elemento);
    void remove(ElementoOrganigramma elemento);
    List<ElementoOrganigramma> getElementi();   //getChild
    void accept(ElementoOrganigrammaVisitor visitor);   //visitor per eseguire operazioni sugli oggetti
//dall'esempio del main
    void setX(int x);
    void setY(int y);

    int getX();
    int getY();
}

