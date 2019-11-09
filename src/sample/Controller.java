package sample;

import components.*;
import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Place;
import modele.Transition;

public class Controller {

    public Group graphPane;
    public ToggleGroup componentsGroup;
    public RadioButton radioPlace;
    public RadioButton radioTransition;
    public RadioButton radioArc;
    public Pane petriNetPane;

    private PlaceComponent placeSelected;
    private TransitionComponent transitionSelected;
    private ArcComponent lastArc;

    public void addPlace(MouseEvent mouseEvent) {
        graphPane.getChildren().add(new PlaceComponent(new Place(), 100, 100));
    }

    public void addElement(MouseEvent mouseEvent) {
        if (radioArc == componentsGroup.getSelectedToggle()) {
//            placeSelected = null;
//            transitionSelected = null;
        }
        if (radioPlace == componentsGroup.getSelectedToggle()) {
            PlaceComponent place = new PlaceComponent(new Place(), (int) mouseEvent.getX(), (int) mouseEvent.getY());
            place.setOnMouseClicked(mouseEvent1 -> {
                if (transitionSelected == null) {
                    placeSelected = place;
                } else {
                    petriNetPane.getChildren().add(new ArcPostComponent(transitionSelected, place));
                    transitionSelected = null;
                    placeSelected = null;
                    if (lastArc != null)
                        petriNetPane.getChildren().remove(lastArc);

                    lastArc = null;
                }
            });
            petriNetPane.getChildren().add(place);
        }
        if (radioTransition == componentsGroup.getSelectedToggle()) {
            TransitionComponent transition = new TransitionComponent(new Transition(), (int) mouseEvent.getX(), (int) mouseEvent.getY());
            transition.setOnMouseClicked(mouseEvent12 -> {
                System.out.println("clic !");
                if (placeSelected == null) {
                    transitionSelected = transition;
                } else {
                    petriNetPane.getChildren().add(new ArcPreComponent(placeSelected, transition));
                    placeSelected = null;
                    transitionSelected = null;
                    if (lastArc != null)
                        petriNetPane.getChildren().remove(lastArc);
                    lastArc = null;
                }
            });
            petriNetPane.getChildren().add(transition);
        }

    }

    public void showArc(MouseEvent mouseEvent) {

        if (componentsGroup.getSelectedToggle() == radioArc) {
            if (lastArc != null) {
                petriNetPane.getChildren().remove(lastArc);
            }
            if (placeSelected != null) {
                lastArc = new ArcComponent(placeSelected.getX(), placeSelected.getY(),
                        (int) mouseEvent.getX(), (int) mouseEvent.getY() - 3, 2);
            } else if (transitionSelected != null) {
                lastArc = new ArcComponent(transitionSelected.getX(), transitionSelected.getY(),
                        (int) mouseEvent.getX(), (int) mouseEvent.getY(), 1);
            }
            if (lastArc != null)
                petriNetPane.getChildren().add(lastArc);
        }

    }
}
