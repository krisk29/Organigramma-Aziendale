package organigrammaAziendale.command;


import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import java.util.ArrayList;

public class RimuoviElementoC implements Command {

    ElementoOrganigramma genitore;
    ElementoOrganigramma elemento;

    public RimuoviElementoC(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        this.genitore = genitore;
        this.elemento = elemento;
    }

    @Override
    public void execute() {
        if (genitore != null) {
            rimuoviElementiFigli(elemento);
            genitore.remove(elemento);
            // notifyObservers();
        }
    }

    private void rimuoviElementiFigli(ElementoOrganigramma elemento) {
        if (elemento instanceof UnitaOrganizzativa) {
            for (ElementoOrganigramma figlio : new ArrayList<>(((UnitaOrganizzativa) elemento).getElementi())) {
                rimuoviElementiFigli(figlio);
            }
            ((UnitaOrganizzativa) elemento).getElementi().clear();
        }
    }

}
