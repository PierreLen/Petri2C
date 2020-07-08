package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Arc extends PetriNetComponent {

    protected int poids;

    int xFrom;
    int yFrom;
    int xTo;
    int yTo;

    public Arc() {
        super();
        this.poids = 1;
    }

    public Arc(int poids) {
        this();
        this.poids = poids;
    }

    /**
     * Ce constructeur permet de dessiner les arcs temporaires qui n'ont pas de destination
     *
     * @param xFrom le x de l'origine
     * @param yFrom le y de l'origine
     * @param xTo   le x de la souris
     * @param yTo   le y de la souris
     * @param type  1 pour arc post; 0 pour arc pre. Mettre une enum ?
     */
    public Arc(int xFrom, int yFrom, int xTo, int yTo, int type) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        LineTo lineTo = new LineTo();
        if (type == 1) {
            moveTo.setX(xFrom);
            moveTo.setY(yFrom + 3);
            // si on ne retire pas quelques pixels, le click ne se déclenche pas sur les places/transistions
            lineTo.setX(xTo-3);
            lineTo.setY(yTo-3);
        } else {
            moveTo.setX(xFrom);
            moveTo.setY(yFrom + Place.getBaseRadius());
            lineTo.setX(xTo+3);
            lineTo.setY(yTo+3);
        }
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getxTo() {
        return xTo;
    }

    public void setxTo(int xTo) {
        this.xTo = xTo;
    }

    public int getyTo() {
        return yTo;
    }

    public void setyTo(int yTo) {
        this.yTo = yTo;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "xFrom=" + xFrom +
                ", yFrom=" + yFrom +
                ", xTo=" + xTo +
                ", yTo=" + yTo +
                '}';
    }
}
