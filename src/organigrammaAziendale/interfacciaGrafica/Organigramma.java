package organigrammaAziendale.interfacciaGrafica;

import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.observer.Subject;

public class Organigramma extends Subject {
    private UnitaOrganizzativa root;

    public UnitaOrganizzativa getRoot() {
        return root;
    }

    public void setRoot(UnitaOrganizzativa root) {
        this.root = root;
        notifyObservers();
    }

    // Metodi per aggiungere/rimuovere elementi
    public void addElemento(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        genitore.add(elemento);
        notifyObservers();
    }

    public void removeElemento(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        genitore.remove(elemento);
        notifyObservers();
    }
}