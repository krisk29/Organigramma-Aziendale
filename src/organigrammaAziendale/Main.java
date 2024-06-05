package organigrammaAziendale;

import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;
import organigrammaAziendale.interfacciaGrafica.Organigramma;
import organigrammaAziendale.interfacciaGrafica.OrganigrammaGUI;

//esempio di chatgpt
public class Main {
    public static void main(String[] args) {
        // Creazione dell'organigramma
        Organigramma organigramma = new Organigramma();

        // Creazione delle unità organizzative e dei ruoli
        UnitaOrganizzativa azienda = new UnitaOrganizzativa("Azienda");
        UnitaOrganizzativa repartoIT = new UnitaOrganizzativa("Reparto IT");
        UnitaOrganizzativa repartoHR = new UnitaOrganizzativa("Reparto HR");

        Ruolo managerIT = new Ruolo("Manager IT");
        Ruolo sviluppatore = new Ruolo("Sviluppatore");
        Ruolo managerHR = new Ruolo("Manager HR");
        Ruolo recruiter = new Ruolo("Recruiter");

        // Composizione dell'organigramma
        azienda.add(repartoIT);
        azienda.add(repartoHR);

        repartoIT.add(managerIT);
        repartoIT.add(sviluppatore);

        repartoHR.add(managerHR);
        repartoHR.add(recruiter);

        organigramma.setRoot(azienda);

        // Creazione della GUI
        OrganigrammaGUI gui = new OrganigrammaGUI(organigramma);

        // Simulazione di modifiche all'organigramma
        Ruolo nuovoSviluppatore = new Ruolo("Nuovo Sviluppatore");
        organigramma.addElemento(repartoIT, nuovoSviluppatore);
    }
}
