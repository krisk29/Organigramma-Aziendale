package organigrammaAziendale.observer;

import java.util.ArrayList;
import java.util.List;

//metodi del pattern observer

public class Subject {
    private List<Observer> osservatori = new ArrayList<>();

    public void addObserver(Observer osservatore) {
        osservatori.add(osservatore);
    }

    public void removeObserver(Observer osservatore) {
        osservatori.remove(osservatore);
    }

    protected void notifyObservers() {
        for (Observer osservatore : osservatori) {
            osservatore.update();
        }
    }
}

