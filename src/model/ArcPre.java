package model;

import components.PlaceComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class ArcPre extends Arc {

    private Place origine;
    private Transition destination;


    public ArcPre(Place origine, Transition destination) {
        super();
        this.origine = origine;
        this.destination = destination;
        this.update();

    }

    public ArcPre(int poids, Place origine, Transition destination) {
        super(poids);
        this.origine = origine;
        this.destination = destination;
        this.update();
    }

    public void update() {
        this.getChildren().clear();
        this.xFrom = origine.getX();
        this.yFrom = origine.getY();
        this.xTo = destination.getX();
        this.yTo = destination.getY();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(origine.getX());
        moveTo.setY(origine.getY() + PlaceComponent.getBaseRadius());
        LineTo lineTo = new LineTo();
        lineTo.setX(destination.getX());
        lineTo.setY(destination.getY());
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
        // triangle de la fleche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                (double) destination.getX(),
                (double) destination.getY() - 3,
                (double) destination.getX() - 6,
                (double) destination.getY() - 12,
                (double) destination.getX() + 6,
                (double) destination.getY() - 12);
        this.getChildren().add(triangle);
    }


    public Place getOrigine() {
        return origine;
    }

    public Transition getDestination() {
        return destination;
    }

    public void setOrigine(Place origine) {
        this.origine = origine;
    }

    public void setDestination(Transition destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "ArcPre{" +
                "xFrom=" + xFrom +
                ", yFrom=" + yFrom +
                ", xTo=" + xTo +
                ", yTo=" + yTo +
                '}';
    }
}


