package components;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class ArcPreComponent extends Parent {
    private PlaceComponent place;
    private TransitionComponent transition;

    public ArcPreComponent(PlaceComponent place, TransitionComponent transition) {
        this.place = place;
        this.transition = transition;
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(place.getX());
        moveTo.setY(place.getY() + PlaceComponent.getBaseRadius());
        LineTo lineTo = new LineTo();
        lineTo.setX(transition.getX());
        lineTo.setY(transition.getY());
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
        // triangle de la fleche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                (double) transition.getX(),
                (double) transition.getY()-3,
                (double) transition.getX() - 6,
                (double) transition.getY() - 12,
                (double) transition.getX() + 6,
                (double) transition.getY() - 12);
        this.getChildren().add(triangle);
    }
    public void update() {
        this.getChildren().clear();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(place.getX());
        moveTo.setY(place.getY() + PlaceComponent.getBaseRadius());
        LineTo lineTo = new LineTo();
        lineTo.setX(transition.getX());
        lineTo.setY(transition.getY());
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
        // triangle de la fleche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                (double) transition.getX(),
                (double) transition.getY()-3,
                (double) transition.getX() - 6,
                (double) transition.getY() - 12,
                (double) transition.getX() + 6,
                (double) transition.getY() - 12);
        this.getChildren().add(triangle);
    }
}
