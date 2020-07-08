package model;

import javafx.scene.Parent;
import sample.editorMain.DrawingZone;

public abstract class PetriNetComponent extends Parent {

    private static int LAST_ID = 0;
    protected int x;
    protected int y;
    protected int pnId;
    protected String description;
    private DrawingZone parentController;

    public PetriNetComponent() {
        this.x = 0;
        this.y = 0;
        this.pnId = ++LAST_ID;
    }

    public PetriNetComponent(int x, int y, DrawingZone parentController) {
        this();
        this.x = x;
        this.y = y;
        this.parentController = parentController;
        this.setTranslateX(x);
        this.setTranslateY(y);
    }


    public int getPnId() {
        return pnId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
        this.setTranslateX(x);
    }

    public void setY(int y) {
        this.y = y;
        this.setTranslateY(y);
    }
}
