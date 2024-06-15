package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.Organigramma;
import organigrammaAziendale.composite.Ruolo;

import javax.swing.*;
import java.util.ArrayList;

public class AggiornaPersoneTextArea implements Command {

    private Ruolo ruolo;
    private Organigramma organigramma;;
    private JTextArea personeTextArea;

    public AggiornaPersoneTextArea(Ruolo ruolo, Organigramma organigramma, JTextArea personeTextArea) {
        this.ruolo = ruolo;
        this.organigramma = organigramma;
        this.personeTextArea = personeTextArea;

    }

    @Override
    public void execute() {
        personeTextArea.setText("");
        if (ruolo != null) {
            ArrayList<String> persone = organigramma.getPersonePerRuolo(ruolo);
            for (String persona : persone) {
                personeTextArea.append(persona + "\n");
            }
        }
    }

}