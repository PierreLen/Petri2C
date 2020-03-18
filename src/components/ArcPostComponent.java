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

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(transition.getX());
        moveTo.setY(transition.getY() + 3);


        LineTo lineTo = new LineTo();
        lineTo.setX(place.getX());
        lineTo.setY(place.getY() - PlaceComponent.getBaseRadius());
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
        this.getChildren().add(triangle);
    }

    public void setArcPost(ArcPost arcPost) {
        if (arcPost.getTransitionO() == transition.getTransition() && arcPost.getPlaceDest() == place.getPlace())
            this.arcPost = arcPost;
    }

    public void update(){
        this.getChildren().clear();
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(transition.getX());
        moveTo.setY(transition.getY() + 3);


        LineTo lineTo = new LineTo();
        lineTo.setX(place.getX());
        lineTo.setY(place.getY() - PlaceComponent.getBaseRadius());
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
        this.getChildren().add(triangle);
    }

    public PlaceComponent getPlace() {
        return place;
    }
}
