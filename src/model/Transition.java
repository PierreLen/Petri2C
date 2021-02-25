package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.editorMain.DrawingZone;

import java.util.HashSet;
import java.util.Set;

public class Transition extends PetriNetComponent {

    private static int LAST_TRANSITION_CREATED = 0;

    private Set<ArcPre> arcPres;
    private Set<ArcPost> arcPosts;
    private Rectangle background;

    public Transition() {
        super();
        this.arcPosts = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.description = "T" + (++LAST_TRANSITION_CREATED);
    }

    public Transition(int x, int y, DrawingZone parentController) {
        super(x, y, parentController);
        this.arcPosts = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.description = "T" + (++LAST_TRANSITION_CREATED);
        this.background = this.getBackground(Color.BLACK);
        this.getChildren().add(background);
        this.getChildren().add(this.getTransitionName());
    }

    public Transition(int x, int y, DrawingZone parentController, String description) {
        super(x, y, parentController);
        this.arcPosts = new HashSet<>();
        this.arcPres = new HashSet<>();
        this.description = description;
        ++LAST_TRANSITION_CREATED;
        this.background = this.getBackground(Color.BLACK);
        this.getChildren().add(background);
        this.getChildren().add(this.getTransitionName());
    }

    /**
     * Change la couleur de la transition en fonction de sa franchissabilité.
     *
     * @param shouldBeColored booléen déterminant si la transition doit être colorée ou non
     */
    public void updateColor(boolean shouldBeColored) {
        this.getChildren().remove(this.background);
        if (shouldBeColored) {
            if (this.isFranchissable()) {
                this.background = this.getBackground(Color.GREEN);
            } else {
                this.background = this.getBackground(Color.RED);
            }
        } else {
            this.background = this.getBackground(Color.BLACK);
        }
        this.getChildren().add(this.background);
    }


    private Rectangle getBackground(Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(40);
        rectangle.setHeight(6);
        rectangle.setTranslateX(-20);
        rectangle.setTranslateY(-3);
        rectangle.setFill(color);
        return rectangle;
    }

    private Text getTransitionName() {
        Text TName = new Text(this.getDescription());
        TName.setFont(new Font(25));
        TName.setTranslateY(5);
        TName.setTranslateX(30);
        return TName;
    }

    public boolean isFranchissable() {
        for (ArcPre arcPre : arcPres) {
            if (arcPre.getOrigine().getNbJetons() < arcPre.getPoids()) {
                return false;
            }
        }
        return true;
    }

    public boolean addArcPost(ArcPost arcPost) {
        return arcPosts.add(arcPost);
    }

    public boolean removeArcPost(ArcPost arcPost) {
        return arcPosts.remove(arcPost);
    }

    public boolean addArcPre(ArcPre arcPre) {
        return arcPres.add(arcPre);
    }

    public boolean removeArcPre(ArcPre arcPre) {
        return arcPres.remove(arcPre);
    }

    public Set<ArcPre> getArcPres() {
        return arcPres;
    }

    public Set<ArcPost> getArcPosts() {
        return arcPosts;
    }


}
