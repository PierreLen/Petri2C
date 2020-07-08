package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class ArcPost extends Arc {

    private Transition origine;
    private Place destination;


    public ArcPost(int poids, Transition origine, Place destination) {
        super(poids);
        this.origine = origine;
        this.destination = destination;
        update();
    }

    public ArcPost(Transition origine, Place destination) {
        this.origine = origine;
        this.destination = destination;
        update();
    }

    public void update() {
        this.getChildren().clear();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(origine.getX());
        moveTo.setY(origine.getY() + 3);
        LineTo lineTo = new LineTo();
        lineTo.setX(destination.getX());
        lineTo.setY(destination.getY() - Place.getBaseRadius());
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
                (double) destination.getY() - Place.getBaseRadius(),
                (double) destination.getX() - 6,
                (double) destination.getY() - 12 - Place.getBaseRadius(),
                (double) destination.getX() + 6,
                (double) destination.getY() - 12 - Place.getBaseRadius());
        this.getChildren().add(triangle);
    }

    public Transition getOrigine() {
        return origine;
    }

    public void setOrigine(Transition origine) {
        this.origine = origine;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }
}
