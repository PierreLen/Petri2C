package components;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import modele.Place;

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

        // ne pas supprimer
        /*QuadCurve quadCurve = new QuadCurve();
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
        this.getChildren().add(quadCurve);*/

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        final int placeX = place.getX();
        final int placeY = place.getY();
        final int tx = transition.getX();
        final int ty = transition.getY();

        double x = placeX - tx;
        double y = placeY - ty;

        double norme = Math.sqrt(x * x + y * y);
        double ux = x / norme;
        double uy = y / norme;

        double sx = -ux * PlaceComponent.getBaseRadius() + placeX;
        double sy = -uy * PlaceComponent.getBaseRadius() + placeY;

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

    /**
     * Potentiellement utile, a voir
     *
     * @param x    int
     * @param y    int
     * @param minX int
     * @param minY int
     * @param maxX int
     * @param maxY int
     *
     * @return p
     */
    private Point getPointOnRect(int x, int y, int minX, int minY, int maxX, int maxY) {
        var midX = (minX + maxX) / 2;
        var midY = (minY + maxY) / 2;

        // coté haut
        if (y <= minY) {
            int x1 = x, x2 = midX, x3 = minX, x4 = maxX;
            int y1 = y, y2 = midY, y3 = minY, y4 = minY;
            double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
            double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
//            double u = ((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

            int px = (int) (x1 + t * (x2 - x1));
            int py = (int) (y1 + t * (y2 - y1));
            System.out.println(new Point(px, py));
            return new Point(px, py);
        }


        return new Point(0, 0);
    }


    public PlaceComponent getPlace() {
        return place;
    }
}


