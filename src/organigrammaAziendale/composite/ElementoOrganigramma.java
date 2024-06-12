package organigrammaAziendale.composite;

import java.util.List;

//design pattern Composite, per la gestione dell'albero dei ruoli

public interface ElementoOrganigramma {
    String getNome();
    void setNome(String nome);
    void add(ElementoOrganigramma elemento);
    void remove(ElementoOrganigramma elemento);
    List<ElementoOrganigramma> getElementi();   //getChild
    //per la posizione
    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
}


