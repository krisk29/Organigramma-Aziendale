package organigrammaAziendale.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Organigramma implements Serializable {
    private ElementoOrganigramma root;
    private HashMap<Ruolo, ArrayList<String>> ruoliMap;
    private static final long SerialVersionUID = 1L;

    public Organigramma() {
        this.ruoliMap = new HashMap<>();
    }

    public ElementoOrganigramma getRoot() {
        return root;
    }

    public void setRoot(ElementoOrganigramma root) {
        this.root = root;
    }

    public static void printOrganigramma(ElementoOrganigramma elemento, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + elemento.getNome());

        if (elemento instanceof UnitaOrganizzativa) {
            for (ElementoOrganigramma figlio : ((UnitaOrganizzativa) elemento).getElementi()) {
                printOrganigramma(figlio, level + 1);
            }
        }
    }

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

    public void aggiungiPersonaAlRuolo(Ruolo ruolo, String persona) {
        ruoliMap.computeIfAbsent(ruolo, k -> new ArrayList<>()).add(persona);
    }

    public void rimuoviPersonaDalRuolo(Ruolo ruolo, String persona) {
        ArrayList<String> persone = ruoliMap.get(ruolo);
        if (persone != null) {
            persone.remove(persona);
            if (persone.isEmpty()) {
                ruoliMap.remove(ruolo);
            }
        }
    }

    public ArrayList<String> getPersonePerRuolo(Ruolo ruolo) {
        return ruoliMap.getOrDefault(ruolo, new ArrayList<>());
    }

    public String toString(){
        return getRoot().getNome();
    }

}
