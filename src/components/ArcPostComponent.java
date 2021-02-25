package components;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import modele.ArcPost;

public class ArcPostComponent extends Parent {
    private PlaceComponent place;
    private TransitionComponent transition;

    private ArcPost arcPost;

    public ArcPostComponent(TransitionComponent transition, PlaceComponent place) {
        this.place = place;
        this.transition = transition;

        /*Path path = new Path();
        MoveTo moveTo = new MoveTo();
        // départ de la flèche
        moveTo.setX(transition.getX());
        moveTo.setY(transition.getY() + 3);


        LineTo lineTo = new LineTo();
        lineTo.setX(place.getX());
        lineTo.setY(place.getY());
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
        // triangle de la fleche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                (double) place.getX(),
                (double) place.getY() - PlaceComponent.getBaseRadius(),
                (double) place.getX() - 6,
                (double) place.getY() - 12 - PlaceComponent.getBaseRadius(),
                (double) place.getX() + 6,
                (double) place.getY() - 12 - PlaceComponent.getBaseRadius());
        this.getChildren().add(triangle);*/
        update();
    }

    public void setArcPost(ArcPost arcPost) {
        if (arcPost.getTransitionO() == transition.getTransition() && arcPost.getPlaceDest() == place.getPlace())
            this.arcPost = arcPost;
    }

    public void update() {
        this.getChildren().clear();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();

        // variables plus courtes à manipuler
        final int tx = transition.getX();
        final int ty = transition.getY();
        final int px = place.getX();
        final int py = place.getY();

        // position de la ligne de la flèche en fonction de la position relative de la place
        moveTo.setX(tx);
        if (place.getY() > transition.getY())
            moveTo.setY(ty + 3);
        else
            moveTo.setY(ty - 2);


        LineTo lineTo = new LineTo();
        // transformation en vecteur
        double x = px - tx;
        double y = py - ty;
        // nomalisation du vecteur pour avoir un vecteur compris entre -1 et 1
        double norme = Math.sqrt(x * x + y * y);
        double ux = x / norme;
        double uy = y / norme;

        // calcul des coordonées de la fin de la ligne de la flèche
        double dist = norme - PlaceComponent.getBaseRadius();
        double xdist = ux * dist;
        double ydist = uy * dist;

        // ajout au composant
        lineTo.setX(tx + xdist);
        lineTo.setY(ty + ydist);
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);

        // coordonées du point A du triangle collé contre le cercle
        double ax = tx + xdist;
        double ay = ty + ydist;

        // calcul des coordonées d'un point que l'on va faire pivoter de 30 ° en horaire et antihoraire
        // pour avoir les points B et C
        double triangleDist = norme - PlaceComponent.getBaseRadius() - 10;
        double trX = ux * triangleDist + tx;
        double trY = uy * triangleDist + ty;
        // sinus et cosinus de 30°
        double c = Math.cos(30 * Math.PI / 180);
        double s = Math.sin(30 * Math.PI / 180);
        //  multiplication par la matrice de rotation horaire pour avoir les coordonnées
        double bx = ((trX - ax) * c + (trY - ay) * s) + ax;
        double by = (-(trX - ax) * s + (trY - ay) * c) + ay;
        // rotation antihoraire
        double cx = ((trX - ax) * c - (trY - ay) * s) + ax;
        double cy = ((trX - ax) * s + (trY - ay) * c) + ay;


        // triangle de la flèche
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(ax, ay, bx, by, cx, cy);
        this.getChildren().add(triangle);
    }

    public PlaceComponent getPlace() {
        return place;
    }
}
