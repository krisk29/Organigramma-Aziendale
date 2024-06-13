package organigrammaAziendale.composite;

import organigrammaAziendale.command.AggiungiElementoC;
import organigrammaAziendale.command.Command;
import organigrammaAziendale.command.RimuoviElementoC;

public class Organigramma {
    private ElementoOrganigramma root;

    public ElementoOrganigramma getRoot() {
        return root;
    }

    public void setRoot(ElementoOrganigramma root) {
        this.root = root;

    }

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