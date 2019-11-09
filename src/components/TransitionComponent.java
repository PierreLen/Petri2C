package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import modele.Transition;

import java.util.ArrayList;
import java.util.List;

public class TransitionComponent extends PetriObjectComponent {
    private Transition transition;

    private List<ArcPostComponent> arcPosts = new ArrayList<>();
    private List<ArcPreComponent> arcPres = new ArrayList<>();

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

    public List<ArcPostComponent> getArcPosts() {
        return arcPosts;
    }

    public List<ArcPreComponent> getArcPres() {
        return arcPres;
    }

    public void addArcPre(ArcPreComponent arcPreComponent) {this.arcPres.add(arcPreComponent);}

    public void addArcPost(ArcPostComponent arcPostComponent) {this.arcPosts.add(arcPostComponent);}

    public Transition getTransition() {
        return transition;
    }
}
