package organigrammaAziendale.composite;

import organigrammaAziendale.command.AggiungiElementoC;
import organigrammaAziendale.command.Command;
import organigrammaAziendale.command.RimuoviElementoC;

// per ora faccio finta che non c'è observer e quindi non estendo subject

public class Organigramma {
    private ElementoOrganigramma root;

    public ElementoOrganigramma getRoot() {
        return root;
    }

    public void setRoot(ElementoOrganigramma root) {
        this.root = root;
        // notifyObservers();
    }

    public void aggiungiElemento(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        Command add = new AggiungiElementoC(genitore,elemento);
        add.execute();
    }

    public void rimuoviElemento(ElementoOrganigramma genitore, ElementoOrganigramma elemento) {
        Command remove = new RimuoviElementoC(genitore,elemento);
        remove.execute();
    }
/*

    //main
    public static void main(String[] args) {
        // Creazione dell'organigramma
        Organigramma organigramma = new Organigramma();

        // Creazione della radice dell'albero
        UnitaOrganizzativa root = new UnitaOrganizzativa("CEO");

        // Impostazione della radice dell'organigramma
        organigramma.setRoot(root);

        // Creazione delle unità organizzative
        UnitaOrganizzativa marketing = new UnitaOrganizzativa("Marketing");
        UnitaOrganizzativa sales = new UnitaOrganizzativa("Sales");
        UnitaOrganizzativa hr = new UnitaOrganizzativa("HR");
        UnitaOrganizzativa boh = new UnitaOrganizzativa("UnitàBoh");

        // Aggiunta delle unità organizzative alla radice
        organigramma.aggiungiElemento(root, marketing);
        organigramma.aggiungiElemento(root, sales);
        organigramma.aggiungiElemento(root, hr);
        organigramma.aggiungiElemento(hr, boh);

        // Creazione dei ruoli
        Ruolo digitalMarketing = new Ruolo("Digital Marketing");
        Ruolo contentMarketing = new Ruolo("Content Marketing");
        Ruolo salesManager = new Ruolo("Sales Manager");
        Ruolo recruiter = new Ruolo("Recruiter");
        Ruolo ruoloboh = new Ruolo("RuoloBoh");

        // Aggiunta dei ruoli alle rispettive unità organizzative
        organigramma.aggiungiElemento(marketing, digitalMarketing);
        organigramma.aggiungiElemento(marketing, contentMarketing);
        organigramma.aggiungiElemento(sales, salesManager);
        organigramma.aggiungiElemento(hr, recruiter);
        organigramma.aggiungiElemento(boh, ruoloboh);

        // Stampa dell'organigramma originale
        System.out.println("Organigramma originale:");
        printOrganigramma(root, 0);
        System.out.println("");

        // Estrazione e stampa del sotto-albero di HR
        ElementoOrganigramma sottoAlberoHR = organigramma.getSottoAlbero(hr);
        System.out.println("Sotto-albero di HR:");
        printOrganigramma(sottoAlberoHR, 0);
        System.out.println("");

        // Rimozione di un elemento e stampa dell'organigramma aggiornato
        organigramma.rimuoviElemento(boh, ruoloboh);
        System.out.println("Organigramma dopo la rimozione di 'RuoloBoh' da 'UnitàBoh':");
        printOrganigramma(root, 0);
    }
*/


    // Metodo per stampare l'organigramma
    public static void printOrganigramma(ElementoOrganigramma elemento, int level) {
        // Indentazione basata sul livello
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + elemento.getNome());

        // Stampa dei figli (se presenti)
        if (elemento instanceof UnitaOrganizzativa) {
            for (ElementoOrganigramma figlio : ((UnitaOrganizzativa) elemento).getElementi()) {
                printOrganigramma(figlio, level + 1);
            }
        }
    }
    //metodi fatti da chatGPT
    public ElementoOrganigramma getSottoAlbero(ElementoOrganigramma elementoOrganigramma) {
        if (elementoOrganigramma instanceof UnitaOrganizzativa) {
            UnitaOrganizzativa originale = (UnitaOrganizzativa) elementoOrganigramma;
            UnitaOrganizzativa copia = new UnitaOrganizzativa(originale.getNome());
            for (ElementoOrganigramma figlio : originale.getElementi()) {
                copia.add(getSottoAlbero(figlio));
            }
            return copia;
        } else {
            return new Ruolo(elementoOrganigramma.getNome());
        }
    }

    // Metodo per trovare un elemento per nome
    public ElementoOrganigramma trovaElementoPerNome(String nome) {
        if (root != null) {
            return trovaElementoPerNomeRecursivo(root, nome);
        }
        return null;
    }

    private ElementoOrganigramma trovaElementoPerNomeRecursivo(ElementoOrganigramma elemento, String nome) {
        if (elemento.getNome().equals(nome)) {
            return elemento;
        }
        if (elemento instanceof UnitaOrganizzativa) {
            for (ElementoOrganigramma figlio : ((UnitaOrganizzativa) elemento).getElementi()) {
                ElementoOrganigramma risultato = trovaElementoPerNomeRecursivo(figlio, nome);
                if (risultato != null) {
                    return risultato;
                }
            }
        }
        return null;
    }

}