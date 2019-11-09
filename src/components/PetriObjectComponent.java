package components;

import javafx.scene.Parent;

public class PetriObjectComponent extends Parent {

    protected int x;
    protected int y;

    public PetriObjectComponent(int x, int y) {
        this.x = x;
        this.y = y;
        // position
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setTranslateX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setTranslateY(y);
    }
}
