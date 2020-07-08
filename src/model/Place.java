package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.editorMain.DrawingZone;

import java.util.ArrayList;
import java.util.List;

public class Place extends PetriNetComponent {

    private static final int BASE_RADIUS = 20;
    private static int LAST_PLACE_CREATED = 0;

    private List<Token> tokens;

    private Circle backgroundCircle;
    private Text nbToken;
    private Text placeName;
    private Rectangle border;


    /**
     * @param x la coordonnée x de la place
     * @param y la coordonnée y de la place
     */
    public Place(int x, int y, DrawingZone controller) {
        super(x, y, controller);
        this.description = "P" + ++LAST_PLACE_CREATED;
        this.tokens = new ArrayList<>();
        // definition du cercle de la place
        this.getChildren().add(getBackgroundCircle());
        // texte avec le nombre de jetons
        this.getChildren().add(getNbToken());
        // nom de la place
        this.getChildren().add(getPlaceName());


        this.setOnMouseDragged(mouseEvent -> {
            this.x += mouseEvent.getX();
            this.y += mouseEvent.getY();
            this.setTranslateX(this.x);
            this.setTranslateY(this.y);
        });
    }

    /**
     * Met a jour les éléments graphiques de la place
     */
    public void update() {
        this.getChildren().clear();
        this.getChildren().add(getBackgroundCircle());
        this.getChildren().add(getNbToken());
        this.getChildren().add(getPlaceName());
    }

    public int getNbJetons() {
        return this.tokens.size();
    }


    private Circle getBackgroundCircle() {
        Circle circle = new Circle();
        circle.setRadius(BASE_RADIUS);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private Text getNbToken() {
        Text nbToken = new Text(Integer.toString(this.tokens.size()));
        nbToken.setFont(new Font(25));
        nbToken.setTranslateX(-(BASE_RADIUS >> 1)); // division par 2 mais erreur avec le signe diviser
        nbToken.setTranslateY(5);
        return nbToken;
    }

    private Text getPlaceName() {
        Text placeName = new Text(this.description);
        placeName.setFont(new Font(25));
        placeName.setTranslateX(30);
        placeName.setTranslateY(5);
        return placeName;
    }

    public static int getBaseRadius() {
        return BASE_RADIUS;
    }


    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Retire tous les tokens de la place
     */
    public void viderPlace() {
        this.tokens = new ArrayList<>();
    }

    public boolean addToken(Token token) {
        if (this.tokens.add(token)) {
            token.setCurrentPlace(this);
            return true;
        }
        return false;
    }

    public Token removeToken() {
        if (tokens.size() > 0)
            return tokens.remove(tokens.size() - 1);
        return null;
    }

}
