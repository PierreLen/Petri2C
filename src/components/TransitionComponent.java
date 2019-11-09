package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import modele.Transition;

public class TransitionComponent extends PetriObjectComponent {
    private Transition transition;

    public TransitionComponent(Transition transition, int x, int y) {
        super(x, y);
        this.transition = transition;
        this.getChildren().add(getBackground());
        this.getChildren().add(getTransitionName());
    }

    public Rectangle getBackground() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(40);
        rectangle.setHeight(6);
        rectangle.setTranslateX(-20);
        rectangle.setTranslateY(-3);
        rectangle.setFill(Color.BLACK);
        return rectangle;
    }

    public Text getTransitionName() {
        Text TName = new Text(transition.getDescription());
        TName.setFont(new Font(25));
        TName.setTranslateY(5);
        TName.setTranslateX(30);
        return TName;
    }


}
