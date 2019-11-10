package components;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ArcPreComponent extends Parent {
    private PlaceComponent place;
    private TransitionComponent transition;
    private Point milieu;

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
                (double) transition.getY() - 3,
                (double) transition.getX() - 6,
                (double) transition.getY() - 12,
                (double) transition.getX() + 6,
                (double) transition.getY() - 12);
        this.getChildren().add(triangle);
    }

    public void update() {
        this.getChildren().clear();


        QuadCurve quadCurve = new QuadCurve();
        quadCurve.setStartX(place.getX());
        quadCurve.setStartY(place.getY() + PlaceComponent.getBaseRadius());
        quadCurve.setEndX(transition.getX());
        quadCurve.setEndY(transition.getY());
        double controlX = (quadCurve.getStartX() + quadCurve.getEndX()) / 2;
        double controlY = (quadCurve.getStartY() + quadCurve.getEndY()) / 2;
        milieu = new Point(controlX, controlY);
        quadCurve.setControlX(controlX);
        quadCurve.setControlY(controlY);
        quadCurve.setFill(Color.TRANSPARENT);
        quadCurve.setStroke(Color.BLACK);
        this.getChildren().add(quadCurve);
//        Path path = new Path();
//        MoveTo moveTo = new MoveTo();
//        moveTo.setX(place.getX());
//        moveTo.setY(place.getY() + PlaceComponent.getBaseRadius());
//        LineTo lineTo = new LineTo();
//        lineTo.setX(transition.getX());
//        lineTo.setY(transition.getY());
//
//        path.getElements().add(moveTo);
//        path.getElements().add(lineTo);
//        path.setFill(Color.BLACK);
//        path.setStroke(Color.BLACK);
//        path.setStrokeWidth(2);
//        this.getChildren().add(path);
        // triangle de la fleche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                (double) transition.getX(),
                (double) transition.getY() - 3,
                (double) transition.getX() - 6,
                (double) transition.getY() - 12,
                (double) transition.getX() + 6,
                (double) transition.getY() - 12);
        this.getChildren().add(triangle);
    }
}
