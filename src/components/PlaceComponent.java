package components;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import modele.Place;

import java.util.regex.Pattern;

public class PlaceComponent extends PetriObjectComponent {

    private static final int BASE_RADIUS = 20;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private Place place;

    public PlaceComponent(Place place, int x, int y) {
        super(x, y);
        this.place = place;
        // definition du cercle de la place
        this.getChildren().add(getBackground());
        // texte avec le nombre de jetons
        this.getChildren().add(getNbToken());
        // nom de la place
        this.getChildren().add(getPlaceName());

    }


    private Circle getBackground() {
        Circle background = new Circle();
        background.setRadius(BASE_RADIUS);
        background.setFill(Color.TRANSPARENT);
        background.setStroke(Color.BLACK);
        return background;
    }

    private Text getNbToken() {
        Text nbToken = new Text(Integer.toString(this.place.getNbJetons()));
        nbToken.setFont(new Font(25));
        nbToken.setTranslateX(-(BASE_RADIUS >> 1));
        nbToken.setTranslateY(5);
        return nbToken;
    }



    public void update() {

        this.getChildren().clear();
        this.getChildren().add(getBackground());
        // texte avec le nombre de jetons
        this.getChildren().add(getNbToken());
        // nom de la place
        this.getChildren().add(getPlaceName());
    }

    private Text getPlaceName() {
        Text placeName = new Text(this.place.getDescription());
        placeName.setFont(new Font(25));
        placeName.setTranslateX(30);
        placeName.setTranslateY(5);
        return placeName;
    }

    public static int getBaseRadius() {
        return BASE_RADIUS;
    }

    public Place getPlace() {
        return place;
    }
}
