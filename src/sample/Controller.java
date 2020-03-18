package sample;

import components.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.*;

import javax.xml.transform.sax.SAXSource;

public class Controller {

    public ToggleGroup componentsGroup;
    public RadioButton radioPlace;
    public RadioButton radioTransition;
    public RadioButton radioArc;
    public RadioButton radioToken;
    public RadioButton radioFranchir;
    public Pane petriNetPane;
    public Label labelPosition;
    public RadioButton radioEdition;

    private PetriNet petriNet;
    private PlaceComponent placeSelected;
    private TransitionComponent transitionSelected;
    private ArcComponent lastArc;
    private boolean dragging = false;
    private PetriObjectComponent draggingComponent;


    public Controller() {
        petriNet = new PetriNet();
    }


    public void MouseDragged(MouseEvent mouseEvent) {
        if (this.dragging && (radioEdition == componentsGroup.getSelectedToggle() || radioArc == componentsGroup.getSelectedToggle())) {
            this.placeSelected = null;
            this.transitionSelected = null;
            for (Node child : petriNetPane.getChildren()) {
                if (child instanceof ArcPreComponent) {
                    ArcPreComponent apc = (ArcPreComponent) child;
                    apc.update();
                } else if (child instanceof ArcPostComponent) {
                    ArcPostComponent apc = (ArcPostComponent) child;
                    apc.update();
                }
            }
            draggingComponent.setX((int) mouseEvent.getX());
            draggingComponent.setY((int) mouseEvent.getY());
        }
    }

    /**
     * Affiche un arc pour indiquer à l'utilisateur quel arc il est en train de dessiner
     *
     * @param mouseEvent
     */
    public void MouseMove(MouseEvent mouseEvent) {
        if (componentsGroup.getSelectedToggle() == radioArc) {
            if (lastArc != null) {
                removeLastArc();
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

    /**
     * Retire l'arc de prévisualisation de la fenetre graphique
     */
    private void removeLastArc() {
        petriNetPane.getChildren().remove(lastArc);
    }

    public void MouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            LeftMouseClicked(mouseEvent);
        }
        if (radioPlace == componentsGroup.getSelectedToggle()) {
            handlePlaceMouseClick(mouseEvent);
        }
        if (radioTransition == componentsGroup.getSelectedToggle()) {
            handleTransitionMouseClicked(mouseEvent);
        }
        if (radioToken == componentsGroup.getSelectedToggle()) {
            //handlePlaceMouseClick(mouseEvent);
            if(placeSelected != null){
                handleAddTokenMouseClick(mouseEvent);
            }
        }
        if (radioFranchir == componentsGroup.getSelectedToggle()) {
            //handlePlaceMouseClick(mouseEvent);
            if(transitionSelected != null){
                handleFranchisementMouseClick(mouseEvent);
            }
        }

        //System.out.println(petriNetPane.getChildren());
    }

    private void LeftMouseClicked(MouseEvent mouseEvent) {
        if (radioToken == componentsGroup.getSelectedToggle()) {
            //handlePlaceMouseClick(mouseEvent);
            if(placeSelected != null){
                handleRemoveTokenMouseClick(mouseEvent);
                System.out.println(petriNet.toString());
            }
        }
        placeSelected = null;
        transitionSelected = null;
        removeLastArc();
        lastArc = null;
    }

    private void handleAddTokenMouseClick(MouseEvent mouseEvent){
        Token t = new Token();
        t.setCurrentPlace(placeSelected.getPlace());
        placeSelected.getPlace().addToken(t);
        petriNet.addPetriObjects(t);
        placeSelected.update();
        placeSelected = null;
        //System.out.println(petriNet.getCurrentMarquage());
    }

    private void handleFranchisementMouseClick(MouseEvent mouseEvent){
        Transition t = transitionSelected.getTransition();
        petriNet.franchir(t);
        for (ArcPostComponent apc : transitionSelected.getArcPosts()) {
                apc.getPlace().update();
        }
        for (ArcPreComponent apc : transitionSelected.getArcPres()) {
            apc.getPlace().update();
        }
        transitionSelected = null;
        System.out.println(petriNet.getCurrentMarquage());
    }

    private void handleRemoveTokenMouseClick(MouseEvent mouseEvent){
        if (placeSelected.getPlace().getNbJetons() >0){
            Token t = placeSelected.getPlace().getTokens().get(placeSelected.getPlace().getNbJetons()-1);
            petriNet.removePetriObject(t);
            placeSelected.getPlace().removeToken(t);
            t.removePlace();

            placeSelected.update();
        }
        //System.out.println(petriNet.getCurrentMarquage());
    }

    private void handlePlaceMouseClick(MouseEvent mouseEvent) {
        Place place1 = new Place();
        PlaceComponent place = new PlaceComponent(place1, (int) mouseEvent.getX(), (int) mouseEvent.getY());
        petriNet.addPetriObjects(place1);
        place.setOnMouseClicked(m -> {
            if (transitionSelected == null) {
                placeSelected = place;
            } else {
                AddArcPost(place);
            }
        });
        place.setOnMousePressed(m -> {
            dragging = true;
            draggingComponent = place;
        });
        place.setOnMouseReleased(m -> {
            dragging = false;
            draggingComponent = null;
        });
        petriNetPane.getChildren().add(place);
    }

    private void handleTransitionMouseClicked(MouseEvent mouseEvent) {
        Transition transitionObject = new Transition();
        TransitionComponent transition = new TransitionComponent(transitionObject, (int) mouseEvent.getX(), (int) mouseEvent.getY());
        petriNet.addPetriObjects(transitionObject);
        transition.setOnMouseClicked(m -> {
            if (placeSelected == null) {
                transitionSelected = transition;
            } else {
                AddArcPre(transition);
            }
        });
        transition.setOnMousePressed(m -> {
            dragging = true;
            draggingComponent = transition;
        });
        transition.setOnMouseReleased(m -> {
            dragging = false;
            draggingComponent = null;
        });
        petriNetPane.getChildren().add(transition);
    }

    /**
     * Ajoute un arc Post à la fenêtre graphique et l'ajoute aussi à l'objet PetriNet pour garder un cohérence des
     * données
     *
     * @param place
     */
    private void AddArcPost(PlaceComponent place) {
        ArcPostComponent arcPostComponent = new ArcPostComponent(transitionSelected, place);
        petriNetPane.getChildren().add(arcPostComponent);
        ArcPost arcPost = new ArcPost(transitionSelected.getTransition(), place.getPlace());
        petriNet.addPetriObjects(arcPost);
        transitionSelected.getTransition().addArcPost(arcPost);
        transitionSelected.addArcPost(arcPostComponent);
        transitionSelected = null;
        placeSelected = null;
        if (lastArc != null) {
            removeLastArc();
        }
        lastArc = null;
    }

    /**
     * Ajoute un arc pre à la fenêtre graphique et l'ajoute aussi à l'objet PetriNet pour garder une cohérence des
     * données.
     *
     * @param transition la transition qui a été cliquée par l'utilisateur
     */
    private void AddArcPre(TransitionComponent transition) {
        ArcPreComponent arcPreComponent = new ArcPreComponent(placeSelected, transition);
        petriNetPane.getChildren().add(arcPreComponent);
        ArcPre arcPre = new ArcPre(transition.getTransition(), placeSelected.getPlace());
        petriNet.addPetriObjects(arcPre);
        transition.getTransition().addArcPre(arcPre);
        transition.addArcPre(arcPreComponent);
        placeSelected = null;
        transitionSelected = null;
        if (lastArc != null)
            removeLastArc();
        lastArc = null;
    }

}