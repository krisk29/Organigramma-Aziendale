package organigrammaAziendale.command;

import organigrammaAziendale.composite.Ruolo;
import organigrammaAziendale.composite.UnitaOrganizzativa;

public class AggiungiRuoloCommand implements Command {
    private UnitaOrganizzativa parent;
    private Ruolo child;

    public AggiungiRuoloCommand(UnitaOrganizzativa parent, Ruolo child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void execute() {
        parent.add(child);
    }
}