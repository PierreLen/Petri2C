package model;

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

    /**
     * Met a jour les éléments graphiques de l'arc
     */
    public void update() {

        this.getChildren().clear();

        // ne pas supprimer
/*QuadCurve quadCurve = new QuadCurve();
        quadCurve.setStartX(destination.getX());
        quadCurve.setStartY(destination.getY() + PlaceComponent.getBaseRadius());
        quadCurve.setEndX(origine.getX());
        quadCurve.setEndY(origine.getY());
        double controlX = (quadCurve.getStartX() + quadCurve.getEndX()) / 2;
        double controlY = (quadCurve.getStartY() + quadCurve.getEndY()) / 2;
        milieu = new Point(controlX, controlY);
        quadCurve.setControlX(controlX);
        quadCurve.setControlY(controlY);
        quadCurve.setFill(Color.TRANSPARENT);
        quadCurve.setStroke(Color.BLACK);
        this.getChildren().add(quadCurve);*/

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        final int placeX = origine.getX();
        final int placeY = origine.getY();
        final int tx = destination.getX();
        final int ty = destination.getY();

        double x = placeX - tx;
        double y = placeY - ty;

        double norme = Math.sqrt(x * x + y * y);
        double ux = x / norme;
        double uy = y / norme;

        double sx = -ux * Place.getBaseRadius() + placeX;
        double sy = -uy * Place.getBaseRadius() + placeY;

        moveTo.setX(sx);
        moveTo.setY(sy);
        LineTo lineTo = new LineTo();
        lineTo.setX(tx);
        lineTo.setY(ty);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);


        // triangle de la fleche

        double ax = tx;
        double ay;
        if (placeY < ty)
            ay = ty - 3;
        else
            ay = ty + 3;

        double triangleDist = 10;
        double trX = ux * triangleDist + tx;
        double trY = uy * triangleDist + ty;

        double c = Math.cos(30 * Math.PI / 180);
        double s = Math.sin(30 * Math.PI / 180);
        //  multiplication par la matrice de rotation horaire pour avoir les coordonnées
        double bx = ((trX - ax) * c + (trY - ay) * s) + ax;
        double by = (-(trX - ax) * s + (trY - ay) * c) + ay;
        // rotation antihoraire
        double cx = ((trX - ax) * c - (trY - ay) * s) + ax;
        double cy = ((trX - ax) * s + (trY - ay) * c) + ay;

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(ax, ay, bx, by, cx, cy);
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


