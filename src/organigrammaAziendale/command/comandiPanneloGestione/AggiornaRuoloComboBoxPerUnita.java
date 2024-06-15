package organigrammaAziendale.command.comandiPanneloGestione;

import organigrammaAziendale.command.Command;
import organigrammaAziendale.composite.ElementoOrganigramma;
import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;

import javax.swing.*;

public class AggiornaRuoloComboBoxPerUnita implements Command {

    private DefaultComboBoxModel<Ruolo> ruoloModelElimina;
    private UnitaOrganizzativa unitaSelezionata;
    private JComboBox<Ruolo> ruoloComboBoxElimina;

    public AggiornaRuoloComboBoxPerUnita(DefaultComboBoxModel<Ruolo> ruoloModelElimina, UnitaOrganizzativa unitaSelezionata, JComboBox<Ruolo> ruoloComboBoxElimina) {
        this.ruoloModelElimina = ruoloModelElimina;
        this.unitaSelezionata = unitaSelezionata;
        this.ruoloComboBoxElimina = ruoloComboBoxElimina;
    }

    @Override
    public void execute() {
        ruoloModelElimina.removeAllElements();
        if (unitaSelezionata != null) {
            for (ElementoOrganigramma elemento : unitaSelezionata.getElementi()) {
                if (elemento instanceof Ruolo) {
                    ruoloModelElimina.addElement((Ruolo) elemento);
                }
            }
        }
        ruoloComboBoxElimina.repaint(); // Forza il repaint della combobox
    }
}
