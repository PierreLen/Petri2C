package components;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ArcComponent extends Parent {

    int xFrom;
    int yFrom;
    int xTo;
    int yTo;

    public ArcComponent(int xFrom, int yFrom, int xTo, int yTo, int type) {
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;


        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        LineTo lineTo = new LineTo();

        // Arc Post
        if (type == 1) {
            moveTo.setX(xFrom);
            moveTo.setY(yFrom + 3);
            lineTo.setX(xTo);
            lineTo.setY(yTo - PlaceComponent.getBaseRadius());
        }
        else {
            moveTo.setX(xFrom);
            moveTo.setY(yFrom + PlaceComponent.getBaseRadius());
            lineTo.setX(xTo);
            lineTo.setY(yTo);
        }
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setFill(Color.BLACK);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(2);
        this.getChildren().add(path);
    }
}
