package sample.grapheDeMarquage;

import javafx.beans.property.SimpleStringProperty;

public class MarquageModel {
    private SimpleStringProperty currentMarquage;
    private SimpleStringProperty nextMarquage;

    public MarquageModel(String currentMarquage, String nextMarquage) {
        this.currentMarquage = new SimpleStringProperty(currentMarquage);
        this.nextMarquage = new SimpleStringProperty(nextMarquage);
    }

    public String getCurrentMarquage() {
        return currentMarquage.get();
    }

    public SimpleStringProperty currentMarquageProperty() {
        return currentMarquage;
    }

    public void setCurrentMarquage(String currentMarquage) {
        this.currentMarquage.set(currentMarquage);
    }

    public String getNextMarquage() {
        return nextMarquage.get();
    }

    public SimpleStringProperty nextMarquageProperty() {
        return nextMarquage;
    }

    public void setNextMarquage(String nextMarquage) {
        this.nextMarquage.set(nextMarquage);
    }
}
