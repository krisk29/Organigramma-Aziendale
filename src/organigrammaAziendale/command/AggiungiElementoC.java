package organigrammaAziendale.command;

import organigrammaAziendale.composite.ElementoOrganigramma;

public class AggiungiElementoC implements Command{

    ElementoOrganigramma genitore;
    ElementoOrganigramma elemento;

    public AggiungiElementoC(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        this.genitore = genitore;
        this.elemento = elemento;
    }

    @Override
    public void execute() {
        if (genitore != null) {
            genitore.add(elemento);
            // notifyObservers();
        }
    }

}